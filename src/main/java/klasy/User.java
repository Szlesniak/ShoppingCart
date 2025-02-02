package klasy;

public class User extends Dane {
    private String name;
    private String surname;
    public Cart cart;
    public User(String password, String login,String name, String surname, String email, String phone, String miasto, String ulica, String nr_bud) {
        this.password = password;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_bud = nr_bud;
    }
    public void setCart(Cart cart){
        this.cart = cart;

    }
}

