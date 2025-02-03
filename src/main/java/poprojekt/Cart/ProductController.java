package poprojekt.Cart;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klasy.Product;

public class ProductController {
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

    public void addProduct(){
        Product product = new Product(name.getText(), Double.parseDouble(prize.getText()), Integer.parseInt(amount.getText()), description.getText(), photo.getText());
        SalesmanController.addProductToList(product);
        AddProduct.getScene().getWindow().hide();
        System.out.println("Dodano nowy produkt!");
    }
    public void setSalesmanController(SalesmanController salesmanController){
     this.SalesmanController = salesmanController;
    }
    public void cancel(){
        Cancel.getScene().getWindow().hide();
        System.out.println("Anulowałeś dodawanie nowego produktu!");
    }

}
