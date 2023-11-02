package ICar;

import java.awt.*;
import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Category> categories;
    private Category interiorCategory;
    private Category exteriorCategory;
    private Category electronicsCategory;

    public ProductManager(){
        categories = new ArrayList<Category>(); //initializing categories array list until a database is connected

        interiorCategory = new Category("Interior", "Accessories for the car's interior.");
        exteriorCategory = new Category("Exterior", "Accessories for the car's exterior.");
        electronicsCategory = new Category("Electronics", "Car electronic gadgets and accessories.");

        categories.add(interiorCategory);
        categories.add(exteriorCategory);
        categories.add(electronicsCategory);
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
