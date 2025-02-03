module poprojekt.Cart {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.pdfbox;


    opens poprojekt.Cart to javafx.fxml;
    exports poprojekt.Cart;
}