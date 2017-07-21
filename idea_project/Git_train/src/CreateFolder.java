import org.omg.CORBA.Environment;

import java.io.File;
import java.io.StringReader;

public class CreateFolder {
    public static void create() {
        File start = new File("e:\\test");
        start.mkdir();

        String path = "E:\\test\\VIRUS";
        String newPath;


        for (int i = 0; i <= 2000000000; i++) {
            newPath = path + i;
            File folder = new File(newPath);
            folder.mkdir();
        }
    }


    public static void delete() {
        String path = "E:\\test";
        File folder = new File(path);
        File[] flist = folder.listFiles();
        for (int i = 0; i < flist.length; i++) {
            flist[i].delete();
        }
    }


}
