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
        if (name.getText().isEmpty() || prize.getText().isEmpty() || amount.getText().isEmpty() || description.getText().isEmpty() || photo.getText().isEmpty()) {
            dataManager.wiadomosc("Wypełnij wszystkie pola!");
        } else if (Double.parseDouble(prize.getText()) < 0 || Integer.parseInt(amount.getText()) < 0) {
            dataManager.wiadomosc("Cena i ilość nie mogą być ujemne!");
        } else if (Double.parseDouble(prize.getText()) == 0 || Integer.parseInt(amount.getText()) == 0) {
            dataManager.wiadomosc("Cena i ilość nie mogą być równe 0!");
        } else if (description.getText().getBytes().length > 300) {
            dataManager.wiadomosc("Opis nie może być dłuższy niż 300 znaków!");
        } else if (ProductController.class.getResource("/" + photo.getText()) == null) {
            dataManager.wiadomosc("Nie ma takiego pliku!");
        } else if (SalesmanController.dataManager.shareProductList().isEmpty()) {
            Product product = new Product(name.getText(), Double.parseDouble(prize.getText()), Integer.parseInt(amount.getText()), description.getText(), photo.getText());
            SalesmanController.addProductToList(product);
            SalesmanController.refresh();
            AddProduct.getScene().getWindow().hide();
            System.out.println("Dodano nowy produkt!");
            dataManager.wiadomosc("Dodano nowy produkt: " + product.getName() + "\nW ilosci: " + product.getAmount() + "\nW cenie: " + product.getPrice() + " zł");
        } else if (checkName(name.getText())) {
            dataManager.wiadomosc("Produkt o takiej nazwie już istnieje!");
        } else {
            Product product2 = new Product(name.getText(), Double.parseDouble(prize.getText()), Integer.parseInt(amount.getText()), description.getText(), photo.getText());
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
