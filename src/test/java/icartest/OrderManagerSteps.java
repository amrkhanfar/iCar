package icartest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import ICar.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class OrderManagerSteps {

    private UserManager userManager;
    private ProductManager productManager;
    private OrderManager orderManager;
    private InstallationManager installationManager;
    private NotificationService notificationService;
    private User customer;
    private ArrayList<Product> cart;


    @Given("all the mangers are initialized")
    public void all_the_mangers_are_initialized() {
        installationManager = new InstallationManager(notificationService);
        userManager = new UserManager(installationManager);
        productManager = new ProductManager();
        notificationService = new NotificationService(userManager.getUsers(), "ultraakch@.com", "wgva fubp arbg rljf");
        orderManager = new OrderManager(notificationService);
        cart = new ArrayList<Product>();
    }
    @Given("a general category exists")
    public void a_general_category_exists() {
        productManager.addCategory(new Category("General", ""));
    }
    @Given("the customer {string} with email {string} is registered")
    public void the_customer_with_email_is_registered(String name, String email) {
        customer = userManager.registerUser(name,email,"123456789",Rank.USER);
        assertNotNull(customer);
    }
    @Given("the following products are available:")
    public void the_following_products_are_available(DataTable dataTable) {

        List<Map<String, String>> productsData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String>  productData : productsData) {
            String productName = productData.get("Product Name");
            double productPrice = Double.parseDouble(productData.get("Price"));

            Product product = new Product(1, productName,"", productPrice, 12);
            Category category = productManager.getCategoryByName("General");
            productManager.addProduct(product,category);
        }
    }
    @When("Sam Hamadneh adds the following products to the cart:")
    public void john_doe_adds_the_following_products_to_the_cart(DataTable dataTable) {
        assertNotNull(customer);
        List<Map<String, String>> productsData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String>  productData : productsData) {
            String productName = productData.get("Product Name");
            int quantity = Integer.parseInt(productData.get("Quantity"));
            for (int i = 0 ; i < quantity ; i++) {
                Product product = productManager.findProductByName(productName);
                assertNotNull(product);
                cart.add(product);
            }
        }
    }
    @When("Sam Hamadneh places the order")
    public void john_doe_places_the_order() {
        assertNotNull(customer);
        orderManager.placeOrder(customer,cart);
    }
    @Then("an order confirmation email is sent to Sam Hamadneh")
    public void an_order_confirmation_email_is_sent_to_john_doe() {
        ArrayList<Order> orderHistory = orderManager.getOrderHistory(customer);
        assertFalse(orderHistory.isEmpty());

        Order order = orderManager.getOrderByID(orderManager.getOrderHistory(customer).get(0).getOrderID());
        assertNotNull(order);
    }




}

