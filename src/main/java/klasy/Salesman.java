package klasy;

import java.util.ArrayList;

public class Salesman extends Dane {
    String company_name;
    String NIP;
    ArrayList<Product> SalesmanProducts = new ArrayList<Product>();
    ArrayList<Product> BoughtProducts = new ArrayList<Product>();
    public Salesman(String company_name, String NIP, String login, String password, String miasto, String ulica, String nr_bud, String email){
        this.password = password;
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
    public void aktualizujIlosc(Product product, int ilosc){
        product.setAmount(ilosc);
    }



}
