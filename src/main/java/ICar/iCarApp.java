package ICar;

import ICar.Menus.LoginRegisterMenu;
import ICar.Menus.MainMenu;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.ArrayList;
import java.util.Scanner;

public class iCarApp {

    static UserManager userManager;
    static ProductManager productManager;
    static OrderManager orderManager;
    static Category category;



    private static User currentUser;
    private static boolean isLoggedIn;
    private static ArrayList<Product>  cart;
    public static void main(String[] args) {

        userManager = new UserManager();
        productManager =  new ProductManager();
        orderManager = new OrderManager();

        Scanner scanner = new Scanner(System.in);
        cart = new ArrayList<Product>();
        isLoggedIn = false;


        System.out.println("  _  _____           \n" +
                " (_)/ ____|          \n" +
                "  _| |     __ _ _ __ \n" +
                " | | |    / _` | '__|\n" +
                " | | |___| (_| | |   \n" +
                " |_|\\_____\\__,_|_|   \n" +
                "                     \n" +
                "                     ");
        while(true){
            System.out.println("Welcome to iCar.");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    currentUser = LoginRegisterMenu.login(scanner, userManager);
                    MainMenu mainMenu = new MainMenu(userManager,productManager,orderManager,currentUser,scanner);
                    mainMenu.displayMenu();
                    break;
                case 2:
                    LoginRegisterMenu.register(scanner, userManager);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Please select a valid option.");
            }
        }



    }
}
