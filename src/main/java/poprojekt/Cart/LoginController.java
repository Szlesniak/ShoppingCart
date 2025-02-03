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
import java.util.ArrayList;

public class LoginController {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Salesman> salesmen = new ArrayList<>();
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
                changeScene(event, "strona_zalog_user.fxml");
                wiadomosc("Zalogowano jako: " + user.getLogin());
                return;
            }
        }

        for (Salesman salesman : salesmen) {
            if (salesman.getLogin().equals(username) && salesman.getPassword().equals(password)) {
                changeScene(event, "strona_zalog_salesman.fxml");
                wiadomosc("Zalogowano jako: " + salesman.getLogin());
                return;
            }
        }
        wiadomosc("Niepoprawne dane logowania");
    }

    public void register(ActionEvent event){
        changeScene(event, "rejestracja.fxml");
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
        alert.setTitle("Wiadomość");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}
