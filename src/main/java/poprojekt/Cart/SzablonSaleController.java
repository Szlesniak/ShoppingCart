package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import klasy.DataManager;
import klasy.Product;

public class SzablonSaleController {
    Product currentproduct;
    ObservableList<Product> productsList = FXCollections.observableArrayList();
    @FXML
    private Label Name;
    @FXML
    private TextField Amount;
    @FXML
    private ImageView Photo;
    @FXML
    private TextField Soldamount;
    @FXML
    private TextField Soldprize;
    @FXML
    private TextField Dodaj;
    @FXML
    private TextField Zmien;
    @FXML
    private TextField Cena;

    public void dodaj(){
        for (Product product : productsList) {
            if(product.getName().equals(Name.getText())){
                currentproduct = product;
            }
        }
        currentproduct.setAmount(currentproduct.getAmount()+Integer.parseInt(Dodaj.getText()));
        refresh();
    }
    public void zmien(){
        for (Product product : productsList) {
            if(product.getName().equals(Name.getText())){
                currentproduct = product;
            }
        }
        currentproduct.setPrice(Double.parseDouble(Zmien.getText()));
        refresh();
    }
    public void setProductData(String name,Double cena, int amount, int soldamount,double soldprize, String photo){
        Cena.setText(Double.toString(cena));
        Name.setText(name);
        Soldamount.setText(Integer.toString(soldamount));
        Soldprize.setText(Double.toString(soldprize));
        Amount.setText(Integer.toString(amount));
        String path = getClass().getResource("/"+photo).toExternalForm();
        Photo.setImage(new javafx.scene.image.Image(path));
    }
    DataManager dataManager = DataManager.getInstance();
    public void initialize() {
        productsList = (dataManager.shareProductList());
    }
    public void refresh(){
        for (Product product : productsList) {
            if(product.getName().equals(Name.getText())){
                currentproduct = product;
            }
        }
        Cena.setText(Double.toString(currentproduct.getPrice()));
        Name.setText(currentproduct.getName());
        Amount.setText(Integer.toString(currentproduct.getAmount()));
        Soldamount.setText(Integer.toString(currentproduct.getSold()));
        Soldprize.setText(Double.toString(currentproduct.getSoldPrice()));
    }

}
