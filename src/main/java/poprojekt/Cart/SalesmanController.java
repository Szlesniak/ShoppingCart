package poprojekt.Cart;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import klasy.DataManager;
import klasy.Product;

public class SalesmanController {
    DataManager dataManager = DataManager.getInstance();
    @FXML
    private Stage NowyProduktStage;
    @FXML
    public void onAddProduct() {
        try {
            if (NowyProduktStage == null || !NowyProduktStage.isShowing()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt.fxml"));
                Parent root = fxmlLoader.load();

                ProductController controller = fxmlLoader.getController();
                controller.setSalesmanController(this);

                NowyProduktStage = new Stage();
                NowyProduktStage.setTitle("NowyProdukt");
                NowyProduktStage.setResizable(false);
                NowyProduktStage.setScene(new Scene(root));

                NowyProduktStage.setOnCloseRequest(event -> NowyProduktStage = null);

                NowyProduktStage.show();
            } else {
                NowyProduktStage.requestFocus();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void addProductToList(Product product) {
        dataManager.addProduct(product);
    }
}
