package klasy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class Order extends Cart {
    public int orderID;
    public User user;
    public boolean finalized;
    public String paymentMethod;
    public String deliveryMethod;
    private String PDFpath;
    public Order(int orderID, User user, String paymentMethod, String deliveryMethod) {
        this.orderID = orderID;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
    }
    public void setFinalized(boolean something){
        finalized = something;
    }
    public boolean isFinalized(){
        return finalized;    }

    public void finalizeOrder() {
        for (Product product : user.cart.getProducts()) {
            product.decreaseStock(product.getBought());
            product.setBought(0);
        }
        user.cart.clearCart();
        System.out.println("Zamówienie zrealizowane! Ilość produktów w magazynie zaktualizowana.");
    }

    public int getOrderId() {
        return orderID;
    }
    public void setPDFpath(String path) {
        this.PDFpath = path;
    }

    public void createOrderPdf(String filePath, Order order) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Zamówienie nr " + order.getOrderId());
            contentStream.endText();

            contentStream.showText("Nazwa produktu\tIlość\tCena");
            contentStream.newLineAtOffset(0, -15);

            for (Product item : order.user.cart.getProducts()) {
                String row = String.format("%s\t%d\t%.2f zł",
                        item.getName(),
                        item.getBought(),
                        item.getPrice()
                );
                contentStream.showText(row);
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.newLineAtOffset(0, -30);
            contentStream.showText("Suma: " + String.format("%.2f zł", order.user.cart.getTotalPrice()));
            contentStream.showText("Metoda płatności: " + order.paymentMethod);
            contentStream.showText("Metoda dostawy: " + order.deliveryMethod);
            contentStream.endText();

            contentStream.close();
            setPDFpath(filePath);
            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}