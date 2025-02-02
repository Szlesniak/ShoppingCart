package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import klasy.Product;

public class KoszykController {
    @FXML
    private ListView<Product> productList;
    ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
//        productList.setItems(products);
    }
}