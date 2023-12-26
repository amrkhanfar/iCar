package icar;

public class Product {
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean available;
    private int id;

    public Product(int id, String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = id;

        if (stock != 0)
            available = true;
        else
            available = false;
    }

    public void displayProductDetails(ReviewManager reviewManager) {
        System.out.println("---- Product Details ----");
        System.out.println("     ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Description: " + this.description);
        System.out.println("Price: $" + this.price);
        System.out.println("Available: " + (this.available ? "Yes" : "No"));

        double averageRating = reviewManager.getAverageRatingForProduct(this);
        System.out.println("Average Rating: " + averageRating);
        System.out.println("-------------------------");
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

