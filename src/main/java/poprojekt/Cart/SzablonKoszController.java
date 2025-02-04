package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import klasy.Cart;
import klasy.DataManager;
import klasy.Product;
import klasy.User;

public class SzablonKoszController {
    private CartController mainController;
    private Parent root;
    User currentuser;
    Cart currentcart;
    Product currentproduct;
    DataManager dataManager = DataManager.getInstance();
    ObservableList<Product> CartProductList = FXCollections.observableArrayList();
    int amount;
    @FXML
    private Label Name;
    @FXML
    private TextField Amount;
    @FXML
    private ImageView Photo;
    @FXML
    private TextField Prize;
    @FXML
    private TextField Delete;

    public void delete() {
        if (CartProductList.isEmpty()) {
            wiadomosc("Koszyk jest pusty!");
        } else {
            for (Product product : CartProductList) {
                if (product.getName().equals(Name.getText())) {
                    currentproduct = product;
                    if (Delete.getText().isEmpty() || Delete.getText() == null || Delete.getText().equals("0")) {
                        wiadomosc("Podaj ilość!");
                        return;
                    } else {
                        amount = Integer.parseInt(Delete.getText());
                    }
                    currentproduct.decreaseStock(amount);
                    wiadomosc("Usunięto z koszyka: " + currentproduct.getName() + "\nW ilości: " + amount);
                    if (currentproduct.getAmount() <= 0 && mainController != null && root != null) {
                        currentcart.removeProduct(currentproduct);
                        mainController.removeTemplate(root);
                    }
                }
            }
            refresh();
            mainController.refresh();
        }
    }

    public void initialize() {
        currentuser = dataManager.getCurrentUser();
        currentcart = currentuser.cart;;
        CartProductList = currentcart.getProducts();
    }
    public void setProductData(String name, int amount  , Double prize, String photo){
        Name.setText(name);
        Amount.setText(Integer.toString(amount));
        Prize.setText(Double.toString(prize));
        Photo.setImage(new javafx.scene.image.Image(photo));
    }
    private void refresh(){
        Name.setText(currentproduct.getName());
        Amount.setText(Integer.toString(currentproduct.getAmount()));
        Prize.setText(Double.toString(currentproduct.getPrice()));
    }
    public void setMainController(CartController mainController) {
        this.mainController = mainController;
    }
    public void setRoot(Parent root) {
        this.root = root;
    }
    public void wiadomosc(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wiadomość");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}
