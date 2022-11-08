
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcos
 */
public class DownloadPDF {

    public static void main(String[] args) throws IOException, MalformedURLException, COSVisitorException {
        download();
    }

    public static void download() throws MalformedURLException, IOException, COSVisitorException {
        PDDocument document = new PDDocument();
        boolean flag=true;
        String fileName="NameToPDF";
        String rutaSaveFile="C:\\YourComputerRoute\\"+fileName+".pdf";
            for (int i = 1; flag; i++) {
                try{
                    URL url = new URL("https://DomainURL_JPG_" + i + ".jpg");
                    BufferedImage img = ImageIO.read(url);
                    float w = img.getWidth();
                    float h = img.getHeight();
                    PDPage page = new PDPage(new PDRectangle(w, h));
                    document.addPage(page);
                    PDXObjectImage img2 = new PDJpeg(document, img);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.drawImage(img2, 0, 0);
                    contentStream.close();
                    System.out.println("Página: "+i+"\n");
                }catch(NullPointerException ex){
                    document.save(rutaSaveFile);
                    document.close();
                    flag=false;
                }
            }
        
    }
}
