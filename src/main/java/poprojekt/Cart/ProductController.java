package poprojekt.Cart;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klasy.DataManager;
import klasy.Product;

public class ProductController {
    DataManager dataManager = DataManager.getInstance();
    private SalesmanController SalesmanController;
    @FXML
    private TextField name;
    @FXML
    private TextField prize;
    @FXML
    private TextField amount;
    @FXML
    private TextArea description;
    @FXML
    private TextField photo;
    @FXML
    private Button Cancel;
    @FXML
    private Button AddProduct;

    public void addProduct() {
        int Amount;
        double Prize;
        try {
            Prize = Integer.parseInt(prize.getText());
        } catch (Exception e) {
            dataManager.wiadomosc("Wartosc przez ciebie wpisana nie jest liczbą!");
            return;
        }
        if (prize.getText().isEmpty() || prize.getText() == null || Prize < 1) {
            dataManager.wiadomosc("Wprowadzono nieprawidłową ilość!");
        }
        try {
            Amount = Integer.parseInt(amount.getText());
        } catch (Exception e) {
            dataManager.wiadomosc("Wartosc przez ciebie wpisana nie jest liczbą!");
            return;
        }
        if (amount.getText().isEmpty() || amount.getText() == null || Amount < 1) {
            dataManager.wiadomosc("Wprowadzono nieprawidłową ilość!");
        }
        if (name.getText().isEmpty() || description.getText().isEmpty() || photo.getText().isEmpty()) {
            dataManager.wiadomosc("Wypełnij wszystkie pola!");
        } else if (description.getText().getBytes().length > 300) {
            dataManager.wiadomosc("Opis nie może być dłuższy niż 300 znaków!");
        } else if (ProductController.class.getResource("/" + photo.getText()) == null) {
            dataManager.wiadomosc("Nie ma takiego pliku!");
        } else if (SalesmanController.dataManager.shareProductList().isEmpty()) {
            Product product = new Product(name.getText(), Prize, Amount, description.getText(), photo.getText());
            SalesmanController.addProductToList(product);
            SalesmanController.refresh();
            AddProduct.getScene().getWindow().hide();
            System.out.println("Dodano nowy produkt!");
            dataManager.wiadomosc("Dodano nowy produkt: " + product.getName() + "\nW ilosci: " + product.getAmount() + "\nW cenie: " + product.getPrice() + " zł");
        } else if (checkName(name.getText())) {
            dataManager.wiadomosc("Produkt o takiej nazwie już istnieje!");
        } else {
            Product product2 = new Product(name.getText(), Prize, Amount, description.getText(), photo.getText());
            SalesmanController.addProductToList(product2);
            SalesmanController.refresh();
            AddProduct.getScene().getWindow().hide();
            System.out.println("Dodano nowy produkt!");
            dataManager.wiadomosc("Dodano nowy produkt: " + product2.getName() + "\nW ilosci: " + product2.getAmount() + "\nW cenie: " + product2.getPrice() + " zł");
        }
    }

    public void setSalesmanController(SalesmanController salesmanController) {
        this.SalesmanController = salesmanController;
    }

    public void cancel() {
        Cancel.getScene().getWindow().hide();
        System.out.println("Anulowałeś dodawanie nowego produktu!");
    }

    public boolean checkName(String name) {
        for (Product product : SalesmanController.dataManager.shareProductList()) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
