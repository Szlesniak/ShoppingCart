package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import klasy.DataManager;
import klasy.Product;
import klasy.Salesman;

public class SzablonSaleController {
    Salesman salesman;
    SalesmanController SalesmanController;
    Product currentproduct;
    ObservableList<Product> productsList = FXCollections.observableArrayList();
    int amount;
    double prize;
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

    public void dodaj() {
        for (Product product : productsList) {
            if (product.getName().equals(Name.getText())) {
                currentproduct = product;
            }
        }
        try {
            amount = Integer.parseInt(Dodaj.getText());
        } catch (Exception e) {
            dataManager.wiadomosc("Wartosc przez ciebie wpisana nie jest liczbą!");
            return;
        }
        if (Dodaj.getText().isEmpty() || Dodaj.getText() == null || amount < 1) {
            dataManager.wiadomosc("Wprowadzono nieprawidłową ilość!");
        } else {
            currentproduct.setAmount(currentproduct.getAmount() + amount);
            dataManager.saveProductsToCSV(productsList);
            dataManager.wiadomosc("Dodano: " + Dodaj.getText() + " sztuk\n Produktu: " + currentproduct.getName());
            refresh();
        }
    }

    public void zmien() {
        for (Product product : productsList) {
            if (product.getName().equals(Name.getText())) {
                currentproduct = product;
            }
        }
        try {
            prize = Integer.parseInt(Zmien.getText());
        } catch (Exception e) {
            dataManager.wiadomosc("Wartosc przez ciebie wpisana nie jest liczbą!");
            return;
        }
        if (Zmien.getText().isEmpty() || Zmien.getText() == null || prize < 1) {
            dataManager.wiadomosc("Wprowadzono nieprawidłową ilość!");
        } else {
            currentproduct.setPrice(prize);
            dataManager.saveProductsToCSV(productsList);
            dataManager.wiadomosc("Zmieniono cenę produktu: " + currentproduct.getName() + " na: " + Zmien.getText());
            refresh();
        }
    }

    public void setProductData(String name, Double cena, int amount, int soldamount, double soldprize, String photo) {
        Cena.setText(Double.toString(cena));
        Name.setText(name);
        Soldamount.setText(Integer.toString(soldamount));
        Soldprize.setText(Double.toString(soldprize));
        Amount.setText(Integer.toString(amount));
        String path = getClass().getResource("/" + photo).toExternalForm();
        Photo.setImage(new javafx.scene.image.Image(path));
    }

    DataManager dataManager = DataManager.getInstance();

    public void initialize() {
        productsList = dataManager.shareProductList();
        salesman = dataManager.getCurrentSalesman();
    }

    public void refresh() {
        for (Product product : productsList) {
            if (product.getName().equals(Name.getText())) {
                currentproduct = product;
            }
        }
        Cena.setText(Double.toString(currentproduct.getPrice()));
        Name.setText(currentproduct.getName());
        if (currentproduct.getAmount() == 0) {
            dataManager.wiadomosc("Produkt o nazwie: " + currentproduct.getName() + " się wyprzedał!" + "\nDodaj ilość tego produktu lub go usuń!");
        }
        Amount.setText(Integer.toString(currentproduct.getAmount()));
        Soldamount.setText(Integer.toString(currentproduct.getSold()));
        Soldprize.setText(Double.toString(currentproduct.getSoldPrice()));
    }

    @FXML
    public void Usun() {
        for (Product product : productsList) {
            if (product.getName().equals(Name.getText())) {
                productsList.remove(product);
                salesman.getSalesmanProducts().remove(product);
                dataManager.saveProductsToCSV(productsList);
                dataManager.wiadomosc("Usunięto produkt: " + product.getName());
                SalesmanController.refresh();
                return;
            }
        }
    }
    public void setSalesmanController(SalesmanController salesmanController){
        SalesmanController = salesmanController;
    }
}