package ICar.Menus;

import ICar.OrderManager;
import ICar.ProductManager;
import ICar.User;
import ICar.UserManager;
import ICar.Rank;

import java.util.Scanner;

public class MainMenu {

    UserManager userManager;
    ProductManager productManager;
    OrderManager orderManager;
    User currentUser;
    Scanner scanner;

    public MainMenu(UserManager userManager, ProductManager productManager, OrderManager orderManager, User currentUser, Scanner scanner) {
        this.userManager = userManager;
        this.productManager = productManager;
        this.orderManager = orderManager;
        this.currentUser = currentUser;
        this.scanner = scanner;
    }

    public void displayMenu (){
        switch (currentUser.getRank()) {

            case Rank.USER:
                //
                break;

            case Rank.ADMIN:
                displayAdminMenu();
                break;

            case Rank.INSTALLER:
                //
                break;

            default:
                //
                break;
        }
    }

    private void displayAdminMenu(){
        while (true){
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Categories");
            System.out.println("3. Manage Users");
            System.out.println("4. Schedule Appointments");
            System.out.println("5. Logout");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Implement product management
                    System.out.println("Managing products...");
                    break;
                case 2:
                    // Implement category management
                    System.out.println("Managing categories...");
                    break;
                case 3:
                    // Implement user management
                    System.out.println("Managing users...");
                    break;
                case 4:
                    // Implement appointment scheduling
                    System.out.println("Scheduling appointments...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
