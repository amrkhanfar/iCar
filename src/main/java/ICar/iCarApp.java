package ICar;

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







    }
}
