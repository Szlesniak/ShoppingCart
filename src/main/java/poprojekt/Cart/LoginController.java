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

public class LoginController {
    private ObservableList<User> users;
    private ObservableList<Salesman> salesmen;
    DataManager dataManager = DataManager.getInstance();

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    public void login(ActionEvent event) {
        users = dataManager.shareUserList();
        salesmen = dataManager.shareSalesmanList();
        String username = loginField.getText();
        String password = passwordField.getText();

        for (User user : users) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                dataManager.setCurrentUser(user);
                dataManager.changeScene(event, "/poprojekt/Cart/strona_zalog_user.fxml");
                dataManager.wiadomosc("Zalogowano jako: " + user.getLogin());
                return;
            }
        }
        for (Salesman salesman : salesmen) {
            if (salesman.getLogin().equals(username) && salesman.getPassword().equals(password)) {
                dataManager.setCurrentSalesman(salesman);
                dataManager.changeScene(event, "/poprojekt/Cart/strona_zalog_salesman.fxml");
                dataManager.wiadomosc("Zalogowano jako: " + salesman.getLogin());
                return;
            }
        }
        dataManager.wiadomosc("Niepoprawne dane logowania");
    }

    public void register(ActionEvent event) {
        dataManager.changeScene(event, "/poprojekt/Cart/rejestracja.fxml");
    }

    public void cancel(ActionEvent event) {
        dataManager.changeScene(event, "/poprojekt/Cart/strona.fxml");
    }
}
