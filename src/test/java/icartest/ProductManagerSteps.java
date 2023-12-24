package icartest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ICar.Category;
import ICar.Product;
import ICar.ProductManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;


public class ProductManagerSteps {

    private ProductManager productManager;
    private Product product;
    private Category category;
    private ArrayList<Category> categories = new ArrayList<Category>();


    @Given("Product manager is initialized")
    public void initializingUserManager() {
        productManager = new ProductManager();
    }
    @Given("The product manager has categories:")
    public void theProductManagerHasCategories(DataTable dataTable) {
        List<Map<String, String>> categoriesData = dataTable.asMaps(String.class, String.class);


        for (Map<String, String> categoryData : categoriesData) {
            String categoryName = categoryData.get("CategoryName");
            String categoryDescription = categoryData.get("CategoryDescription");

            Category categoryToAdd = new Category(categoryName, categoryDescription);
            productManager.addCategory(categoryToAdd);
        }
    }
    @Given("a product with ID {int} and name {string}")
    public void a_product_with_id_and_name(Integer id, String name) {
        product = new Product(id, name, "", 0.0, 0);
    }


    @Given("the product manager adds the product to the {string} category")
    public void the_product_manager_adds_the_product_to_the_category(String catogeryName) {
        category = productManager.getCategoryByName(catogeryName);
        assertNotNull(category);
        productManager.addProduct(product, category);
    }
    @When("the product manager adds the previous product to the category {string}")
    public void the_product_manager_adds_the_previous_product_to_the_category(String catogeryName) {
        category = productManager.getCategoryByName(catogeryName);
        assertNotNull(category);
        productManager.addProduct(product, category);
    }
    @Then("the product should be added to the {string} category")
    public void the_product_should_be_added_to_the_category(String categoryName) {
        category = productManager.getCategoryByName(categoryName);
        assertNotNull(category);
        assertTrue(category.getProducts().contains(product));
    }

    @Given("the product manager has a category {string}")
    public void the_product_manager_has_a_category(String categoryName) {
        category = new Category(categoryName, "");
        productManager.addCategory(category);
    }

    @When("the product manager removes the product from the {string} category")
    public void the_product_manager_removes_the_product_from_the_category(String categoryName) {
        category = productManager.getCategoryByName(categoryName);
        assertNotNull(category);
        productManager.removeProductFromCategory(product, category);
    }
    @Then("the product should be removed from the {string} category")
    public void the_product_should_be_removed_from_the_category(String categoryName) {
        assertNotNull(category);
        assertFalse(category.getProducts().contains(product));
    }

    @When("the product manager finds the category for the product with ID {int}")
    public void the_product_manager_finds_the_category_for_the_product_with_id(Integer id) {
        product = productManager.findProductById(id);
        assertNotNull(product);
        category = productManager.findCategoryByProduct(product);
        assertNotNull(category);
    }
    @Then("the category should be {string}")
    public void the_category_should_be(String categoryName) {
        assertEquals(categoryName, category.getName());
    }

    @When("the product manager finds the product with ID {int}")
    public void the_product_manager_finds_the_product_with_id(Integer id) {
        product = productManager.findProductById(id);
        assertNotNull(product);
    }
    @Then("the product name should be {string}")
    public void the_product_name_should_be(String productName) {
        assertEquals(productName, product.getName());
    }
    @When("the product manager displays the categories")
    public void the_product_manager_displays_the_categories() {
        productManager.displayCategories();
    }
    @Then("the product manager should see the following categories:")
    public void the_product_manager_should_see_the_following_categories(io.cucumber.datatable.DataTable dataTable) {
        ArrayList<String> expectedCategories = new ArrayList<String>(dataTable.asList(String.class));
        ArrayList<String> actualCategories = new ArrayList<String>();
        for (Category category : productManager.getCategories()) {
            actualCategories.add(category.getName());
        }
        assertEquals(expectedCategories, actualCategories);
    }


    @Given("the product manager adds a new category {string} with description {string}")
    public void the_product_manager_adds_a_new_category_with_description(String name, String description) {
        category = new Category(name, description);
        productManager.addCategory(category);
    }


    @Given("the product manager removes the category {string}")
    public void the_product_manager_removes_the_category(String name) {
        category = productManager.getCategoryByName(name);
        productManager.removeCategory(category);
    }
    @When("the product manager gets all categories")
    public void the_product_manager_gets_all_categories() {
        categories = productManager.getCategories();
    }
    @Then("the product manager should have the following categories:")
    public void the_product_manager_should_have_the_following_categories(io.cucumber.datatable.DataTable dataTable) {
        ArrayList<String> expectedCategories = new ArrayList<String>(dataTable.asList(String.class));
        ArrayList<String> actualCategories = new ArrayList<String>();
        for (Category category : categories) {
            actualCategories.add(category.getName());
        }
        assertEquals(actualCategories, expectedCategories);
    }
    @When("the product manager removes the product with ID {int}")
    public void the_product_manager_removes_the_product_with_id(Integer id) {
        product = productManager.findProductById(id);
        category = productManager.findCategoryByProduct(product);
        productManager.removeProductFromCategory(product, category);
    }
    @Then("the product manager should not find the product with ID {int}")
    public void the_product_manager_should_not_find_the_product_with_id(Integer id) {
        product = productManager.findProductById(id);
        assertNull(product);
    }

}
