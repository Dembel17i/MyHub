import sun.text.normalizer.UTF16;
import sun.text.normalizer.UnicodeSet;
import sun.util.calendar.BaseCalendar;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;

public class Downloader {

    public static File createFile(String url) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        String fileName = url + "_" + dateFormat.format(new Date()) + ".txt";
        fileName = fileName.replace(':', '.');
        fileName = fileName.replace('/', '.');

        File dir = new File("downloaded_pages");
        File page = new File("downloaded_pages", fileName);
        System.out.println(fileName);
        if (!dir.exists()) dir.mkdir();

        try {
            page.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return page.getAbsoluteFile();
    }


    public static String download(String url) {
        BufferedReader br = null;
        FileWriter writer = null;
        BufferedWriter bw = null;
        URL page = null;


        try {
            page = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            br = new BufferedReader(new InputStreamReader(page.openStream()));
//            writer = new FileWriter(createFile(url));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(createFile(url), false), Charset.forName("UTF-8")));

            String line;
            while ((line = br.readLine()) != null)
//                System.out.println(line);
//                writer.write(line);
                bw.write(line);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "f";
    }


    public static void main(String[] args) throws IOException {
//    createFile("http://lenta.ru/");
//    download("https://lenta.ru/");
//        download("https://4pda.ru/");
        download("https://www.youtube.com/watch?v=GQzORogZX-Q");
    }

}
