package testconnect;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;

public class BarcodeGenerator {

    public void generateAndSaveBarcode(String inputLine, String filePath) {
        try {
            // Create the barcode
            Barcode barcode = BarcodeFactory.createCode128(inputLine);
            // Create the document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(inputLine+".pdf"));
            document.open();
            BufferedImage image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            barcode.draw(g, 10, 40);
            g.dispose();
            Image pdfImage = Image.getInstance(image, null);
            document.add(pdfImage);
            document.close();

            System.out.println("Barcode PDF generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
        barcodeGenerator.generateAndSaveBarcode("hello", "D:");
    }
}
