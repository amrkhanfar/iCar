package ICar;

import ICar.Menus.LoginRegisterMenu;
import ICar.Menus.MainMenu;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.ArrayList;
import java.util.Scanner;

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


        userManager = new UserManager();
        productManager =  new ProductManager();
        installationManager = new InstallationManager();
        reviewManager = new ReviewManager();
        notificationService = new NotificationService(userManager.getUsers(), "ultraakch@gmail.com", "wgva fubp arbg rljf");
        orderManager = new OrderManager(notificationService);
        cart = new ArrayList<Product>();


        ConsoleUI consoleUI = new ConsoleUI(userManager,productManager,installationManager,reviewManager,notificationService,orderManager,cart);
        consoleUI.start();








        }

    }

