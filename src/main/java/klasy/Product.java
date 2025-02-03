package klasy;

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
    public int getSold(){
        return sold;
    }
    public double getSoldPrice(){
        return sold * price;
    }
}
