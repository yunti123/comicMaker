import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DownloadImg {

    private String source;
    private String path;
    private String name;

    public DownloadImg(String source, String path, String name) throws IOException {
        this.source = source;
        this.path = path;
        this.name = name;
        doIt();
    }


    private void doIt() throws IOException {
        String temp = new File(path).getAbsolutePath();
        //String src = "http://www.omgbeaupeep.com/comics/mangas/Deadpool/016 - Cable and Deadpool 001 (2004)/read-deadpool-comics-online-free-002.jpg";
        //src = src.replaceAll(" ", "%20");
        //System.out.println(src);
        Boolean b = true;
        URL url =  new URL(source);
                                //http://www.omgbeaupeep.com/comics/mangas/Deadpool/016%20-%20Cable%20and%20Deadpool%20001%20(2004)/read-deadpool-comics-online-free-003.jpg
                                //http://www.omgbeaupeep.com/comics/mangas/Deadpool/016%20-%20Cable%20and%20Deadpool%20001%20(2004)/read-deadpool-comics-online-free-002.jpg



        InputStream is = null;
        OutputStream os = null;

        try {
           is = url.openStream();
            os = new FileOutputStream(temp+File.separator+ name + ".jpg");

            byte[] buff = new byte[2048];
            int length;

            while ((length = is.read(buff)) != -1){
                os.write(buff,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            b = false;
        }finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
                b = false;
            }

        }

        if(b)
            System.out.println("Kayıt Başarılı!!");
        else
            System.out.println("HATA!!");

    }
}
