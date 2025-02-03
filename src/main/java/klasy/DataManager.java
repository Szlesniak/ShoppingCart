package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class DataManager {
    private static DataManager instace;
    private ArrayList<User> users;
    private ArrayList<Salesman> salesmen;
    private ObservableList<Product> productList;

    private DataManager() {
        users = new ArrayList<>();
        salesmen = new ArrayList<>();
        productList = FXCollections.observableArrayList();
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
}
