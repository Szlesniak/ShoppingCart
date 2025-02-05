package klasy;

import java.util.ArrayList;

public class Salesman extends Dane {
    String company_name;
    String NIP;
    ArrayList<Product> SalesmanProducts = new ArrayList<Product>();
    ArrayList<Product> BoughtProducts = new ArrayList<Product>();
    public Salesman(String login,String password, String company_name, String NIP, String email, String phone, String miasto, String ulica, String nr_bud){
        this.password = password;
        this.phone = phone;
        this.login = login;
        this.email = email;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_bud = nr_bud;
        this.company_name = company_name;
        this.NIP = NIP;
    }
    public void addProduct(Product product){
        SalesmanProducts.add(product);
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Product> getSalesmanProducts() {
        return SalesmanProducts;
    }
    public int getTotalSold(){
        int total = 0;
        for (Product product : SalesmanProducts){
            total += product.sold;
        }
        return total;
    }
    public double getTotalMoney(){
        double total = 0;
        for (Product product : SalesmanProducts){
            total += product.getTotalPrice();
        }
        return total;
    }
    public String getCompany_name() {
        return company_name;
    }
    public String getNIP(){
        return NIP;
    }
}
