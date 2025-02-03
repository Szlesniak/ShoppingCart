package klasy;

import java.util.ArrayList;

public class Cart {
    User user;
    private ArrayList<Product> products = new ArrayList<Product>();
    public void addProduct(Product product) {
        products.add(product);
    }
    public void setUser(User user){
        this.user = user;
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public void clearCart(){
        products.clear();
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int iloscProduktów(){
        int l = 0;
        for (Product product : products) {
            l += product.bought;
        }
        return l;
    }
}
