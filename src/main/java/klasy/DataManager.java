package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    private DataManager() {

        users = new ArrayList<>();
        salesmen = new ArrayList<>();
        productList = loadProductsFromCSV("/Produkty.csv");
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

    public void saveProductsToCSV(List<Product> products, String resourcePath) {
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resourcePath, true))) {
            for (Product product : products) {
                writer.write(product.getName() + ";" + product.getPrice() + ";" + product.getAmount() + ";" + product.getDescription() + ";" + product.getPhoto());
            }
            System.out.println("Produkty zapisano do pliku: " + resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Product> loadProductsFromCSV(String resourcePath) {
        ObservableList<Product> products = FXCollections.observableArrayList();

        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Nie znaleziono zasobu: " + resourcePath);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

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
}
