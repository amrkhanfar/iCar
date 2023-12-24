package ICar;

import java.time.LocalDateTime;
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

    public Order placeOrder(User customer, ArrayList<Product> cart) {
        if (cart.isEmpty()) {
            return null;
        }

        ArrayList<Product> orderProducts = new ArrayList<Product>(cart);  //creating a clone
        Order order = new Order(RandomIDGenerator.generateUniqueId(), LocalDateTime.now(), customer, cart);
        orders.add(order);
        notificationService.sendOrderConfirmationNotification(customer, order);
        cart.clear();
        return order;
    }

    public Order getOrderByID(int orderID) {
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




