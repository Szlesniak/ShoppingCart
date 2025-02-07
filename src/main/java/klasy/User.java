package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

public class User extends Dane {
    private String name;
    private String surname;
    public Cart cart = new Cart();
    private ObservableList<Order> orders;
    public User(String login, String password,String name, String surname, String email, String phone, String miasto, String ulica, String nr_bud) {
        this.password = password;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_bud = nr_bud;
        this.orders = FXCollections.observableArrayList();

    }
    public void setCart(Cart cart){
        this.cart = cart;
    }

    public String getSurname() {return surname;}

    public String getName() {
        return name;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
    public ObservableList<Order> getOrders() {
        return orders;
    }
}

