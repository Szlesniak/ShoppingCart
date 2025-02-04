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

    private static ObservableList<Product> productsList = FXCollections.observableArrayList();

    public void initialize() {
        platnosc.getItems().addAll(paymentMethod1, paymentMethod2, paymentMethod3);
        dostawa.getItems().addAll(deliveryMethod1, deliveryMethod2, deliveryMethod3);
        currentuser = dataManager.getCurrentUser();
        productsList = currentuser.cart.getProducts();
        currentuser = dataManager.getCurrentUser();
        refresh();
        String deliveryMethod = dostawa.getSelectionModel().getSelectedItem();
        String paymentMethod = platnosc.getSelectionModel().getSelectedItem();
    }
    private void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt_koszyk.fxml"));
            Parent productNode = loader.load();

            SzablonKoszController controller = loader.getController();
            controller.setMainController(this);
            controller.setRoot(this);
            CartController.getChildren().add(productNode);
            controller.setProductData(product.getName(), product.getBought(), product.getPrice(), product.getPhoto() );

            contentBox.getChildren().add(productNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void order() {

        clearcart();
    }
    public void clearcart() {
        currentuser.cart.clearCart();
        refresh();
    }
    public void refresh() {
        contentBox.getChildren().clear();
        productsList = currentuser.cart.getProducts();
        for (Product product : productsList) {
            addProductToUI(product);
        }
        prize.setText(Double.toString(currentuser.cart.getTotalPrice()));
    }
    public void removeTemplate(Parent template) {
        CartController.getChildren().remove(template);
    }
}
