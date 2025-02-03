package klasy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Product {
    private int id;
    public String name;
    private double price;
    private int amount = 0;
    private String description;
    private String photo;
    public int bought = 0;
    public int sold = 0;
    public Product(String name, double price, int amount, String description, String photo) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.photo = photo;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(int newprice){
        price = newprice;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int newAmount){
        amount = newAmount;
    }
    public double getTotalPrice() {
        return price * bought;
    }
    public void addAmount(int amount) {
        this.amount += amount;
    }
    public void subAmount(int amount) {
        this.amount -= amount;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public String getPhoto() {
        return photo;
    }
    public void setBought(int newamount){
        bought = newamount;

    }
    public void decreaseStock(int amount) {
        if (amount > 0 && amount <= this.amount) {
            this.amount -= amount;
        } else {
            System.out.println("Błąd: Brak wystarczającej ilości produktu w magazynie!");
        }
    }
    public static void saveProductsToCSV(List<Product> products, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nazwa,Cena,StanMagazynowy\n");
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getAmount() + "\n");
            }
            System.out.println("Produkty zapisano do pliku: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getBought(){
        return bought;
    }
    public int getSold(){
        return sold;
    }
    public double getSoldPrice(){
        return sold * price;
    }
}
