package ICar;

import io.cucumber.java.sl.In;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleUI {
    private User currentUser;
    ArrayList<Product>  cart;
    private UserManager userManager;
    private ProductManager productManager;
    private InstallationManager installationManager;
    private ReviewManager reviewManager;
    private  NotificationService notificationService;
    private OrderManager orderManager;
    private Scanner scanner;

    public ConsoleUI(UserManager userManager, ProductManager productManager, InstallationManager installationManager, ReviewManager reviewManager, NotificationService notificationService, OrderManager orderManager, ArrayList<Product> cart) {
        this.userManager = userManager;
        this.productManager =  productManager;
        this.installationManager = installationManager;
        this.reviewManager = reviewManager;
        this.notificationService = notificationService;
        this.orderManager = orderManager;
        scanner = new Scanner(System.in);
        this.cart = cart;
    }

    public void start() {

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    registerUser(Rank.USER);
                    break;
                case 3:
                    System.out.println("Exiting iCar...");
                    return;

                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n\n--- Welcome " + currentUser.getName() + " to iCar ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void loginUser() {
        System.out.print("Enter your email: ");
        String userInputEmail = scanner.next();
        scanner.nextLine();  // Consume the newline character
        userInputEmail = userInputEmail.toLowerCase().trim();

        System.out.print("Enter your password: ");
        String userInputPassword = scanner.next();
        scanner.nextLine();  // Consume the newline character

        currentUser = userManager.authenticateUser(userInputEmail, userInputPassword);

        if (currentUser == null) {
            System.out.print( "\n\n\n\n\n\n\n\n\n\n" + "Email/Password you've entered is wrong" + "\n\n\n\n");
        } else {
            handleLoggedInUser();
        }
    }

    private User registerUser(String rank) {
        String userInputEmail;
        String userInputPassword;
        String userInputName;

        do {
            System.out.print("Enter new email / Enter # to exit: ");
            userInputEmail = scanner.next();
            scanner.nextLine();  // Consume the newline character

            if (userInputEmail.equals("#")) {
                return null;
            }

            if (userManager.getUserByEmail(userInputEmail) != null){
                System.out.println("Email already exists. Please choose another email.\n");
            }
        } while (userManager.getUserByEmail(userInputEmail) != null);

        do {
            System.out.print("Enter password / Enter # to exit: ");
            userInputPassword = scanner.next();


            if (userInputPassword.equals("#")) {
                return null;
            }

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
        User registeredUser = userManager.registerUser(userInputName, userInputEmail, userInputPassword, rank);
        return registeredUser;

    }

    private void handleLoggedInUser() {
        int choice;
        switch (currentUser.getRank()) {
            case Rank.USER:
                while (currentUser != null) {
                    displayUserMenu();
                    choice = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    handleUserMenuChoice(choice);
                }
                break;
            case Rank.ADMIN:
                while (currentUser != null) {
                    displayAdminMenu();
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    handleAdminMenuChoice(choice);
                }
            case Rank.INSTALLER:
                displayInstallerMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character


        }
    }
    private void handleInstallerMenuChoice(int choice) {
        switch (choice) {
            case 1:
                viewInstallationRequestsForInstaller();
                break;
            case 2:
                completeInstallationRequest();
                break;
            case 3:
                System.out.println("Logging out...");
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private void completeInstallationRequest() {
        System.out.print("Enter the ID of the installation request to complete: ");
        int requestId = scanner.nextInt();

        InstallationRequest requestToComplete = installationManager.findInstallationRequestById(requestId);
        if (requestToComplete != null) {
            requestToComplete.completeRequest();
            System.out.println("Installation request marked as completed.");
        } else {
            System.out.println("Invalid installation request ID. Please try again.");
        }
    }

    private void viewInstallationRequestsForInstaller() {
        ArrayList<InstallationRequest> installerRequests = installationManager.getInstallerByName(currentUser.getName()).getAssignedRequests();

        if (installerRequests.isEmpty()) {
            System.out.println("No installation requests available.");
        } else {
            System.out.println("---- Installation Requests ----");
            for (InstallationRequest request : installerRequests) {
                System.out.println(request.getRequestDetails());
                System.out.println("--------------");
            }
        }
    }
    private void displayInstallerMenu() {
        System.out.println("---- Installer Menu ----");
        System.out.println("1. View Installation Requests");
        System.out.println("2. Complete Installation Request");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
    }



    private void displayAdminMenu() {
        System.out.println("---- Admin Menu ----");
        System.out.println("1. Manage Products");
        System.out.println("2. Manage Categories");
        System.out.println("3. Manage Users");
        System.out.println("4. Schedule Appointments");
        System.out.println("5. View Installation Requests");
        System.out.println("6. View Analytics and Reports");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
    }

    private void handleAdminMenuChoice(int choice) {
        switch (choice) {
            case 1:
                manageProducts();
                break;
            case 2:
                manageCategories();
                break;
            case 3:
                manageUsers();
            case 4:
                scheduleAppointments();
                break;
            case 5:
                viewInstallationRequests();
                break;
            case 6:
                //viewAnalyticsAndReports();
                break;
            case 7:
                System.out.println("Logging out...");
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private void viewInstallationRequests() {
        // Get the installation requests from the InstallationManager
        ArrayList<InstallationRequest> installationRequests = installationManager.getInstallationRequests();

        if (installationRequests.isEmpty()) {
            System.out.println("No installation requests available.");
        } else {
            // Separate pending requests from other requests
            ArrayList<InstallationRequest> pendingRequests = new ArrayList<>();
            ArrayList<InstallationRequest> otherRequests = new ArrayList<>();

            for (InstallationRequest request : installationRequests) {
                if (request.getStatus() == InstallationRequest.Status.PENDING) {
                    pendingRequests.add(request);
                } else {
                    otherRequests.add(request);
                }
            }

            // Display pending requests first
            if (!pendingRequests.isEmpty()) {
                System.out.println("---- Pending Installation Requests ----");
                for (InstallationRequest request : pendingRequests) {
                    System.out.println(request.getRequestDetails());
                    System.out.println("--------------");
                }
            }

            // Display other requests
            if (!otherRequests.isEmpty()) {
                System.out.println("---- Other Installation Requests ----");
                for (InstallationRequest request : otherRequests) {
                    System.out.println(request.getRequestDetails());
                    System.out.println("--------------");
                }
            }
        }
    }


    private void scheduleAppointments() {
        System.out.println("---- Schedule Appointments ----");
        ArrayList<InstallationRequest> pendingInstallationRequests = installationManager.getPendingInstallationRequest();

        if (pendingInstallationRequests.isEmpty()) {
            System.out.println("No pending installation requests available for scheduling.");
            return;
        }

        System.out.println("---- Pending Installation Requests ----");
        for (InstallationRequest request : pendingInstallationRequests) {
            System.out.println(request.getRequestDetails());
            System.out.println("--------------");
        }

        System.out.print("Enter the Installation Request ID to schedule an appointment (or 0 to go back): ");
        int requestID = scanner.nextInt();

        if (requestID == 0) {
            System.out.println("Going back to Admin Menu.");
            return;
        }

        InstallationRequest selectedInstallationRequest = installationManager.findInstallationRequestById(requestID);
        if (selectedInstallationRequest == null) {
            System.out.println("Invalid Installation Request ID. Please try again.");
            return;
        }

        System.out.print("Enter the scheduled date and time (yyyy-MM-dd HH:mm): ");
        String scheduledDateTimeString = scanner.next();
        LocalDateTime scheduledDateTime = LocalDateTime.parse(scheduledDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Installer selectedInstaller = null;
        while (selectedInstaller == null) {
            selectedInstaller = selectInstallerForInstallationRequest();

            if (selectedInstaller == null) {
                System.out.println("Installer was not found. (Enter anything to continue/ # to cancel: ");
                String enteredText = scanner.next();

                if (enteredText.equals("#")) {
                    return;
                }
            }
        }

        installationManager.assignInstallerToRequest(selectedInstallationRequest,selectedInstaller);
        selectedInstallationRequest.setScheduledDateTime(scheduledDateTime);
        notificationService.sendInstallationRequestNotification(selectedInstaller,selectedInstallationRequest);


    }

    private Installer selectInstallerForInstallationRequest() {
        System.out.println("---- Installers List ---");
        ArrayList<Installer> list = installationManager.getInstallers();


        for (Installer installer : list) {
            System.out.println("Installer name: " + installer.getName());
            System.out.println("Installer Email: " + installer.getEmail());
            System.out.println("--------");
        }

        System.out.print("Enter the installer name: ");
        String enteredName = scanner.nextLine();

        return installationManager.getInstallerByName(enteredName);

    }

    public void manageUsers() {
        System.out.println("---- Manage Users ----");
        System.out.println("1. View All Users");
        System.out.println("2. Add User");
        System.out.println("3. Edit User");
        System.out.println("4. Remove User");
        System.out.println("5. Go Back");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                viewAllUsers();
                break;
            case 2:
                addUserByAdmin();
                break;
            case 3:
                editUser();
                break;
            case 4:
                removeUser();
                break;
            case 5:
                System.out.println("Returning to the main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private void removeUser() {
        System.out.print("Enter the email of the user to remove: ");
        String userEmail = scanner.nextLine();

        User userToRemove = userManager.getUserByEmail(userEmail);
        if (userToRemove != null) {
            userManager.deleteUser(userToRemove);
            System.out.println("User have been removed successfully");
        } else {
            System.out.println("There is no user linked to the email you entered");
        }
    }

    private void editUser() {

        System.out.println("---- Edit User ----");
        System.out.print("Enter the email of the user to edit: ");
        String userEmail = scanner.nextLine();

        User userToEdit = userManager.getUserByEmail(userEmail);

        if (userToEdit != null) {
            System.out.println("Current User Information:");
            System.out.println("Name: " + userToEdit.getName());
            System.out.println("Email: " + userToEdit.getEmail());
            System.out.println("Role: " + userToEdit.getRank());

            System.out.print("Enter new name (or press Enter to keep current name): ");
            String newName = scanner.nextLine().trim();

            if (!newName.isEmpty()) {
                userToEdit.setName(newName);
            }

            System.out.print("Enter new password (or press Enter to keep current password): ");
            String newPassword = scanner.nextLine().trim();

            if (!newPassword.isEmpty()) {
                userToEdit.setPassword(newPassword);
            }

            System.out.println("User information updated successfully.");
        } else {
            System.out.println("User not found with the provided email.");
        }
    }

    private void addUserByAdmin() {
        System.out.print("Enter the role of the new user (admin/user/installer): ");
        String userToAddRole = scanner.nextLine().trim().toLowerCase();
        if (userToAddRole.equals(Rank.USER) || userToAddRole.equals(Rank.ADMIN))  {
            registerUser(userToAddRole);
        } else if (userToAddRole.equals(Rank.INSTALLER)) {
            User installerToRegister = registerUser(userToAddRole);
            if (installerToRegister != null) {

            }
        } else {
            System.out.println("Invalid input.");
            return;
        }
    }
    private void viewAllUsers() {
        System.out.println("---- View All Users ----");
        ArrayList<User> users = userManager.getUsers();

        for (User user : users) {
            System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail() + ", Role: " + user.getRank());
        }
    }

    public void manageCategories() {
        while (true) {
            System.out.println("---- Manage Categories ----");
            System.out.println("1. Add Category");
            System.out.println("2. Edit Category");
            System.out.println("3. Delete Category");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    editCategory();
                    break;
                case 3:
                    deleteCategory();
                    break;
                case 4:
                    System.out.println("Returning to Admin Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void addCategory() {
        System.out.print("Enter the name of the new category: ");
        String categoryName = scanner.nextLine();

        System.out.print("Enter the description of the new category: ");
        String categoryDescription = scanner.nextLine();

        Category newCategory = new Category(categoryName, categoryDescription);
        productManager.addCategory(newCategory);
        System.out.println("Category added successfully.");
    }

    private void editCategory() {
        System.out.print("Enter the name of the category to edit: ");
        String categoryName = scanner.nextLine();

        Category categoryToEdit = productManager.getCategoryByName(categoryName);

        if (categoryToEdit != null) {
            System.out.print("Enter the new name for the category (or press Enter to keep the current name):  ");
            String newCategoryName = scanner.nextLine();
            if(!newCategoryName.isEmpty()) {
                categoryToEdit.setName(newCategoryName);
            }

            System.out.print("Enter the new description (or press Enter to keep the current description): ");
            String newCategoryDescription = scanner.nextLine();
            if (!newCategoryDescription.isEmpty()) {
                categoryToEdit.setDescription(newCategoryDescription);
            }

            System.out.println("Category edited successfully.");
        } else {
            System.out.println("Category not found.");
        }
    }

    private void deleteCategory() {
        System.out.print("Enter the name of the category to delete: ");
        String categoryName = scanner.nextLine();

        Category categoryToDelete = productManager.getCategoryByName(categoryName);

        if (categoryToDelete != null) {
            boolean isRemoved = productManager.removeCategory(categoryToDelete);

            if (isRemoved) {
                System.out.println("Category deleted successfully.");
            } else {
                System.out.println("Failed to delete the category.");
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    public void manageProducts() {
        while (true) {
            System.out.println("---- Product Management Menu ----");
            System.out.println("1. View All Products");
            System.out.println("2. Add New Product");
            System.out.println("3. Update Product Information");
            System.out.println("4. Remove Product");
            System.out.println("5. Exit Product Management");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 5:
                    System.out.println("Exiting Product Management Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public void removeProduct() {
        System.out.print("Enter the ID of the product to remove: ");
        int enteredProductID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product productToRemove = productManager.findProductById(enteredProductID);

        if (productToRemove != null) {
            Category productCategory = productManager.findCategoryByProduct(productToRemove);

            if (productCategory != null) {
                boolean isRemoved = productCategory.removeProduct(productToRemove);

                if (isRemoved) {
                    System.out.println("Product removed successfully.");
                } else {
                    System.out.println("Failed to remove the product.");
                }
            } else {
                System.out.println("Error: Product category not found.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }


    private void updateProduct() {
        System.out.print("Enter the ID of the product to update: ");
        int enteredProductID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product productToUpdate = productManager.findProductById(enteredProductID);

        if (productToUpdate != null) {
            System.out.println("Current product information:");
            productToUpdate.displayProductDetails();


            System.out.print("Enter new product name (or press Enter to keep the current name): ");
            String newProductName = scanner.nextLine();
            if (!newProductName.isEmpty()) {
                productToUpdate.setName(newProductName);
            }

            System.out.print("Enter new product description (or press Enter to keep the current description): ");
            String newProductDescription = scanner.nextLine();
            if (!newProductDescription.isEmpty()) {
                productToUpdate.setDescription(newProductDescription);
            }

            System.out.print("Enter new product price (or enter 0 to keep the current price): ");
            double newProductPrice = scanner.nextDouble();
            if (newProductPrice != 0) {
                productToUpdate.setPrice(newProductPrice);
            }

            System.out.print("Enter new product stock quantity (or enter 0 to keep the current stock): ");
            int newProductStock = scanner.nextInt();
            if (newProductStock != 0) {
                productToUpdate.setStock(newProductStock);
            }

            System.out.println("Product updated successfully:");
            productToUpdate.displayProductDetails();
        } else {
            System.out.println("Product not found.");
        }
    }


    private void addNewProduct() {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter product description: ");
        String productDescription = scanner.nextLine();

        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();

        System.out.print("Enter product stock quantity: ");
        int productStock = scanner.nextInt();

        Product newProduct = new Product(productName, productDescription, productPrice, productStock);

        System.out.println("Select a category to add the product to:");
        productManager.displayCategories();
        System.out.print("Enter category name: ");
        String categoryName = scanner.next();
        Category selectedCategory = productManager.getCategoryByName(categoryName);

        if (selectedCategory != null) {
            Product addedProduct = productManager.addProduct(newProduct, selectedCategory);
            if (addedProduct != null) {
                System.out.println("Product added successfully to the category: " + selectedCategory.getName());
            } else {
                System.out.println("Product already exists in the category.");
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    private void displayUserMenu() {
        System.out.println("---- User Menu ----");
        System.out.println("1. Browse Products");
        System.out.println("2. View My Cart");
        System.out.println("3. View My Order History");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }

    private void handleUserMenuChoice(int choice) {
        switch (choice) {
            case 1:
                browseProducts();
                break;
            case 2:
                viewCart();
                break;
            case 3:
                viewOrderHistory();
                break;
            case 4:
                logoutUser();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }



    private void browseProducts() {
        System.out.println("---- Product Catalog ----");


        for (Category category : productManager.getCategories()) {
            System.out.println("Category: " + category.getName());
            for (Product product : category.getProducts()) {
                product.displayProductDetails();
            }
        }
        System.out.print("Enter the ID of the product you want to view / 0 to go back: ");
        int selectedProductId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        if (selectedProductId != 0) {
            Product selectedProduct = productManager.findProductById(selectedProductId);

            if (selectedProduct != null) {
                cart.add(selectedProduct);
                System.out.println(selectedProduct.getName() + "has been added to your cart.");
            } else {
                System.out.println("Invalid product ID. Please try again.");
            }
        } else {
            return;
        }
    }

    private void viewCart() {
        System.out.println("---- Cart ----");
        for (Product product : cart) {
            product.displayProductDetails();
        }
    }

    private void viewOrderHistory() {
        ArrayList<Order> customerOrderHistory = orderManager.getOrderHistory(currentUser);

        if(customerOrderHistory.isEmpty()) {
            System.out.println("You have no previous orders.");
        } else {
            System.out.println("--- Previous Orders ---");
            for (Order order : customerOrderHistory) {
                System.out.println("Order ID: " + order.getOrderID());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Total Cost: $" + order.calculateCost());

                InstallationRequest currentOrderInstallationRequest = installationManager.checkIfOrderHasInstallationRequest(order);
                if (currentOrderInstallationRequest != null) {
                    System.out.println("- Installation request for this order -");
                    System.out.println(currentOrderInstallationRequest.getRequestDetails());
                }
                System.out.println("Products:");
                for (Product product : order.getProducts()) {
                    System.out.println("  - " + product.getName() + " | Price: $" + product.getPrice());
                }

                System.out.println("----------------");
            }

            }
        }

    private void logoutUser() {
        currentUser = null;
        System.out.println("Logged out...");
        System.out.println("Thanks for using iCar.");
    }









}
