import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class DownloadImg {

    private String source;
    private String path;
    private String name;
    private ArrayList<String> tut;
    private PdfHelper pdfHelper;

    public DownloadImg(String path) {
        this.path = path;
        pdfHelper = new PdfHelper();
        tut = new ArrayList<>();
    }

    public void SaveFromSource(String source, String name) throws IOException {
        this.source = source;
        this.name = name;
        doIt();
    }

    public void newPdf(){

        String adi = name.split("-")[0];
        pdfHelper.jpg2pdf(tut,adi);

        try{
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.interrupted();
            e.printStackTrace();
        }

        delete(tut);
        System.out.println("Resimler Silindi!!");

        tut = new ArrayList<>();
    }



    private void doIt() throws IOException {
        String temp = new File(path).getAbsolutePath();
        temp = temp + File.separator + name + ".jpg";

        Boolean b = true;
        URL url =  new URL(source);

        InputStream is = null;
        OutputStream os = null;

        try {
           is = url.openStream();
            os = new FileOutputStream(temp);

            byte[] buff = new byte[2048];
            int length;

            while ((length = is.read(buff)) != -1){
                os.write(buff,0,length);
            }
            tut.add(temp);
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
            System.out.println("Resim indirildi Başarılı!!");
        else
            System.out.println("HATA!!");

    }

    private void delete(ArrayList<String> arr){

        int len = arr.size();
        File f;

        try {

            int i;
            for(i = 0; i<len;i++){
               f = new File(arr.get(i));
               f.delete();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
