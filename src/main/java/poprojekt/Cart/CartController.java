package poprojekt.Cart;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import klasy.DataManager;
import klasy.Product;
import klasy.User;

public class CartController {
    UserController userController;
    @FXML
    private ComboBox<String> platnosc;
    @FXML
    private ComboBox<String> dostawa;
    @FXML
    private TextField prize;
    @FXML
    private VBox contentBox;
    @FXML
    private Button Clear;

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
        String deliveryMethod = dostawa.getSelectionModel().getSelectedItem();
        String paymentMethod = platnosc.getSelectionModel().getSelectedItem();
        refresh();
        if (currentuser.cart.getProducts().isEmpty()){
            Clear.setDisable(true);
            platnosc.setDisable(true);
            dostawa.setDisable(true);
        } else {
            Clear.setDisable(false);
            platnosc.setDisable(false);
            dostawa.setDisable(false);
        }
    }
    private void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt_koszyk.fxml"));
            Parent productNode = loader.load();

            SzablonKoszController controller = loader.getController();
            controller.setMainController(this);
            controller.setRoot(productNode);

            controller.setProductData(product.getName(), product.getBought(), product.getPrice(), product.getPhoto() );

            contentBox.getChildren().add(productNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void order() {


        wiadomosc("Zamówienie złożone!");
        clearcart();
    }
    public void clearcart() {
        if (currentuser.cart.getProducts().isEmpty()) {
            wiadomosc("Koszyk jest pusty nie ma czego usuwać!");
            return;
        }
        currentuser.cart.clearCart();
        refresh();
        userController.refresh();
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
        Platform.runLater(()->contentBox.getChildren().remove(template));
    }
    public void setMainController(UserController userController) {
        this.userController = userController;
    }
    public void wiadomosc(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wiadomość");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}
