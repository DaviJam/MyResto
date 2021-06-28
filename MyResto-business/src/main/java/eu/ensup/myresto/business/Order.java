package eu.ensup.myresto.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<Product> product;
    private Date order_date;
    private Status status;

    public Order(){
        this.id = 0;
        this.user = new User();
        this.product = new ArrayList<Product>();
        this.order_date = null;
        this.status = Status.ANNULE;
    }

    public Order(int id, User user, List<Product> product, Date order_date, Status status) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.order_date = order_date;
        this.status = status;
    }

    public Order(User user, List<Product> product, Date order_date, Status status) {
        this.user = user;
        this.product = product;
        this.order_date = order_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", order_date='" + order_date + '\'' +
                ", status=" + status +
                '}';
    }
}
