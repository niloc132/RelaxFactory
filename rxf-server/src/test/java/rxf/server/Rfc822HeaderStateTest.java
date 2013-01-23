package rxf.server;

import one.xio.HttpHeaders;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static one.xio.HttpMethod.UTF8;

public class Rfc822HeaderStateTest {
    static {
        BlobAntiPatternObject.setDEBUG_SENDJSON(true);
    }

    public static final String TMID_8627085284984078588 = "_tmid=8627085284984078588";
    public static final String FBSTUFF1 =
            "datr=byr_UBMy1etuo2RL3W6RgjP0; reg_fb_gate=https%3A%2F%2Fwww.facebook.com%2F; reg_fb_ref=https%3A%2F%2Fwww.facebook.com%2F; highContrast=0; wd=1680x395";
    public static final String FB_P3P_POLICY =
            "CP=\"Facebook does\n not have a\n P3P policy.\n Learn why\n here:\n http://fb.me/p3p\"";
    String H1 = "POST /StreamReceiver/services HTTP/1.1\n"
            + "Host: inplay-rcv02.scanscout.com\n"
            + "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:18.0) Gecko/20100101 Firefox/18.0 FirePHP/0.7.1\n"
            + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
            + "Accept-Language: en-US,en;q=0.5\n" + "Accept-Encoding: gzip, deflate\n" + "DNT: 1\n"
            + "Cookie: " + TMID_8627085284984078588 + "\n" + "x-insight: activate\n"
            + "Connection: keep-alive\n",
            H2 = "GET / HTTP/1.1\n"
                    + "Host: www.facebook.com\n"
                    + "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:18.0) Gecko/20100101 Firefox/18.0 FirePHP/0.7.1\n"
                    + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
                    + "Accept-Language: en-US,en;q=0.5\n" + "Accept-Encoding: gzip, deflate\n" + "DNT: 1\n"
                    + "Cookie: " + FBSTUFF1 + "\n" + "x-insight: activate\n" + "Connection: keep-alive\n"
                    + "Cookie: " + TMID_8627085284984078588 + "\n" + "Cache-Control: max-age=0\n" + "\n";
    String H3 = "HTTP/1.1 200 OK\n"
            + "Cache-Control: private, no-cache, no-store, must-revalidate\n"
            + "Content-Encoding: gzip\n"
            + "Content-Type: text/html; charset=utf-8\n"
            + "Date: Wed, 23 Jan 2013 00:10:39 GMT\n"
            + "Expires: Sat, 01 Jan 2000 00:00:00 GMT\n"
            + "P3P: "
            + FB_P3P_POLICY
            + "\n"
            + "Pragma: no-cache\n"
            + "Set-Cookie: highContrast=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/; domain=.facebook.com; httponly\n"
            + "reg_ext_ref=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/; domain=.facebook.com\n"
            + "wd=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/; domain=.facebook.com; httponly\n"
            + "X-Content-Type-Options: nosniff\n" + "X-Frame-Options: DENY\n"
            + "X-XSS-Protection: 1; mode=block\n"
            + "X-FB-Debug: AyxIiMHG0EA+jrasdfasdfsEsvtwdICkt4496U=\n" + "X-Firefox-Spdy: 2\n" + "\n",
            H4 = "POST /mail/u/0/?ui=2&ik=600asdfasdf86&rid=3472..&auto=1&view=lno&_reqid=1asdfasdf5&pcd=1&mb=0&rt=j HTTP/1.1\n" +
                    "Host: mail.google.com\n" +
                    "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:18.0) Gecko/20100101 Firefox/18.0 FirePHP/0.7.1\n" +
                    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                    "Accept-Language: en-US,en;q=0.5\n" +
                    "Accept-Encoding: gzip, deflate\n" +
                    "DNT: 1\n" +
                    "X-Same-Domain: 1\n" +
                    "Content-Type: application/x-www-form-urlencoded;charset=utf-8\n" +
                    "Referer: https://mail.google.com/_/mail-static/_/js/main/m_i,t/rt=h/ver=hNp-bC4BSMQ.en./sv=1/am=!yvlu5erXbzf1BO3aasdfasdNhplkFTF3-2sI8ciWu5Ccq2YWUOe8_5g/d=1\n" +
                    "Content-Length: 0\n" +
                    "Cookie: gmailchat=sadfasdaf@gmail.com/902064; GMAIL_AT=AF6bupPsadfasdfaAFWB8ps4MXleg; GMAIL_IMP=v*2%2Fgd-t*1*1150!http%3A%252f%252fwww.google.com%252fig%252fmodules%252fcalendar-gmail.xml%2Fip-il*0%2Fip-o*0%2Fip-dil*0%2Fip-r*1%2Fip-oe*1%2Fip-p*1%2Fgd-t*1*2094!http%3A%252f%252fwww.google.com%252fig%252fmodules%252fdocs.xml%2Fbc-se6*1%2Fbc-se12*1%2Fpf-s*4673; GX=DQAAAJkBAAAlnE4Pu1mJmcasdfasdffKRTN5voSQURUbGUSv3qLINpHwX0dRuye6XdWZLyJ4skV14FXZGVT2mvjmjxi8cDkQw0N25elAqh2B-BfGg8GnRBUO8J7ic6tkXp2af3c-jSVcgAHky3me9Thw9wQaeYEJ9GIXCzx5A00Feg790rPprZHMzaV6PQ1Id1iRsrBwgV4e5l7c3zjZ1cJ_1ovcVIubMc1pIyhAAyx0gdg90fm5mVmRDGEzc6MQClM70pmbfo1aFfV0HjonpFZ011DUnUWfk4mulIwpLFrbwE7oCWSRJff72Qj2BPMTyp0CbcvVjdtZaJ5x1mE0_yQCkoXNdd_6LBLFoOwGE_oDQd6Yq0fwOgUadf0_odvD9QRmZYb5VF43QN4AKxcvHp4g5HIb_isBUVH__xVtpM_PWhtqLqHr2ruPuLTFbD8-EKFIENxDvZqJNMzMo8UFxiNr6sdfasdfiCQMMelmpqSgCJNmAEskM9zj8GOeTpyh7nnjMXZzERAzrK7w; S=gmail=fgbZW4vIzVWCtZnFVCoOMg; PREF=ID=5a2sdfa90e7:U=8c514sfda77f:FF=0:LD=en:TM=1357sadf00:LM=1358625547:GM=1:S=rYMmpjBrqo0Q78y5; NID=67=dy_0k2SRmS5-q7gEtD5f-az0WwCpNciGDvuiP3x0kJLsarI5pQRzKqvd0ajnn3-Dq6MWimIBE-C8ySfOOqx5j17vatu3pfuZsfrdthMTVaUIL7cE5z94j_E7_TYak95N1DfpFsAl50GetpjXd0MjjiuD2V0V4DDMttm0cxEscxSCgtTfEvmfHkwfC9f8BJ28tPoHaq8Pigxi0Dj1NZmHIHbVibm7fhFBneIpf_g42_YU; SID=DQAAAJYBAACJjEbykiLymnsmCijJ7sPIiCsCwT3v_yD44NeDI1i_r5mhm6Hnf-_RkyYccS7t06YAC5U7bmilftFnUYXMydh1JjzbltkVug8NoPwiluQ7WXVB2mSCk2aOJoSZ1J3IckydUz2y8Hznun6YoFC0rWkWyPuChf_X7devicDNd7dtSAo8OunVXn2hbGkBC3AuQb27lXtcf7ATLIz9Arh4xLTM0dDj_Z9CVeBWAkDBFpk-wU7MDijwAZuxeUTAZiGPBFvO8S0FuZnP9IasdfRJJpIFoKQm_uvvJ6_JJ9fXNyllr3w36HaqF42tzTmJ6rA7eiqDsWWd-pfjWe3Qs9uUtlzsG0jd3FOVWO9KihjJOlO5YuXLZjLiqWD027T-yiLh7KV9Zlrs-sZyPeyk31z1AasdfasdfNxN9rHe8K_kfn4k8XFGPbwQpiExiQwon6_H7BoNm7RYa2BHP_9arlNBSagylN_GV54MWLKMWxbsw4q2DfBmT_bCfmkpQ; HSID=ALfs2asdfNogoY; SSID=A3ZS9cJVATN-UjcZP; APISID=DHxcLasdfsdfH/AxXWzuqwfHtg295qj; SAPISID=tgSIdsbz9xkHOX1P/Agnhtasdf2FpFQ\n" +
                    "x-insight: activate\n" +
                    "Connection: keep-alive\n" +
                    "Pragma: no-cache\n" +
                    "Cache-Control: no-cache\n" +
                    "\n";

