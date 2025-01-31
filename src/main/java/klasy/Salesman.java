package klasy;

public class Salesman extends Dane {
    String name;
    String company;
    String NIP;
    String nr_konta;
    public Salesman(String name, String company, String NIP, String nr_konta, String login, String password, String miasto, String ulica, String nr_bud, String email){
        this.password = password;
        this.login = login;
        this.name = name;
        this.email = email;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_bud = nr_bud;
        this.company = company;
        this.NIP = NIP;
        this.nr_konta = nr_konta;
    }



}
