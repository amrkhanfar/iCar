package ICar;

import java.util.ArrayList;

public class iCarApp {





    private static User currentUser;
    private static boolean isLoggedIn;
    private static ArrayList<Product>  cart;

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
        notificationService = new NotificationService(userManager.getUsers(), "ultraakch@.com", "wgva fubp arbg rljf");
        installationManager.setNotificationService(notificationService);
        orderManager = new OrderManager(notificationService);
        productManager = new ProductManager();


        cart = new ArrayList<Product>();


        ConsoleUI consoleUI = new ConsoleUI(userManager,productManager,installationManager,reviewManager,notificationService,orderManager,cart);
        consoleUI.start();








        }

    }

