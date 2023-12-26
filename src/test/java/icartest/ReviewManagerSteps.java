package icartest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import icar.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewManagerSteps {
    NotificationService notificationService;
    ReviewManager reviewManager;
    InstallationManager installationManager;
    UserManager userManager;
    ProductManager productManager;
    OrderManager orderManager;
    User user;
    Product product;
    Category category;
    InstallationRequest installationRequest;
    Order order;

    @Given("all the mangers for reviewManagerSteps are initialized")
    public void all_the_mangers_for_review_manager_steps_are_initialized() {
        reviewManager = new ReviewManager();
        installationManager = new InstallationManager(null);
        userManager = new UserManager(installationManager);
        notificationService = new NotificationService("ultraakch@.com", "wgva fubp arbg rljf");
        installationManager.setNotificationService(notificationService);
        orderManager = new OrderManager(notificationService);
        productManager = new ProductManager();
    }

    @Given("a category called general exists")
    public void a_category_called_general_exists() {
        category = productManager.addCategory(new Category("General", ""));
        assertNotNull(category);
    }

    @Given("a product {string} with ID {int}")
    public void a_product_with_id(String Name, Integer ID) {
        product = new Product(ID,Name,"",12.0,12);
        assertNotNull(category);
        assertNotNull(productManager.addProduct(product,category));
    }
    @Given("a user {string} with email {string}")
    public void a_user_with_email(String userName, String userEmail) {
        user = userManager.registerUser(userName,userEmail,"123456789",Rank.USER);
        assertNotNull(user);
    }
    @When("Fawwaz Qanazea adds a review with rating {int} and content {string}")
    public void fawwaz_qanazea_adds_a_review_with_rating_and_content(Integer reviewRating, String reviewContent) {
        assertNotNull(product);
        assertNotNull(user);
        reviewManager.addProductReview(user, product,reviewContent, reviewRating);
    }
    @Then("the product {string} should have an average rating of {string}")
    public void the_product_should_have_an_average_rating_of(String productName, String productAverageRating) {
        product = productManager.findProductByName(productName);
        Double expectedAverageRating = Double.parseDouble(productAverageRating);
        Double actualAverageRating = reviewManager.getAverageRatingForProduct(product);

        assertEquals(expectedAverageRating, actualAverageRating);
    }
    @Then("the review should be displayed in the product reviews")
    public void the_review_should_be_displayed_in_the_product_reviews() {
        reviewManager.viewProductReviews(product);
        assertFalse(reviewManager.getProductReviews(product).isEmpty());
    }

    @Given("a customer {string} with email {string}")
    public void a_customer_with_email(String userName, String userEmail) {
        user = userManager.registerUser(userName,userEmail,"123456789",Rank.USER);
        assertNotNull(user);
    }
    @Given("a product {string} with ID {int} that Farah ordered")
    public void a_product_with_id_that_farah_ordered(String productName, Integer ID) {
        Product productToAdd = new Product(ID,productName,"", 555.2, 82);
        product = productManager.addProduct(productToAdd, category);

        ArrayList<Product> cart = new ArrayList<Product>();
        cart.add(product);

        order = orderManager.placeOrder(user,cart);
        assertNotNull(order);
        installationRequest = installationManager.makeInstallationRequest(order, "no notes.");
    }
    @Given("it was scheduled by an admin")
    public void it_was_scheduled_by_an_admin() {
        assertEquals(installationRequest.getStatus(), InstallationRequest.Status.SCHEDULED);
    }
    @Given("an installation request for Farah's order")
    public void an_installation_request_for_farah_s_order() {
        userManager.registerUser("Gail Lewis", "gaillewis@walmart.com", "1234567890", Rank.INSTALLER);
        Installer installer = installationManager.getInstallerByName("Gail Lewis");
        installationManager.assignInstallerToRequest(installationRequest,installationManager.getInstallerByName("Gail Lewis"), LocalDateTime.now());
    }
    @When("Installer Sam adds a review with rating {int} and content {string}")
    public void customer_farah_adds_a_review_with_rating_and_content(Integer rating, String reviewContent) {
        installationRequest.completeRequest();
        Installer installer = installationManager.getInstallerByName("Gail Lewis");
        reviewManager.addInstallationRequestReview(installer.getUserClassProfile(),installationRequest,reviewContent,rating);

    }
    @Then("the installation request should have a review")
    public void the_installation_request_should_have_a_review() {
       InstallationRequest installationRequest1 =  installationManager.checkIfOrderHasInstallationRequest(order);
       assertFalse(reviewManager.getInstallationRequestReviews(installationRequest).isEmpty());
       assertEquals(installationRequest1,installationRequest);
    }
    @Then("the review should be displayed in the installation request reviews")
    public void the_review_should_be_displayed_in_the_installation_request_reviews() {
        ArrayList<Review> reviews = reviewManager.getInstallationRequestReviews(installationRequest);
        for (Review review : reviews) {
            reviewManager.displayReviewDetails(review);
        }
    }

}
