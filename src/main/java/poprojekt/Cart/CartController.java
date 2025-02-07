package poprojekt.Cart;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import klasy.DataManager;
import klasy.Order;
import klasy.Product;
import klasy.User;

import java.util.Iterator;

public class CartController {
    String paymentMethod;
    String deliveryMethod;
    UserController userController;

    @FXML
    private ComboBox<String> platnosc;
    @FXML
    private ComboBox<String> dostawa;
    @FXML
    private TextField prize;
    @FXML
    private VBox contentBox;
    @FXML
    private Button Clear;

    String paymentMethod1 = "Przelew";
    String paymentMethod2 = "Blik";
    String paymentMethod3 = "Gotówka przy odbiorze";
    String deliveryMethod1 = "Kurier";
    String deliveryMethod2 = "Paczkomat";
    String deliveryMethod3 = "Odbiór osobisty";
    User currentuser;
    DataManager dataManager = DataManager.getInstance();

    private static ObservableList<Product> productsList = FXCollections.observableArrayList();
    private static ObservableList<Order> orders = FXCollections.observableArrayList();

    public void initialize() {
        platnosc.getItems().addAll(paymentMethod1, paymentMethod2, paymentMethod3);
        dostawa.getItems().addAll(deliveryMethod1, deliveryMethod2, deliveryMethod3);
        currentuser = dataManager.getCurrentUser();
        productsList = currentuser.cart.getProducts();
        Iterator<Product> iterator = productsList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getAmount() < currentuser.cart.getProds().get(product)) {
                iterator.remove();
                currentuser.cart.getProds().put(product,0);
                currentuser.cart.removeProduct(product);
                dataManager.wiadomosc("Produkt został usunięty, ponieważ jego dostępna ilość była mniejsza niż ilość w koszyku!");
            }
        }
        refresh();
        if (currentuser.cart.getProducts().isEmpty()) {
            Clear.setDisable(true);
            platnosc.setDisable(true);
            dostawa.setDisable(true);
        } else {
            Clear.setDisable(false);
            platnosc.setDisable(false);
            dostawa.setDisable(false);
        }
    }

    private void addProductToUI(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szablon_produkt_koszyk.fxml"));
            Parent productNode = loader.load();

            SzablonKoszController controller = loader.getController();
            controller.setMainController(this);
            controller.setCurrentuser(currentuser);
            controller.setRoot(productNode);

            controller.setProductData(product.getName(),currentuser.cart.getIloscCart(product), product.getPrice(), product.getPhoto());

            contentBox.getChildren().add(productNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void order() {
        setDeliveryMethod();
        setPaymentMethod();
        Order order = new Order(dataManager.shareOrderList().size() + 1, paymentMethod, deliveryMethod);
        orders.add(order);
        dataManager.addOrder(order);
        dataManager.saveOrdersToCSV(orders);

        order.setUser(currentuser);
        String workingDir = System.getProperty("user.dir");
        String PdfPath = workingDir + "/Zamówienie";
        order.createOrderPdf(PdfPath + order.getOrderId() + ".pdf", order);
        dataManager.shareOrderList().add(order);
        order.finalizeOrder();
        dataManager.wiadomosc("Zamówienie złożone!");
        clearcart();
    }

    public void clearcart() {
        if (currentuser.cart.getProducts().isEmpty()) {
            dataManager.wiadomosc("Koszyk jest pusty nie ma czego usuwać!");
            return;
        }
        currentuser.cart.clearCart();
        refresh();
        userController.refresh();
    }

    public void refresh() {
        contentBox.getChildren().clear();
        productsList = currentuser.cart.getProducts();
        for (Product product : productsList) {
            addProductToUI(product);
        }
        prize.setText(Double.toString(currentuser.cart.getTotalPrice()));
    }

    public void removeTemplate(Parent template) {
        Platform.runLater(() -> contentBox.getChildren().remove(template));
    }

    public void setMainController(UserController userController) {
        this.userController = userController;
    }

    public void setPaymentMethod() {
        paymentMethod = platnosc.getSelectionModel().getSelectedItem();
        currentuser.cart.setPaymentMethod(paymentMethod);
    }

    public void setDeliveryMethod() {
        deliveryMethod = dostawa.getSelectionModel().getSelectedItem();
        currentuser.cart.setDeliveryMethod(deliveryMethod);
    }

    public void cancel(ActionEvent event) {
        dataManager.changeScene(event, "/poprojekt/Cart/strona_zalog_user.fxml");
    }
}
