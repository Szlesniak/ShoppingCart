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
        for (Product product : cartProducts) {
            prods.put(product,0);
        }
        cartProducts.clear();
    }
    public ObservableList<Product> getProducts() {
        return cartProducts;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartProducts) {
            totalPrice += product.getTotalPrice() * prods.get(product);
        }
        return totalPrice;
    }
    public int getIloscCart(Product prod) {
        return prods.getOrDefault(prod,0);
    }

    public boolean isEmpty() {
        return cartProducts.isEmpty();
    }

    public HashMap<Product, Integer> getProds() {
        return prods;
    }
    public void setCartProducts(Product prod, int amount) {
        prods.put(prod,amount);
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
