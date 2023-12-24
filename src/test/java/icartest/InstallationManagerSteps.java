package icartest;
import ICar.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InstallationManagerSteps {
    NotificationService notificationService;
    ReviewManager reviewManager;
    InstallationManager installationManager;
    UserManager userManager;
    ProductManager productManager;
    OrderManager orderManager;

    Installer installer;
    InstallationRequest installationRequest;
    @Given("there are installers registered in the system")
    public void there_are_installers_registered_in_the_system() {
        userManager.registerUser("Gail Lewis", "gaillewis@walmart.com", "1234556789", Rank.INSTALLER);
        installer = installationManager.getInstallerByName("Gail Lewis");
        assertNotNull(installer);
    }

    @Given("all the required managers are set up")
    public void all_the_required_managers_are_set_up() {
        reviewManager = new ReviewManager();
        installationManager = new InstallationManager(null);
        userManager = new UserManager(installationManager);
        notificationService = new NotificationService(userManager.getUsers(), "ultraakch@.com", "wgva fubp arbg rljf");
        installationManager.setNotificationService(notificationService);
        notificationService.setUsers(userManager.getUsers());
        orderManager = new OrderManager(notificationService);
        productManager = new ProductManager();
    }
    @Given("a customer placed an order with installation request")
    public void a_customer_placed_an_order_with_installation_request() {
        User customer = userManager.registerUser("Customer1", "customer@customer.com", "123456789", Rank.USER);
        assertNotNull(customer);
        Product product = new Product(39, "Test Product", "basic product", 12, 5);
        assertNotNull(product);
        Category category = new Category("General", "General Category");
        assertNotNull(category);
        assertNotNull(productManager.addProduct(product,category));

        ArrayList<Product> tempCart = new ArrayList<Product>();
        tempCart.add(product);

        Order order = orderManager.placeOrder(customer,tempCart);
        assertNotNull(order);

        installationManager.makeInstallationRequest(order, "I will be home tomorrow morning");
    }

    @Given("there are pending installation requests")
    public void there_are_pending_installation_requests() {
        assertFalse(installationManager.getPendingInstallationRequest().isEmpty());
    }
    @When("the admin views the list of pending installation requests")
    public void the_admin_views_the_list_of_pending_installation_requests() {
        for (InstallationRequest installationRequest : installationManager.getPendingInstallationRequest()) {
            System.out.println(installationRequest.getRequestDetails());
        }
    }
    @Then("the admin should see the details of each pending installation request")
    public void the_admin_should_see_the_details_of_each_pending_installation_request() {
    }

    @Given("there is a pending installation request")
    public void there_is_a_pending_installation_request() {
        assertFalse(installationManager.getPendingInstallationRequest().isEmpty());
    }
    @When("an installer is assigned to the installation request")
    public void an_installer_is_assigned_to_the_installation_request() {
        installationRequest = installationManager.getPendingInstallationRequest().get(0);
        assertNotNull(installationRequest);
        installationManager.assignInstallerToRequest(installationRequest,installer, LocalDateTime.now());
    }
    @Then("the installation request should be in the scheduled status")
    public void the_installation_request_should_be_in_the_scheduled_status() {
        assertEquals(installationRequest.getStatus(), InstallationRequest.Status.SCHEDULED);
    }

    @Given("there is a scheduled installation request")
    public void there_is_a_scheduled_installation_request() {
        installationRequest = installationManager.getInstallationRequests().get(0);
        assertNotNull(installationRequest);
        installationManager.assignInstallerToRequest(installationRequest,installer,LocalDateTime.now());
        assertEquals(installationRequest.getStatus(), InstallationRequest.Status.SCHEDULED);
    }
    @When("the installer completes the installation request")
    public void the_installer_completes_the_installation_request() {
        installationManager.completeInstallationRequest(installationRequest);
    }
    @Then("the installation request should be in the completed status")
    public void the_installation_request_should_be_in_the_completed_status() {
        assertEquals(installationRequest.getStatus(), InstallationRequest.Status.COMPLETED);
    }


    @Given("a user places an order for installation")
    public void a_user_places_an_order_for_installation() {
        //already done in the background
    }
    @When("an installation request is created")
    public void an_installation_request_is_created() {
        //already done in the background
    }
    @Then("the installation request should be in the pending status")
    public void the_installation_request_should_be_in_the_pending_status() {
        installationRequest = installationManager.getPendingInstallationRequest().get(0);
        assertNotNull(installationRequest);

        assertEquals(installationRequest.getStatus(), InstallationRequest.Status.PENDING);
    }


}
