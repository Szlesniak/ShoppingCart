package klasy;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> products = new ArrayList<Product>();
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
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
}
