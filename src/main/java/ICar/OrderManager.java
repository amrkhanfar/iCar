package ICar;

import java.util.ArrayList;
import java.util.Date;

public class OrderManager {
    private ArrayList<Order> orders;
    private NotificationService notificationService;

    public OrderManager(NotificationService notificationService)
    {
        orders = new ArrayList<Order>();
        this.notificationService = notificationService;
    }

    public void placeOrder(int id, Date date, User customer, ArrayList<Product> cart) {
        Order order = new Order(id, date, customer, cart);
        orders.add(order);
        notificationService.sendOrderConfirmationNotification(customer, order);
    }

    public Order getOrderDetails(int orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null; // Order not found.
    }

    public ArrayList<Order> getOrderHistory(User customer) {
        ArrayList<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().getEmail().equals(customer.getEmail())) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }


}




