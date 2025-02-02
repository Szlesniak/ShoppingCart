package klasy;

public class Order {
    private User user;
    private String orderMethod;
    private String paymentMethod;
    private String status;
    private String date;

    public Order(User user, String orderMethod, String paymentMethod, String status, String date) {
        this.user = user;
        this.orderMethod = orderMethod;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.date = date;
    }

}
