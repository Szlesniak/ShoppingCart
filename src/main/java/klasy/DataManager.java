package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DataManager {
    private static DataManager instace;
    private ObservableList<User> users;
    private ObservableList<Salesman> salesmen;
    private ObservableList<Product> productList;
    private ObservableList<Order> orders;
    private User currentUser;
    private Salesman currentSalesman;
    Product currentproduct;

    String workingDir = System.getProperty("user.dir");
    String ProduktyPath = workingDir + "/Produkty.csv";
    File Produkty = new File(ProduktyPath);
    String SalesmenPath = workingDir + "/Salesmen.csv";
    File SalesmenFile = new File(SalesmenPath);
    String UsersPath = workingDir + "/Users.csv";
    File Users = new File(UsersPath);
    String OrdersPath = workingDir + "/zamówienia.csv";
    File Orders = new File(OrdersPath);

    private DataManager() {

        users = loadUsersFromCSV();
        salesmen = loadSalesmenFromCSV();
        productList = loadProductsFromCSV();
        orders = loadOrdersFromCSV();
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

    public ObservableList<User> shareUserList() {
        return users;
    }

    public void addSalesman(Salesman salesman) {
        salesmen.add(salesman);
    }

    public ObservableList<Salesman> shareSalesmanList() {
        return salesmen;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public ObservableList<Product> shareProductList() {
        return productList;
    }

    public void saveProductsToCSV(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Produkty))) {
            writer.write("Nazwa;Cena;Ilosc;Opis;Zdjecie;Login\n");
            for (Product product : products) {
                writer.write(product.getName() + ";" + product.getPrice() + ";" + product.getAmount() + ";" + product.getDescription() + ";" + product.getPhoto() + ";" + product.getSalesman().getLogin() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Product> loadProductsFromCSV() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Product product;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Produkty, StandardCharsets.UTF_8));

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
                String salesman = data[5];

                product = new Product(name, price, stock, description, photo);
                products.add(product);
                for (Salesman P : salesmen) {
                    if (P.getLogin().equals(salesman)) {
                        product.setSalesman(P);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    public void saveSalesmenToCSV() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(SalesmenFile))){
            writer.write("login,password,company_name,NIP,email,phone,miasto,ulica,nr_bud\n");
            for(Salesman salesman : salesmen){
                writer.write(salesman.getLogin() + ";" + salesman.getPassword() + ";" + salesman.getCompany_name() + ";" + salesman.getNIP() + ";" + salesman.getEmail() + ";" + salesman.getPhone() + ";" + salesman.getMiasto() + ";" + salesman.getUlica() + ";" + salesman.getNr_bud() + "\n");
            }

        } catch (IOException r){
            r.printStackTrace();
        }

    }
    public ObservableList<Salesman> loadSalesmenFromCSV() {
        ObservableList<Salesman> salesm = FXCollections.observableArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SalesmenFile, StandardCharsets.UTF_8));

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(";");
                String login = data[0];
                String password = data[1];
                String company_name = data[2];
                String NIP = data[3];
                String email = data[4];
                String phone = data[5];
                String miasto = data[6];
                String ulica = data[7];
                String nr_bud = data[8];


                salesm.add(new Salesman(login, password, company_name, NIP, email,phone,miasto,ulica,nr_bud));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesm;
    }
    public void saveUsersToCSV() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Users))){
            writer.write("login,password,name,surname,email,phone,miasto,ulica,nr_bud\n");
            for(User user : users){
                writer.write(user.getLogin() + ";" + user.getPassword() + ";" + user.getName() + ";" + user.getSurname() + ";" + user.getEmail() + ";" + user.getPhone() + ";" + user.getMiasto() + ";" + user.getUlica() + ";" + user.getNr_bud() + "\n");
            }

        } catch (IOException r){
            r.printStackTrace();
        }

    }
    public ObservableList<User> loadUsersFromCSV() {
        ObservableList<User> usr = FXCollections.observableArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Users, StandardCharsets.UTF_8));

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(";");
                String login = data[0];
                String password = data[1];
                String name = data[2];
                String surname = data[3];
                String email = data[4];
                String phone = data[5];
                String miasto = data[6];
                String ulica = data[7];
                String nr_bud = data[8];


                usr.add(new User(login, password, name, surname, email,phone,miasto,ulica,nr_bud));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usr;
    }
    public void saveOrdersToCSV(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Orders))) {
            writer.write("Nazwa;Cena;Ilosc;Opis;Zdjecie;Login\n");
            for (Order order : orders) {
                writer.write(String.valueOf(order.getOrderId()) + ";" + order.getUser().getLogin() + ";" + order.paymentMethod + ";" + order.deliveryMethod + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Order> loadOrdersFromCSV() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        Order order;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Orders, StandardCharsets.UTF_8));

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(";");
                int orderId = Integer.parseInt(data[0]);
                String user = data[1];
                String paymentMethod = data[2];
                String deliveryMethod = data[3];

                order = new Order(orderId, paymentMethod, deliveryMethod);
                orders.add(order);

                for (User P : users) {
                    if (P.getLogin().equals(user)) {
                        order.setUser(P);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
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
    public Product setCurrentProduct (User currentuser, Label label) {
        for (Product product : currentuser.cart.getProducts()) {
            if (product.getName().equals(label.getText())) {
                currentproduct = product;
                break;
            }
        }
        return currentproduct;
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public ObservableList<Order> shareOrderList(){
        return orders;
    }
}