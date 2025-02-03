package poprojekt.Cart;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SzablonSaleController {
    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private ImageView imageView;

    public void setProductData(String name, String price) {
        productName.setText(name);
        productPrice.setText(price);
    }
}
