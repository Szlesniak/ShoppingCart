package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import klasy.DataManager;
import klasy.Product;
import klasy.User;

import java.io.IOException;

public class UserController {
    User currentUser;
    @FXML
    private TextField username;
    @FXML
    private VBox contentBox;


    DataManager dataManager = DataManager.getInstance();
    private static ObservableList<Product> productsList = FXCollections.observableArrayList();

    public void initialize() {
        productsList = dataManager.shareProductList();
        currentUser = dataManager.getCurrentUser();
        refresh();
    }

    public void logout(ActionEvent event) {
        dataManager.setCurrentUser(null);
        dataManager.changeScene(event, "/poprojekt/Cart/strona.fxml");
        dataManager.wiadomosc("Wylogowano");
    }

    private void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt.fxml"));
            Parent productNode = loader.load();

            SzablonController controller = loader.getController();
            controller.setProductData(product.getName(), product.getDescription(), product.getPrice(), product.getAmount() - product.getBought(), product.getPhoto());

            contentBox.getChildren().add(productNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Koszyk(ActionEvent event) {
        dataManager.changeScene(event, "/poprojekt/Cart/koszyk.fxml");
    }

    void refresh() {
        contentBox.getChildren().clear();
        for (Product product : productsList) {
            addProductToUI(product);
        }
        currentUser = dataManager.getCurrentUser();
        username.setText(currentUser.getName());
    }
}
