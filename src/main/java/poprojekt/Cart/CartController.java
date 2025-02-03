package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import klasy.DataManager;
import klasy.Product;
import klasy.User;

import java.io.IOException;

public class CartController {
    @FXML
    private ComboBox<String> platnosc;
    @FXML
    private ComboBox<String> dostawa;
    @FXML
    private TextField prize;
    @FXML
    private VBox contentBox;

    String paymentMethod1 = "Przelew";
    String paymentMethod2 = "Blik";
    String paymentMethod3 = "Gotówka przy odbiorze";
    String deliveryMethod1 = "Kurier";
    String deliveryMethod2 = "Paczkomat";
    String deliveryMethod3 = "Odbiór osobisty";
    User currentuser;
    DataManager dataManager = DataManager.getInstance();

    String deliveryMethod = dostawa.getSelectionModel().getSelectedItem();
    String paymentMethod = platnosc.getSelectionModel().getSelectedItem();

    private static ObservableList<Product> productsList = FXCollections.observableArrayList();

    public void initialize() {
        platnosc.getItems().addAll(paymentMethod1, paymentMethod2, paymentMethod3);
        dostawa.getItems().addAll(deliveryMethod1, deliveryMethod2, deliveryMethod3);
        productsList = currentuser.cart.getProducts();
        currentuser = dataManager.getCurrentUser();
        for (Product product : productsList) {
            addProductToUI(product);
        }
    }
    private void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt_koszyk.fxml"));
            Parent productNode = loader.load();

            SzablonController controller = loader.getController();
            controller.setProductData(product.getName(),product.getDescription(), product.getPrice(), product.getAmount(), product.getPhoto() );

            contentBox.getChildren().add(productNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void order() {

    }
    public void clearcart() {
        currentuser.cart.clearCart();
    }
}