    @Test
    public void testAppendHeadersOne() {
        Rfc822HeaderState state = new Rfc822HeaderState("One");
        assertEquals(1, state.headerInterest().length);
        state.addHeaderInterest("Two");
        assertEquals(2, state.headerInterest().length);
        String[] expecteds = {"One", "Two"};
        Assert.assertArrayEquals(expecteds, state.headerInterest());
    }

    @Test
    public void testAppendHeadersMany() {
        Rfc822HeaderState state = new Rfc822HeaderState("One");
        assertEquals(1, state.headerInterest().length);
        state.addHeaderInterest("Two", "Three");
        assertEquals(3, state.headerInterest().length);
        String[] expecteds = {"One", "Three", "Two",};
        Assert.assertArrayEquals(expecteds, state.headerInterest());
    }

    @Test
    public void testAsRequestHeaderByteBuffer() {
        Rfc822HeaderState req = new Rfc822HeaderState();
        req.methodProtocol("VERB").pathResCode("/noun").protocolStatus("HTTP/1.0").headerString(
                "Header", "value").headerString("Header2", "value2");
        ByteBuffer buf = req.asRequestHeaderByteBuffer();
        String result = UTF8.decode(buf.duplicate()).toString();

        assertEquals("VERB /noun HTTP/1.0\r\nHeader: value\r\nHeader2: value2\r\n\r\n", result);
    }

