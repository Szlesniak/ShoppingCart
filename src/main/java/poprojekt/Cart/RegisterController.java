package poprojekt.Cart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import klasy.DataManager;
import klasy.Salesman;
import klasy.User;
import java.io.IOException;


public class RegisterController {
    DataManager dataManager = DataManager.getInstance();
    @FXML
    private TextField userLogin;
    @FXML
    private PasswordField userPasswd;
    @FXML
    private TextField userName;
    @FXML
    private TextField userSurname;
    @FXML
    private TextField userEmail;
    @FXML
    private TextField userPhone;
    @FXML
    private TextField userCity;
    @FXML
    private TextField userRoad;
    @FXML
    private TextField userNr;
    @FXML
    private TextField saleLogin;
    @FXML
    private PasswordField salePasswd;
    @FXML
    private TextField saleCompany;
    @FXML
    private TextField saleNIP;
    @FXML
    private TextField saleEmail;
    @FXML
    private TextField salePhone;
    @FXML
    private TextField saleCity;
    @FXML
    private TextField saleRoad;
    @FXML
    private TextField saleNr;

    public void userRegister(ActionEvent event) {
        String login = userLogin.getText();
        String password = userPasswd.getText();
        String name = userName.getText();
        String surname = userSurname.getText();
        String email = userEmail.getText();
        String phone = userPhone.getText();
        String miasto = userCity.getText();
        String ulica = userRoad.getText();
        String nr_bud = userNr.getText();
        User user = new User(login, password, name, surname, email, phone, miasto, ulica, nr_bud);
        dataManager.addUser(user);
        dataManager.setCurrentUser(user);
        wiadomosc("Zarejestrowano nowe konto!");
        changeScene(event, "zaloguj.fxml");
    }

    public void saleRegister(ActionEvent event) {
        String login = saleLogin.getText();
        String password = salePasswd.getText();
        String company = saleCompany.getText();
        String NIP = saleNIP.getText();
        String email = saleEmail.getText();
        String phone = salePhone.getText();
        String miasto = saleCity.getText();
        String ulica = saleRoad.getText();
        String nr_bud = saleNr.getText();
        Salesman salesman = new Salesman(login, password, company, NIP, email, phone, miasto, ulica, nr_bud);
        dataManager.addSalesman(salesman);
        dataManager.setCurrentSalesman(salesman);
        wiadomosc("Zarejestrowano nowe konto!");
        changeScene(event, "zaloguj.fxml");
    }
    public void cancel(ActionEvent event){
        changeScene(event, "strona.fxml");
    }
    private void changeScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
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
    public void wiadomosc(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wiadomosc");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}
