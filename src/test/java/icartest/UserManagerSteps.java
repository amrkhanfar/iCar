package icartest;

import ICar.Rank;
import ICar.User;
import ICar.UserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserManagerSteps {

    private UserManager userManager;
    private User newUser;
    private User loggedinUser;
    private User searchedUser, deletedUser;
    private ArrayList<User> searchedUsers;

    @Given("the user manager is initialized")
    public void the_user_manager_is_initialized() {
        userManager = new UserManager();
    }
    @When("a user with name {string}, email {string}, password {string}, and role {string} registers")
    public void a_user_with_name_email_password_and_role_registers(String name, String email, String password, String role) {
        newUser = userManager.registerUser(name,email,password,role);
    }
    @Then("the registration is successful")
    public void the_registration_is_successful() {
       assertNotNull(newUser);
    }

    @Given("a user with name {string}, email {string}, password {string}, and role {string} exists")
    public void a_user_with_name_email_password_and_role_exists(String name, String email, String password, String role) {
        userManager.registerUser(name,email,password,role);
    }
    @When("the user with email {string} and password {string} tries to authenticate")
    public void the_user_with_email_and_password_tries_to_authenticate(String email, String password) {
        loggedinUser = userManager.authenticateUser(email, password);
    }
    @Then("the authentication is successful")
    public void the_authentication_is_successful() {
        assertNotNull(loggedinUser);
    }


    @When("a user with email {string} and password {string} tries to authenticate")
    public void a_user_with_email_and_password_tries_to_authenticate(String email, String password) {
        loggedinUser = userManager.authenticateUser(email, password);
    }
    @Then("the authentication fails")
    public void the_authentication_fails() {
        assertNull(loggedinUser);
    }


    @When("a request is made to get the user with email {string}")
    public void a_request_is_made_to_get_the_user_with_email(String email) {
        searchedUser = userManager.getUserByEmail(email);
    }
    @Then("the user is found")
    public void the_user_is_found() {
        assertNotNull(searchedUser);
    }


    @When("the user with email {string} is deleted")
    public void the_user_with_email_is_deleted(String email) {
        deletedUser = userManager.getUserByEmail(email);
        userManager.deleteUser(deletedUser);
    }
    @Then("the deletion is successful")
    public void the_deletion_is_successful() {
        assertFalse(userManager.getUsers().contains(deletedUser));
    }


    @When("a request is made to delete the user with email {string}")
    public void a_request_is_made_to_delete_the_user_with_email(String email) {
        deletedUser = userManager.getUserByEmail(email);
        userManager.deleteUser(deletedUser);
    }
    @Then("the deletion fails")
    public void the_deletion_fails() {
        assertFalse(userManager.getUsers().contains(deletedUser));
    }


    @When("a request is made to get the user with name {string}")
    public void a_request_is_made_to_get_the_user_with_name(String name) {
        searchedUser = userManager.getUserByName(name);
    }


    @When("a request is made to get users with role {string}")
    public void a_request_is_made_to_get_users_with_role(String role) {
        searchedUsers = userManager.getUsersByRole(role);
    }
    @Then("the users with role {string} are found")
    public void the_users_with_role_are_found(String role) {
        assertFalse(searchedUsers.isEmpty());
        for (User user : searchedUsers) {
            assertEquals(user.getRank(), role);
        }
    }

}
