package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.print.attribute.standard.JobStateReason;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instace;
    private ArrayList<User> users;
    private ArrayList<Salesman> salesmen;
    private ObservableList<Product> productList;
    private User currentUser;
    private Salesman currentSalesman;

    String workingDir = System.getProperty("user.dir");  // Pobiera katalog roboczy
    String filePath = workingDir + "/Produkty.csv";  // Tworzy pełną ścieżkę do pliku
    File file = new File(filePath);  // Tworzy obiekt pliku

    private DataManager() {

        users = new ArrayList<>();
        salesmen = new ArrayList<>();
        productList = loadProductsFromCSV();
    }

    public static DataManager getInstance() {
        if (instace == null) {
            instace = new DataManager();
        }
        return instace;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> shareUserList() {
        return users;
    }

    public void addSalesman(Salesman salesman) {
        salesmen.add(salesman);
    }

    public ArrayList<Salesman> shareSalesmanList() {
        return salesmen;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public ObservableList<Product> shareProductList() {
        return productList;
    }

    public void saveProductsToCSV(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (Product product : products) {
                writer.write(product.getName() + ";" + product.getPrice() + ";" + product.getAmount() + ";" + product.getDescription() + ";" + product.getPhoto() + "\n");
            }
            System.out.println("Produkty zapisano do pliku! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Product> loadProductsFromCSV() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(";");
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                int stock = Integer.parseInt(data[2]);
                String description = data[3];
                String photo = data[4];

                products.add(new Product(name, price, stock, description, photo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentSalesman(Salesman salesman) {
        currentSalesman = salesman;
    }
    public Salesman getCurrentSalesman() {
        return currentSalesman;
    }
    public void changeScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(DataManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
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
