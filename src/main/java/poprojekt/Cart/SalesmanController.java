package poprojekt.Cart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import klasy.DataManager;
import klasy.Product;

import java.io.IOException;

import klasy.Salesman;;

public class SalesmanController {
    Salesman salesman;
    DataManager dataManager = DataManager.getInstance();
    @FXML
    private Stage NowyProduktStage;
    @FXML
    private TextField username;
    @FXML
    private TextField solld;
    @FXML
    private TextField money;
    @FXML
    private TextField unique;
    @FXML
    private VBox contentBox;

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
        salesman.addProduct(product);
        refresh();
        dataManager.saveProductsToCSV(dataManager.shareProductList());
    }

    public void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt_salesman.fxml"));
            Parent productNode = loader.load();

            SzablonSaleController controller = loader.getController();

            controller.setProductData(product.getName(), product.getPrice(), product.getAmount(), product.getSold(), product.getSoldPrice(), product.getPhoto());
            contentBox.getChildren().add(productNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event) {
        dataManager.setCurrentUser(null);
        dataManager.changeScene(event, "/poprojekt/Cart/strona.fxml");
        dataManager.wiadomosc("Wylogowano");
    }

    public void initialize() {
        username.setText(dataManager.getCurrentSalesman().getCompany_name());
        salesman = dataManager.getCurrentSalesman();
        refresh();
    }

    public void refresh() {
        solld.setText(Double.toString(salesman.getTotalSold()));
        money.setText(Double.toString(salesman.getTotalMoney()));
        unique.setText(Integer.toString(salesman.getSalesmanProducts().size()));
        contentBox.getChildren().clear();
        for (Product product : salesman.getSalesmanProducts()) {
            addProductToUI(product);
        }
    }
}
