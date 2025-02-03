package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import klasy.DataManager;
import klasy.Product;
import klasy.User;


public class SzablonController {
    User currentuser;
    Product currentproduct;
    ObservableList<Product> productsList = FXCollections.observableArrayList();
    @FXML
    private Label Description;
    @FXML
    private TextField Prize;
    @FXML
    private Label Name;
    @FXML
    private ImageView Photo;
    @FXML
    private TextField Amount;
    @FXML
    private TextField Availability;
    @FXML
    public void addToCart(){
        currentuser.cart.addProduct(currentproduct, Integer.parseInt(Amount.getText()));
    }

    DataManager dataManager = DataManager.getInstance();
    public void initialize() {
        productsList = dataManager.shareProductList();
        for (Product product : productsList) {
            if(product.getName().equals(Name.getText())){
                currentproduct = product;
            }
        }
        currentuser = dataManager.getCurrentUser();
    }

    public void setProductData(String name, String description, Double prize,  int availability, String photo){
        Name.setText(name);
        Description.setText(description);
        Prize.setText(Double.toString(prize));
        Availability.setText(Integer.toString(availability));
        Photo.setImage(new javafx.scene.image.Image(photo));
    }
}
