package icar;

import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Category> categories;


    public ProductManager(){
        categories = new ArrayList<Category>(); //initializing categories array list until a database is connected

//        interiorCategory = new Category("Interior", "Accessories for the car's interior.");
//        exteriorCategory = new Category("Exterior", "Accessories for the car's exterior.");
//        electronicsCategory = new Category("Electronics", "Car electronic gadgets and accessories.");

//        categories.add(interiorCategory);
//        categories.add(exteriorCategory);
//        categories.add(electronicsCategory);
    }

    public Product addProduct (Product product, Category category) {              //needs to be edited when database is linked.
        if (category.getProducts().contains(product)){
            return null;  //already exists
        }

        category.addProduct(product);
        return product; //added successfully
    }

    public boolean removeProductFromCategory (Product product, Category category){
        boolean isRemoved = category.removeProduct(product);
        return isRemoved;
    }

    public Category findCategoryByProduct(Product product) {
        for (Category category : categories) {
            if (category.getProducts().contains(product)) {
                return category;
            }
        }
        return null; // Category not found
    }

    public Product findProductById(int productId) {
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getId() == productId) {
                    return product;
                }
            }
        }
        return null; // Product not found
    }

    public Product findProductByName(String productName) {
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getName().trim().toLowerCase().equals(productName.trim().toLowerCase())) {
                    return product;
                }
            }
        }
        return null; // Product not found
    }

    public Category addCategory(Category category){
        categories.add(category);
        return category;
    }

    public void displayCategories(){
        System.out.println("--- Categories ---");
        for (Category category : categories) {
            System.out.println("- "+ category.getName());
        }
    }

    public Category getCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.getName().trim().toLowerCase().equals(categoryName.trim().toLowerCase())) {
                return category;
            }
        }
        return null;
    }

    public boolean removeCategory(Category categoryToRemove) {
        return categories.remove(categoryToRemove);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
