package rxf.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;

import one.xio.AsioVisitor;

import static one.xio.HttpMethod.UTF8;
import static rxf.server.BlobAntiPatternObject.ThreadLocalSetCookies;

/**
 * User: jim
 * Date: 4/21/12
 * Time: 2:50 PM
 */
class CookieAwareWriteResponse extends AsioVisitor.Impl {

  private final String process;
  private final SelectionKey key;

  public CookieAwareWriteResponse(String process, SelectionKey key) {
    this.process = process;
    this.key = key;
  }

  @Override
  public void onWrite(SelectionKey selectionKey) throws IOException {
    Map<String, String> setCookiesMap = ThreadLocalSetCookies.get();
    String cookieDeclaration = "";
    if (null != setCookiesMap && !setCookiesMap.isEmpty()) {
      cookieDeclaration = "Set-Cookie: ";

      Iterator<Map.Entry<String, String>> iterator = setCookiesMap.entrySet().iterator();
      if (iterator.hasNext()) {
        do {
          Map.Entry<String, String> stringStringEntry = iterator.next();
          cookieDeclaration += stringStringEntry.getKey() + "=" + stringStringEntry.getValue().trim();
          if (iterator.hasNext()) cookieDeclaration += "; ";
        } while (iterator.hasNext());
      }
      cookieDeclaration += "\r\n";
    }
    int length = process.length();
    String s1 = "HTTP/1.1 200 OK\r\n" +
        cookieDeclaration +
        "Content-Type: application/json\r\n" +
        "Content-Length: " + length + "\r\n\r\n" + process;
    final ByteBuffer payload = UTF8.encode(s1);
    final int write = ((SocketChannel) key.channel()).write(payload);
    final int total = payload.limit();
    if (total != write) key.attach(new AsioVisitor.Impl() {
      int remaining = total - write;


      @Override
      public void onWrite(SelectionKey selectionKey) throws IOException {
        int write1 = ((SocketChannel) selectionKey.channel()).write((ByteBuffer) payload.position(total - remaining));
        remaining -= write1;
      }


    });

    System.err.println("debug: " + s1);
  }

}
