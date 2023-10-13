package ICar;

import java.util.ArrayList;

public class Category {

    private String name;
    private String description;
    private ArrayList<Product> products;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<Product>();
    }

    public void addProduct (Product product){
        this.products.add(product);
    }


    public boolean removeProduct (Product product){
        boolean isRemoved = products.remove(product);
        return isRemoved; //returns true if the element existed in the first place
                          //returns false if the element didn't exist

    }

    public ArrayList<Product> getProducts (){
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


