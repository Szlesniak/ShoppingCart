package klasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class Cart {
    private ObservableList<Product> cartProducts = FXCollections.observableArrayList();
    private String paymentMethod;
    private String deliveryMethod;
    private HashMap<Product, Integer> prods = new HashMap<>();
    public void addProduct(Product product, int amount) {
        cartProducts.add(product);
        prods.put(product, amount);
    }
    public void removeProduct(Product product) {
        cartProducts.remove(product);
    }
    public void clearCart(){
        cartProducts.clear();
    }
    public ObservableList<Product> getProducts() {
        return cartProducts;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartProducts) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }
    public HashMap<Product, Integer> getProds() {
        return prods;
    }
    public boolean isEmpty() {
        return cartProducts.isEmpty();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
