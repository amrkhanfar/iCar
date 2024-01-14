package icar;

import java.util.ArrayList;

public class iCarApp {
    static UserManager userManager;
    static ProductManager productManager;
    static InstallationManager installationManager;
    static ReviewManager reviewManager;
    static NotificationService notificationService;
    static OrderManager orderManager;


    public static void main(String[] args) {

        reviewManager = new ReviewManager();
        installationManager = new InstallationManager(null);
        userManager = new UserManager(installationManager);
        notificationService = new NotificationService("ultraakch@g.com", "");
        installationManager.setNotificationService(notificationService);
        orderManager = new OrderManager(notificationService);
        productManager = new ProductManager();

        // Register Users
        userManager.registerUser("Nidal Qaffaf", "nidal@gmail.com", "nidal123456", Rank.INSTALLER);
        userManager.registerUser("Amr Khanfar", "amr@gmail.com", "amr123456", Rank.ADMIN);
        User reviewer1 = userManager.registerUser("Diana Shweki", "diana@gmail.com", "diana123456", Rank.USER);
        User reviewer2 = userManager.registerUser("Young Sam", "sam@gmail.com", "sam123456", Rank.USER);

        // Add Categories
        Category electronicsCategory = new Category("Electronics", "Car electronic gadgets and accessories");
        Category interiorCategory = new Category("Interior", "Accessories for the car's interior");
        Category exteriorCategory = new Category("Exterior", "Accessories for the car's exterior");
        productManager.addCategory(electronicsCategory);
        productManager.addCategory(interiorCategory);
        productManager.addCategory(exteriorCategory);

        // Add Products
        Product gpsTracker = new Product(420,"GPS Tracker", "Real-time car tracking device", 49.99, 10);
        Product seatCover = new Product(147,"Leather Seat Cover", "Premium leather seat cover", 79.99, 20);

        productManager.addProduct(gpsTracker, electronicsCategory);
        productManager.addProduct(seatCover, interiorCategory);

        // Add Reviews
        reviewManager.addProductReview(reviewer1, gpsTracker, "Great product!", 5);
        reviewManager.addProductReview(reviewer1, seatCover, "Didn't like the material nor the color!", 2);
        reviewManager.addProductReview(reviewer2, gpsTracker, "A bit laggy but it does the job!", 4);
        reviewManager.addProductReview(reviewer2, seatCover, "Underrated!, i love it.", 5);

        // Place Order and Add Installation Request
        ArrayList<Product> cart = new ArrayList<>();
        cart.add(gpsTracker);
        cart.add(seatCover);

        Order order = orderManager.placeOrder(reviewer1, cart);
        installationManager.makeInstallationRequest(order,"Will be free this wednesday");


        ConsoleUI consoleUI = new ConsoleUI(userManager,productManager,installationManager,reviewManager,orderManager);
        consoleUI.start(); //gg

        }

    }

