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
        notificationService = new NotificationService("ultraakch@.com", "wgva fubp arbg rljf");
        installationManager.setNotificationService(notificationService);
        orderManager = new OrderManager(notificationService);
        productManager = new ProductManager();



        ConsoleUI consoleUI = new ConsoleUI(userManager,productManager,installationManager,reviewManager,orderManager);
        consoleUI.start(); //gg

        }

    }

