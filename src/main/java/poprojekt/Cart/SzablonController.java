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
    int availability;
    int amount;
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
    public void addToCart() {
        if (currentuser == null) {
            dataManager.wiadomosc("Nie masz koszyka, zaloguj sie najpierw!");
            return;
        }
        for (Product product : productsList) {
            if (product.getName().equals(Name.getText())) {
                currentproduct = product;
            }
        }
        refresh();
        if (Amount.getText().isEmpty() || Amount.getText() == null || Integer.parseInt(Amount.getText()) < 1) {
            dataManager.wiadomosc("Wprowadzono nieprawidłową ilość!");
        } else {
            amount = Integer.parseInt(Amount.getText());
            if (availability < amount) {
                dataManager.wiadomosc("Brak wystarczającej ilości produktu na magazynie!");
                return;
            }
            if (amount > availability) {
                dataManager.wiadomosc("Za dużo! Nie ma tyle na magazynie!");
                return;
            }
            if (currentuser.cart.getProducts().isEmpty()) {
                currentuser.cart.addProduct(currentproduct, amount);
                dataManager.wiadomosc("Dodano do koszyka: " + currentproduct.getName() + "\nW ilości: " + amount);
                refresh();
            } else {
                for (Product products : currentuser.cart.getProducts()) {
                    if (products.getName().equals(currentproduct.getName())) {
                        currentuser.cart.setCartProducts(currentproduct,currentuser.cart.getIloscCart(currentproduct) + amount);
                        dataManager.wiadomosc("Dodano do koszyka: " + currentproduct.getName() + "\nW ilości: " + amount);
                        refresh();
                        return;
                    } else {
                        currentuser.cart.addProduct(currentproduct, amount);
                        dataManager.wiadomosc("Dodano do koszyka: " + currentproduct.getName() + "\nW ilości: " + amount);
                        refresh();
                        return;
                    }
                }
            }
        }
    }

    DataManager dataManager = DataManager.getInstance();

    public void initialize() {
        productsList = (dataManager.shareProductList());
        currentuser = dataManager.getCurrentUser();
    }

    public void setProductData(String name, String description, Double prize, int availability, String photo) {
        Name.setText(name);
        Description.setText(description);
        Prize.setText(Double.toString(prize));
        Availability.setText(Integer.toString(availability));
        Photo.setImage(new javafx.scene.image.Image(photo));
        refresh();
    }

    public void refresh() {
        for (Product product : productsList) {
            if (product.getName().equals(Name.getText())) {
                currentproduct = product;
            }
        }
        if (currentuser == null) {
            availability = currentproduct.getAmount();
        } else {
            Availability.setText(Integer.toString(currentproduct.getAmount() - currentuser.cart.getIloscCart(currentproduct)));
        }
        availability = Integer.parseInt(Availability.getText());
    }
}
