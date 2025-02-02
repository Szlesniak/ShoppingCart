package poprojekt.Cart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KoszykApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KoszykApplication.class.getResource("strona.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 1024);
        stage.setResizable(false);
        stage.setTitle("Koszyk!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}