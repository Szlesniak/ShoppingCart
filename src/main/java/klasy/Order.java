package klasy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class Order {
    public String orderID;
    public Cart cart;
    public Order(int orderID, Cart cart){

    }

    public String getOrderId() {
        return orderID;
    }

    public static void createOrderPdf(String filePath, Order order) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Nagłówek
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Zamówienie nr " + order.getOrderId());
            contentStream.endText();

            // Nagłówki tabeli
            contentStream.showText("Nazwa produktu\tIlość\tCena");
            contentStream.newLineAtOffset(0, -15);

            // Produkty
            for (Product item : order.cart.getProducts()) {
                String row = String.format("%s\t%d\t%.2f zł",
                        item.getName(),
                        item.getBought(),
                        item.getPrice()
                );
                contentStream.showText(row);
                contentStream.newLineAtOffset(0, -15);
            }

            // Suma
            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Suma: " + String.format("%.2f zł", order.cart.getTotalPrice()));
            contentStream.endText();

            contentStream.close();
            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}