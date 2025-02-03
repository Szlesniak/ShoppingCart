package poprojekt.Cart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class SzablonController {
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
        int amount = Integer.parseInt(Amount.getText());
    }

    public void setProductData(String name, String description, Double prize,  int availability, String photo){
        Name.setText(name);
        Description.setText(description);
        Prize.setText(Double.toString(prize));
        Availability.setText(Integer.toString(availability));
        Photo.setImage(new javafx.scene.image.Image(photo));
    }
}