    @Test
    public void testApplySimpleResponse() {
        ByteBuffer simpleResponse =
                ByteBuffer.wrap("HTTP/1.0 200 OK\r\nServer: NotReallyAServer\r\n\r\n".getBytes());

        Rfc822HeaderState state = new Rfc822HeaderState();
        state.addHeaderInterest("Server");
        state.apply(simpleResponse);

        final String actual = state.methodProtocol();
        assertEquals("HTTP/1.0", actual);
        final String actual1 = state.pathResCode();
        assertEquals("200", actual1);
        final String actual2 = state.protocolStatus();
        assertEquals("OK", actual2);
        final String server = state.headerString("Server");
        assertEquals("NotReallyAServer", server);
    }

    @Test
    public void testApplySimpleRequest() {
        ByteBuffer simpleRequest =
                ByteBuffer
                        .wrap("GET /file/from/path.suffix HTTP/1.0\r\nContent-Type: application/json\r\n\r\n"
                                .getBytes());

        Rfc822HeaderState state = new Rfc822HeaderState("Content-Type");
        state.apply(simpleRequest);

        assertEquals("GET", state.methodProtocol());
        assertEquals("/file/from/path.suffix", state.pathResCode());
        assertEquals("HTTP/1.0", state.protocolStatus());
        assertEquals("application/json", state.headerString("Content-Type"));
    }

    @Test
    public void testAsResponseHeaderByteBuffer() {
        Rfc822HeaderState resp = new Rfc822HeaderState();
        resp.methodProtocol("HTTP/1.0").pathResCode("501").protocolStatus("Unsupported Method")
                .headerString("Connection", "close");
        ByteBuffer buf = resp.asResponseHeaderByteBuffer();
        String result = UTF8.decode(buf).toString();
        assertEquals("HTTP/1.0 501 Unsupported Method\r\nConnection: close\r\n\r\n", result);
    }

    @Test
    public void testSimpleHeader() {
        ByteBuffer buf = (ByteBuffer) UTF8.encode(H1).rewind();
        buf.toString();

        Rfc822HeaderState apply = ActionBuilder.get().state().apply(buf);
        List<String> headersNamed = apply.$req().getHeadersNamed(HttpHeaders.Cookie);
        assertEquals(TMID_8627085284984078588, headersNamed.iterator().next());

    }

    @Test
    public void testMultiHeader() {
        ByteBuffer buf = (ByteBuffer) UTF8.encode(H2).rewind();
        buf.toString();

        Rfc822HeaderState apply = ActionBuilder.get().state().apply(buf);
        List<String> headersNamed = apply.$req().getHeadersNamed(HttpHeaders.Cookie);
        Iterator<String> iterator = headersNamed.iterator();
        assertEquals(TMID_8627085284984078588, iterator.next());
        assertEquals(FBSTUFF1, iterator.next());
    }

    @Test
    public void testHeaderLineContinuations() {
        ByteBuffer buf = (ByteBuffer) UTF8.encode(H3).rewind();
        buf.toString();

        Rfc822HeaderState apply = ActionBuilder.get().state().apply(buf);
        List<String> headersNamed = apply.$req().getHeadersNamed("P3P");
        Iterator<String> iterator = headersNamed.iterator();
        assertEquals(FB_P3P_POLICY, iterator
                .next());
    }

