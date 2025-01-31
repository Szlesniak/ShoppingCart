module poprojekt.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens poprojekt.demo1 to javafx.fxml;
    exports poprojekt.demo1;
}