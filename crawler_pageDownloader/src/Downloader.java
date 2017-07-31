package components;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {


    public String download(String url) {
        return loadPage(url, charsetLookUp(url));
    }


    public String download(String url, String charsetName) {
        return loadPage(url, charsetName);
    }


    private String charsetLookUp(String url) {
        Pattern p = Pattern.compile("charset=(\"|)[A-z,-]{1,}[0-9]{1,}");
        Matcher m = p.matcher(loadPage(url, "UTF-8"));
        String result = null;
        try {
            while (m.find()) {
                result = m.group().substring(8);
            }
            if (result.startsWith("\"")) {
                result = result.replaceAll("\"", "");
            }
        } catch (NullPointerException e) {
            try {
                throw new RuntimeException("Не удолось определить кодировку страницы. Будет установлена кодировка по умолчанию - UTF-8!");
            } catch (RuntimeException e1) {
                e1.printStackTrace();
                return "utf-8";
            }
        }
        return result;
    }


    private String loadPage(String url, String charset) {
        BufferedReader br = null;
        URL page;
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            page = new URL(url);
            br = new BufferedReader(new InputStreamReader(page.openStream(), Charset.forName(charset)));

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            try {
                throw new RuntimeException("Не удалось загрузить страницу!");
            } catch (RuntimeException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}