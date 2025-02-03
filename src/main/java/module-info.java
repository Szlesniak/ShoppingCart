module poprojekt.Cart {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens poprojekt.Cart to javafx.fxml;
    exports poprojekt.Cart;
}