import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class PdfHelper {

    private static final String pathHeader = "/home/yunti/Desktop/yedek/deneme/";
    private String temp;
    private Document doc;
    private FileOutputStream fos;
    private Image img;
    private int len;

    public PdfHelper() {

    }

    public void jpg2pdf(ArrayList<String> source, String name){

        temp = new String("");
        temp = pathHeader + name +".pdf";
        len = source.size();

        try {

            doc = new Document();
            PdfWriter.getInstance(doc,new FileOutputStream(temp));
            doc.open();

            int i ;
            for(i=0;i<len;i++){
                img = Image.getInstance(source.get(i));

                doc.setPageSize(img);
                img.setAbsolutePosition(0,0);
                doc.newPage();
                doc.add(img);
            }

            System.out.println("Pdf oluşturuldu!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            doc.close();
        }

    }


}
