package klasy;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> cartProducts = new ArrayList<Product>();
    public void addProduct(Product product, int amount) {
        cartProducts.add(product);
        product.setBought(amount);
    }
    public void removeProduct(Product product) {
        cartProducts.remove(product);
    }
    public void clearCart(){
        cartProducts.clear();
    }
    public ArrayList<Product> getProducts() {
        return cartProducts;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartProducts) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }
    public boolean isEmpty() {
        return cartProducts.isEmpty();
    }

    public int iloscProduktów(){
        int l = 0;
        for (Product product : cartProducts) {
            l += product.bought;
        }
        return l;
    }
}
