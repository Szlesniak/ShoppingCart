package klasy;

public class Product {
    public String name;
    private double price;
    private int amount = 0;
    private String description;
    private String photo;
    private Salesman salesman;
    public int sold = 0;
    public Product(String name, double price, int amount, String description, String photo) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.photo = photo;
    }
    public Product(){};
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(Double newprice){
        price = newprice;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int newAmount){
        amount = newAmount;
    }
    public double getTotalPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getPhoto() {
        return photo;
    }
    public void decreaseStock(int amount) {
        if (amount > 0 && amount <= this.amount) {
            this.amount -= amount;
        } else {
            System.out.println("Brak wystarczającej ilości produktu w Koszyku!");
        }
    }
    public void setSalesman(Salesman salesman){
        this.salesman = salesman;
        if (!salesman.getSalesmanProducts().contains(this)){
            salesman.addProduct(this);
        }
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public int getSold(){
        return sold;
    }
    public double getSoldPrice(){
        return sold * price;
    }

    public void setSold(int amount){
        this.sold = this.sold + amount;
    }
}
