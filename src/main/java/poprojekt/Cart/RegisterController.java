package poprojekt.Cart;

import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.stream.Stream;


public class RegisterController {
    private boolean matchloginuser = false;
    private boolean matchloginsale = false;
    private ObservableList<User> users;
    private ObservableList<Salesman> salesmen;
    DataManager dataManager = DataManager.getInstance();
    @FXML
    private TextField userLogin;
    @FXML
    private PasswordField userPasswd;
    @FXML
    private PasswordField userPasswdRep;
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
    private PasswordField salePasswdRep;
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

    public void initialize() {
        users = dataManager.shareUserList();
        salesmen = dataManager.shareSalesmanList();
    }

    public void userRegister(ActionEvent event) {
        String login = userLogin.getText();
        String password = userPasswd.getText();
        String passwordrep = userPasswdRep.getText();
        String name = userName.getText();
        String surname = userSurname.getText();
        String email = userEmail.getText();
        String phone = userPhone.getText();
        String miasto = userCity.getText();
        String ulica = userRoad.getText();
        String nr_bud = userNr.getText();
        for (Salesman salesman : salesmen) {
            if (salesman.getLogin().equals(login)) {
                wiadomosc("Login zajęty!");
                matchloginsale = true;
                return;
            } else {
                matchloginsale = false;
            }
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                wiadomosc("Login zajęty!");
                matchloginuser = true;
                return;
            } else {
                matchloginuser = false;
            }
        }
        if (matchloginuser || matchloginsale) {
            wiadomosc("Login zajęty!");
        } else if (!password.equals(passwordrep)) {
            wiadomosc("Hasła nie są takie same!");
        } else if (Stream.of(login, password, name, surname, email, phone, miasto, ulica, nr_bud).anyMatch(String::isEmpty)) {
            wiadomosc("Proszę wypełnić wszystkie pola!");
        } else {
            User user = new User(login, password, name, surname, email, phone, miasto, ulica, nr_bud);
            dataManager.addUser(user);
            dataManager.saveUsersToCSV();
            dataManager.setCurrentUser(user);
            wiadomosc("Zarejestrowano nowe konto!");
            changeScene(event, "zaloguj.fxml");
        }
    }

    public void saleRegister(ActionEvent event) {
        String login = saleLogin.getText();
        String password = salePasswd.getText();
        String passwordrep = salePasswdRep.getText();
        String company = saleCompany.getText();
        String NIP = saleNIP.getText();
        String email = saleEmail.getText();
        String phone = salePhone.getText();
        String miasto = saleCity.getText();
        String ulica = saleRoad.getText();
        String nr_bud = saleNr.getText();
        for (Salesman salesman : salesmen) {
            if (salesman.getLogin().equals(login)) {
                wiadomosc("Login zajęty!");
                matchloginsale = true;
                return;
            } else {
                matchloginsale = false;
            }
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                wiadomosc("Login zajęty!");
                matchloginuser = true;
                return;
            } else {
                matchloginuser = false;
            }
        }
        if (matchloginuser || matchloginsale) {
            wiadomosc("Login zajęty!");
        } else if (!password.equals(passwordrep)) {
            wiadomosc("Hasła nie są takie same!");
        } else if (Stream.of(login, password, company, NIP, email, phone, miasto, ulica, nr_bud).anyMatch(String::isEmpty)) {
            wiadomosc("Proszę wypełnić wszystkie pola!");
        } else {
            Salesman salesman = new Salesman(login, password, company, NIP, email, phone, miasto, ulica, nr_bud);
            dataManager.addSalesman(salesman);
            dataManager.saveSalesmenToCSV();
            dataManager.setCurrentSalesman(salesman);
            wiadomosc("Zarejestrowano nowe konto!");
            changeScene(event, "zaloguj.fxml");
        }
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
