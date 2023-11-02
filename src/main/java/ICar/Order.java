package ICar;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderID;
    private Date orderDate;
    private ArrayList<Product> products;
    private User customer;

    public Order(int orderID, Date orderDate, User customer, ArrayList<Product> products) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customer = customer;
        this.products = products;
    }

    public double calculateCost() {
        double cost = 0.0;
        for (Product product : products){
            cost += product.getPrice();
        }
        return cost;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}


