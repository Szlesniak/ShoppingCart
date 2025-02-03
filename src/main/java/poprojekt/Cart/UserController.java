package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasy.DataManager;
import klasy.Product;

public class UserController {

    DataManager dataManager = DataManager.getInstance();
    private static ObservableList<Product> productsList = FXCollections.observableArrayList();

    public void initialize() {
        productsList = dataManager.shareProductList();
    }
}
