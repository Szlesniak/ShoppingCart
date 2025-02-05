package poprojekt.Cart;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import klasy.DataManager;
import klasy.Salesman;
import klasy.User;

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
                dataManager.wiadomosc("Login zajęty!");
                matchloginsale = true;
                return;
            } else {
                matchloginsale = false;
            }
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                dataManager.wiadomosc("Login zajęty!");
                matchloginuser = true;
                return;
            } else {
                matchloginuser = false;
            }
        }
        if (matchloginuser || matchloginsale) {
            dataManager.wiadomosc("Login zajęty!");
        } else if (!password.equals(passwordrep)) {
            dataManager.wiadomosc("Hasła nie są takie same!");
        } else if (Stream.of(login, password, name, surname, email, phone, miasto, ulica, nr_bud).anyMatch(String::isEmpty)) {
            dataManager.wiadomosc("Proszę wypełnić wszystkie pola!");
        } else {
            User user = new User(login, password, name, surname, email, phone, miasto, ulica, nr_bud);
            dataManager.addUser(user);
            dataManager.saveUsersToCSV();
            dataManager.setCurrentUser(user);
            dataManager.wiadomosc("Zarejestrowano nowe konto!");
            dataManager.changeScene(event, "/poprojekt/Cart/zaloguj.fxml");
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
                dataManager.wiadomosc("Login zajęty!");
                matchloginsale = true;
                return;
            } else {
                matchloginsale = false;
            }
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                dataManager.wiadomosc("Login zajęty!");
                matchloginuser = true;
                return;
            } else {
                matchloginuser = false;
            }
        }
        if (matchloginuser || matchloginsale) {
            dataManager.wiadomosc("Login zajęty!");
        } else if (!password.equals(passwordrep)) {
            dataManager.wiadomosc("Hasła nie są takie same!");
        } else if (Stream.of(login, password, company, NIP, email, phone, miasto, ulica, nr_bud).anyMatch(String::isEmpty)) {
            dataManager.wiadomosc("Proszę wypełnić wszystkie pola!");
        } else {
            Salesman salesman = new Salesman(login, password, company, NIP, email, phone, miasto, ulica, nr_bud);
            dataManager.addSalesman(salesman);
            dataManager.saveSalesmenToCSV();
            dataManager.setCurrentSalesman(salesman);
            dataManager.wiadomosc("Zarejestrowano nowe konto!");
            dataManager.changeScene(event, "/poprojekt/Cart/zaloguj.fxml");
        }
    }

    public void cancel(ActionEvent event) {
        dataManager.changeScene(event, "/poprojekt/Cart/strona.fxml");
    }
}
