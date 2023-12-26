package icar;

import java.util.ArrayList;

public class Category {

    private String name;
    private String description;
    private ArrayList<Product> products;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public void addProduct (Product product){
        this.products.add(product);
    }


    public boolean removeProduct (Product product){
        return products.remove(product);
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


