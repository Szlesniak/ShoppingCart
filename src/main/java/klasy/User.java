package klasy;

public class User extends Dane {
    private String firma;
    private String name;
    private String surname;
    private String phone;
    public User(String password, String login , String firma,String name, String surname, String email, String phone, String miasto, String ulica, String nr_bud) {
        this.password = password;
        this.login = login;
        this.firma = firma;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_bud = nr_bud;
    }
}

