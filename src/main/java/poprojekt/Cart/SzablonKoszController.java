package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import klasy.Cart;
import klasy.DataManager;
import klasy.Product;
import klasy.User;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Iterator;

public class SzablonKoszController {
    private CartController mainController;
    private Parent root;
    User currentuser;
    Cart currentcart;
    Product currentproduct = new Product();
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
            dataManager.wiadomosc("Koszyk jest pusty!");
            return;
        }

        Iterator<Product> iterator = CartProductList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (product.getName().equals(Name.getText())) {
                found = true;
                currentproduct = product;

                if (Delete.getText().isEmpty() || Delete.getText().equals("0")) {
                    dataManager.wiadomosc("Podaj ilość!");
                    return;
                }

                try {
                    amount = Integer.parseInt(Delete.getText());
                } catch (NumberFormatException e) {
                    dataManager.wiadomosc("Błąd: Ilość musi być liczbą całkowitą!");
                    return;
                }

                // Aktualizacja ilości w koszyku
                currentcart.getProds().put(currentproduct, currentcart.getIloscCart(currentproduct) - amount);
                Amount.setText(Integer.toString(currentcart.getIloscCart(currentproduct)));

                dataManager.wiadomosc("Usunięto z koszyka: " + currentproduct.getName() + "\nW ilości: " + amount);

                // Jeśli ilość <= 0, usuń produkt z koszyka i interfejsu
                if (currentcart.getProds().get(currentproduct) <= 0) {
                    iterator.remove(); // Bezpieczne usunięcie z listy
                    currentcart.removeProduct(currentproduct);

                    if (mainController != null && root != null) {
                        mainController.removeTemplate(root);
                    }
                }
            }
        }

        if (!found) {
            dataManager.wiadomosc("Produkt nie został znaleziony w koszyku!");
        }

        mainController.refresh();
        update();
    }


    public void initialize() {
        currentuser = dataManager.getCurrentUser();
        CartProductList = currentuser.cart.getProducts();
        currentcart = currentuser.cart;
        currentproduct = dataManager.setCurrentProduct(currentuser, Name);
        if(currentproduct==null){
            currentproduct = currentcart.getProducts().getFirst();
        }
        update();
    }

    public void setProductData(String name, int amount, Double prize, String photo) {
        Name.setText(name);
        Amount.setText(Integer.toString(amount));
        Prize.setText(Double.toString(prize));
        Photo.setImage(new javafx.scene.image.Image(photo));
    }

    public void update() {
        Name.setText(currentproduct.getName());
        if (currentcart == null){
            currentcart = currentuser.cart;
        }
        Amount.setText(Integer.toString(currentcart.getProds().get(currentproduct)));
        Prize.setText(Double.toString(currentproduct.getPrice()));
}

public void setMainController(CartController mainController) {
    this.mainController = mainController;
}

public void setRoot(Parent root) {
    this.root = root;
}
public void setCurrentuser(User currentuser){
        this.currentuser = currentuser;
        this.currentcart = currentuser.cart;
        this.currentproduct = dataManager.setCurrentProduct(currentuser, Name);
}
}
