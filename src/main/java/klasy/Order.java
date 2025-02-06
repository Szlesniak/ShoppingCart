package klasy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class Order extends Cart {
    DataManager dataManager = DataManager.getInstance();
    public int orderID;
    public User user;
    public String paymentMethod;
    public String deliveryMethod;

    private String PDFpath;
    public Order(int orderID, User user, String paymentMethod, String deliveryMethod) {
        this.orderID = orderID;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
    }
    public void finalizeOrder() {
        for (Product product : user.cart.getProducts()) {
            product.decreaseStock(user.cart.getIloscCart(product));
            user.cart.setCartProducts(product,0);
        }
        user.cart.clearCart();
        user.setOrder(this);
        dataManager.wiadomosc("Zamówienie zrealizowane! Ilość produktów w magazynie zaktualizowana.");
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
            try {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
            } catch (IOException e) {
                System.err.println("Error setting font: " + e.getMessage());
                contentStream.setFont(PDType1Font.COURIER, 12);
            }

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Zamowienie nr " + order.getOrderId());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 685);
            contentStream.showText("Nazwa produktu    Ilosc    Cena");
            contentStream.endText();

            int yOffset = 670;
            for (Product item : order.user.cart.getProducts()) {
                String row = String.format("%-20s %-10d %.2f zl",
                        item.getName(),
                        order.user.cart.getIloscCart(item),
                        item.getPrice()
                );
                contentStream.beginText();
                contentStream.newLineAtOffset(100, yOffset);
                contentStream.showText(row);
                contentStream.endText();
                yOffset -= 15;
            }

            contentStream.beginText();
            contentStream.newLineAtOffset(100, yOffset - 30);
            contentStream.showText("Suma: " + String.format("%.2f zl", order.user.cart.getTotalPrice()));
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(100, yOffset - 45);
            contentStream.showText("Metoda platnosci: " + order.paymentMethod);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(100, yOffset - 60);
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