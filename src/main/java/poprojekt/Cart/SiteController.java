package poprojekt.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import klasy.DataManager;
import klasy.Product;
import javafx.event.ActionEvent;
import java.io.IOException;



public class SiteController {

    DataManager dataManager = DataManager.getInstance();
    private static ObservableList<Product> productsList = FXCollections.observableArrayList();

    @FXML
    private Button Login;
    @FXML
    private Button Register;
    @FXML
    private VBox contentBox;

    private void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt.fxml"));
            Parent productNode = loader.load();

            SzablonController controller = loader.getController();
            controller.setProductData(product.getName(),product.getDescription(), product.getPrice(), product.getAmount(), product.getPhoto() );

            contentBox.getChildren().add(productNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize() {
        productsList = dataManager.shareProductList();
        contentBox.getChildren().clear(); // Wyczyszczenie starej zawartości
        for (Product product : productsList) {
            addProductToUI(product);
        }
    }

    public void login(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("zaloguj.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void register(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rejestracja.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}