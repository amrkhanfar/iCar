package ICar;

import java.awt.*;
import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Category> categories;

    public ProductManager(){
        categories = new ArrayList<Category>(); //initializing categories array list until a database is connected

        Category interiorCategory = new Category("Interior", "Accessories for the car's interior.");
        Category exteriorCategory = new Category("Exterior", "Accessories for the car's exterior.");
        Category electronicsCategory = new Category("Electronics", "Car electronic gadgets and accessories.");
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

    public Category addCategory(Category category){
        categories.add(category);
        return category;
    }



}