    @Test
    public void testStupicBigCookies() {
        ByteBuffer buf = (ByteBuffer) UTF8.encode(H4).rewind();
        buf.toString();

        Rfc822HeaderState apply = ActionBuilder.get().state().apply(buf);
        Pair<ByteBuffer, ? extends Pair> byteBufferPair = apply.headerExtract(HttpHeaders.Cookie.getToken());
        assertNotNull(byteBufferPair);
        ByteBuffer a = byteBufferPair.getA();
        Pair<Pair<ByteBuffer, ByteBuffer>, ? extends Pair> pairPair = Rfc6265.parseCookie(a);
        assertNotNull(pairPair);
        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
        while (pairPair != null) {
            Pair<ByteBuffer, ByteBuffer> a1 = pairPair.getA();
            objectObjectLinkedHashMap.put(UTF8.decode(a1.getA()), UTF8.decode(a1.getB()));
            pairPair = pairPair.getB();
        }
        assertEquals("{SAPISID=tgSIdsbz9xkHOX1P/Agnhtasdf2FpF, APISID=DHxcLasdfsdfH/AxXWzuqwfHtg295qj, SSID=A3ZS9cJVATN-UjcZP, HSID=ALfs2asdfNogoY, SID=DQAAAJYBAACJjEbykiLymnsmCijJ7sPIiCsCwT3v_yD44NeDI1i_r5mhm6Hnf-_RkyYccS7t06YAC5U7bmilftFnUYXMydh1JjzbltkVug8NoPwiluQ7WXVB2mSCk2aOJoSZ1J3IckydUz2y8Hznun6YoFC0rWkWyPuChf_X7devicDNd7dtSAo8OunVXn2hbGkBC3AuQb27lXtcf7ATLIz9Arh4xLTM0dDj_Z9CVeBWAkDBFpk-wU7MDijwAZuxeUTAZiGPBFvO8S0FuZnP9IasdfRJJpIFoKQm_uvvJ6_JJ9fXNyllr3w36HaqF42tzTmJ6rA7eiqDsWWd-pfjWe3Qs9uUtlzsG0jd3FOVWO9KihjJOlO5YuXLZjLiqWD027T-yiLh7KV9Zlrs-sZyPeyk31z1AasdfasdfNxN9rHe8K_kfn4k8XFGPbwQpiExiQwon6_H7BoNm7RYa2BHP_9arlNBSagylN_GV54MWLKMWxbsw4q2DfBmT_bCfmkpQ, NID=67=dy_0k2SRmS5-q7gEtD5f-az0WwCpNciGDvuiP3x0kJLsarI5pQRzKqvd0ajnn3-Dq6MWimIBE-C8ySfOOqx5j17vatu3pfuZsfrdthMTVaUIL7cE5z94j_E7_TYak95N1DfpFsAl50GetpjXd0MjjiuD2V0V4DDMttm0cxEscxSCgtTfEvmfHkwfC9f8BJ28tPoHaq8Pigxi0Dj1NZmHIHbVibm7fhFBneIpf_g42_YU, PREF=ID=5a2sdfa90e7:U=8c514sfda77f:FF=0:LD=en:TM=1357sadf00:LM=1358625547:GM=1:S=rYMmpjBrqo0Q78y5, S=gmail=fgbZW4vIzVWCtZnFVCoOMg, GX=DQAAAJkBAAAlnE4Pu1mJmcasdfasdffKRTN5voSQURUbGUSv3qLINpHwX0dRuye6XdWZLyJ4skV14FXZGVT2mvjmjxi8cDkQw0N25elAqh2B-BfGg8GnRBUO8J7ic6tkXp2af3c-jSVcgAHky3me9Thw9wQaeYEJ9GIXCzx5A00Feg790rPprZHMzaV6PQ1Id1iRsrBwgV4e5l7c3zjZ1cJ_1ovcVIubMc1pIyhAAyx0gdg90fm5mVmRDGEzc6MQClM70pmbfo1aFfV0HjonpFZ011DUnUWfk4mulIwpLFrbwE7oCWSRJff72Qj2BPMTyp0CbcvVjdtZaJ5x1mE0_yQCkoXNdd_6LBLFoOwGE_oDQd6Yq0fwOgUadf0_odvD9QRmZYb5VF43QN4AKxcvHp4g5HIb_isBUVH__xVtpM_PWhtqLqHr2ruPuLTFbD8-EKFIENxDvZqJNMzMo8UFxiNr6sdfasdfiCQMMelmpqSgCJNmAEskM9zj8GOeTpyh7nnjMXZzERAzrK7w, GMAIL_IMP=v*2%2Fgd-t*1*1150!http%3A%252f%252fwww.google.com%252fig%252fmodules%252fcalendar-gmail.xml%2Fip-il*0%2Fip-o*0%2Fip-dil*0%2Fip-r*1%2Fip-oe*1%2Fip-p*1%2Fgd-t*1*2094!http%3A%252f%252fwww.google.com%252fig%252fmodules%252fdocs.xml%2Fbc-se6*1%2Fbc-se12*1%2Fpf-s*4673, GMAIL_AT=AF6bupPsadfasdfaAFWB8ps4MXleg, gmailchat=sadfasdaf@gmail.com/902064}"
                , String.valueOf(objectObjectLinkedHashMap));
    }
}
