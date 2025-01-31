module poprojekt.Cart {
    requires javafx.controls;
    requires javafx.fxml;


    opens poprojekt.Cart to javafx.fxml;
    exports poprojekt.Cart;
}