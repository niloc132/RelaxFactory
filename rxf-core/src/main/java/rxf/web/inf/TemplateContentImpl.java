package rxf.web.inf;

import com.google.common.io.CharStreams;
import one.xio.HttpStatus;
import rxf.shared.PreRead;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.channels.SelectionKey;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@PreRead
public abstract class TemplateContentImpl extends ContentRootImpl {
    static Map<File, File> content = new LinkedHashMap<>();

    @Override
    public void onWrite(final SelectionKey key) throws Exception {

        //todo: paths
        String finalFname = fileScrub(getRootPath() + SLASHDOTSLASH + this.getReq().path().split("\\?")[0]);
        File file = new File(finalFname);
        if (file.isDirectory()) {
            file = new File(finalFname + "/index.html");
        }
        finalFname = file.getCanonicalPath();


        boolean send200 = file.canRead() && file.isFile();

        if (send200) {
            File rxf;
            if (!content.containsKey(file)) {
                rxf = File.createTempFile("rxf", ".html");
                rxf.deleteOnExit();
                try (FileWriter fileWriter = new FileWriter(rxf)) {
                    fileWriter.write(doReplace(CharStreams.toString(new FileReader(file))));
                }
                content.put(file, rxf);
            } else
                rxf = content.get(file);
            sendFile(key, finalFname, rxf, new Date(), getReq().asResponse().status(HttpStatus.$200), null);
        }
    }

    public    abstract String doReplace(String src);
}
