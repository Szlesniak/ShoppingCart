package klasy;

public class Product {
    private int id;
    public String name;
    private double price;
    private int amount = 0;
    public Product(int id, String name, double price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
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
    public int getAmount() {
        return amount;
    }
    public double getTotalPrice() {
        return price * amount;
    }
    public void addAmount(int amount) {
        this.amount += amount;
    }
    public void subAmount(int amount) {
        this.amount -= amount;
    }
}
