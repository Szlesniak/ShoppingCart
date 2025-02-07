package klasy;
import javafx.scene.control.Alert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;

public class Order extends Cart {
    public int orderID;
    public User user;
    public String paymentMethod;
    public String deliveryMethod;
    private String PDFpath;
    public Order(int orderID, String paymentMethod, String deliveryMethod) {
        this.orderID = orderID;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
    }
    public void finalizeOrder() {
        for (Product product : user.cart.getProducts()) {
            product.decreaseStock(user.cart.getIloscCart(product));
            product.setSold(user.cart.getIloscCart(product));
            user.cart.setCartProducts(product,0);
        }
        user.cart.clearCart();
        wiadomosc("Zamówienie zrealizowane!");
    }

    public int getOrderId() {
        return orderID;
    }
    public void setPDFpath(String path) {
        this.PDFpath = path;
    }
    public void setUser(User user){
        this.user = user;
        user.addOrder(this);
    }

    public void createOrderPdf(String filePath, Order order) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.COURIER, 12);

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Zamowienie nr " + order.getOrderId());
            contentStream.endText();

            String header = String.format("%-25s %-10s %s", "Nazwa produktu", "Ilosc", "Cena");
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 685);
            contentStream.showText(header);
            contentStream.endText();

            int yOffset = 670;
            for (Product item : order.user.cart.getProducts()) {
                int ilosc = order.user.cart.getIloscCart(item);
                double cena = item.getPrice();

                String row = String.format("%-25s %-10d %.2f zl",
                        item.getName(),
                        ilosc,
                        cena);

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

            contentStream.beginText();
            contentStream.newLineAtOffset(100, yOffset - 75);
            contentStream.showText("Nabywca: " + order.user.getName() + " " + order.user.getSurname());
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(100, yOffset - 90);
            contentStream.showText("Adres dostawy: " + order.user.getUlica() + " " + order.user.getNr_bud() + ", " + order.user.getMiasto());
            contentStream.endText();

            contentStream.close();
            setPDFpath(filePath);
            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void wiadomosc(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wiadomość");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
    public User getUser() {
        return user;
    }
}