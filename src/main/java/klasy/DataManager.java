package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instace;
    private ObservableList<User> users;
    private ObservableList<Salesman> salesmen;
    private ObservableList<Product> productList;
    private User currentUser;
    private Salesman currentSalesman;

    String workingDir = System.getProperty("user.dir");  // Pobiera katalog roboczy
    String ProduktyPath = workingDir + "/Produkty.csv";  // Tworzy pełną ścieżkę do pliku
    File Produkty = new File(ProduktyPath);  // Tworzy obiekt pliku
    String SalesmenPath = workingDir + "/Salesmen.csv";
    File SalesmenFile = new File(SalesmenPath);
    String UsersPath = workingDir + "/Users.csv";
    File Users = new File(UsersPath);

    private DataManager() {

        users = loadUsersFromCSV();
        salesmen = loadSalesmenFromCSV();
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
            writer.write("Nazwa;Cena;Ilosc;Opis;Zdjecie\n");
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

                products.add(new Product(name, price, stock, description, photo));
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
