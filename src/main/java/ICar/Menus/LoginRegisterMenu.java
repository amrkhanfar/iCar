package ICar.Menus;

import ICar.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginRegisterMenu {



    public static User login(Scanner scanner, UserManager userManager) {


        System.out.print("Enter your email: ");
        String userInputEmail = scanner.next();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter your password: ");
        String userInputPassword = scanner.next();
        scanner.nextLine();  // Consume the newline character


        User currentUser = userManager.authenticateUser(userInputEmail.toLowerCase().trim(), userInputPassword);

        if (currentUser == null){
            System.out.print( "\n\n\n\n\n\n\n\n\n\n" + "Email/Password you've entered is wrong" + "\n\n\n\n");
            return null;
        } else {
            System.out.println("\n\n\nWelcome " + currentUser.getName() + "!");

            if (!currentUser.getRank().equals(Rank.USER)) {
                System.out.println("Your role: " + currentUser.getRank() + "\n\n");
            }
            return currentUser;
        }
    }

    public static User register(Scanner scanner, UserManager userManager){

        String userInputEmail;
        String userInputPassword;
        String userInputName;

        do {
            System.out.print("Enter your new email: ");
            userInputEmail = scanner.next();
            scanner.nextLine();  // Consume the newline character

            if (userManager.getUserByEmail(userInputEmail) != null){
                System.out.println("Email already exists. Please choose another email.\n");
            }
        } while (userManager.getUserByEmail(userInputEmail) != null);

        do {
            System.out.print("Enter your password: ");
            userInputPassword = scanner.next();
            scanner.nextLine();  // Consume the newline character

            if (userInputPassword.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.\n");
            }
        } while (userInputPassword.length() < 8);

        do {
            System.out.print("Enter your full name: ");
            userInputName= scanner.next();
            scanner.nextLine();  // Consume the newline character

            if (userInputName.length() < 2) {
                System.out.println("Name has to be more than 1 characters. Please choose another name.\n");
            }
        } while (userInputName.length() < 2);

        System.out.println("Your account have been registered successfully.\n\n");
        return userManager.registerUser(userInputName, userInputEmail, userInputPassword, Rank.USER);

    }
}
