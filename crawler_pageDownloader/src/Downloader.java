import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {


    public String download(String url) throws UnsupportedEncodingException {
        return getPage(url, getCharset(url));
    }



    private String getCharset(String url) {
        Pattern p = Pattern.compile("charset=(\"|)[A-z,-]{1,}[0-9]{1,}");
        Matcher m = p.matcher(getPage(url, "UTF-8"));
        String result = null;
        while (m.find()) {
            result = m.group().substring(8);
        }
        if (result.startsWith("\"")) {
            result = result.replaceAll("\"", "");
        }
        return result;
    }



    private String getPage(String url, String charset) {
        BufferedReader br = null;
        URL page = null;
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            page = new URL(url);
            br = new BufferedReader(new InputStreamReader(page.openStream(), Charset.forName(charset)));

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}