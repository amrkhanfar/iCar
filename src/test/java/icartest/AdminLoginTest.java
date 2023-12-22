package icartest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminLoginTest {

    @When("the admin provides valid credentials")
    public void the_admin_provides_valid_credentials(io.cucumber.datatable.DataTable dataTable) {

    }

    @Then("the admin is successfully logged into the app")
    public void the_admin_is_successfully_logged_into_the_app() {

    }

    @When("the admin provides invalid credentials")
    public void the_admin_provides_invalid_credentials(io.cucumber.datatable.DataTable dataTable) {

    }

    @Given("the admin is not logged in the app")
    public void the_admin_is_not_logged_in_the_app() {

    }
    @When("the admin provides empty credentials")
    public void the_admin_provides_empty_credentials(io.cucumber.datatable.DataTable dataTable) {

    }
    @When("the admin attempts to log in")
    public void the_admin_attempts_to_log_in() {

    }
    @Then("the admin is unable to log in")
    public void the_admin_is_unable_to_log_in() {

    }
    @Then("an error message is displayed to inform the admin of the issue")
    public void an_error_message_is_displayed_to_inform_the_admin_of_the_issue() {

    }
}
