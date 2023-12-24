package ICar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Order {
    private int orderID;
    private LocalDateTime orderDate;
    private ArrayList<Product> products;
    private User customer;

    public Order(int orderID, LocalDateTime orderDate, User customer, ArrayList<Product> products) {
        orderDate = orderDate;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customer = customer;
        this.products = products;
    }
    public int getProductQuantityInOrder(Product product) {
        return Collections.frequency(products,product);
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
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


