package icartest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class CheckOutSteps {

    public int am;

    @Given("I should have {int} NIS")
    public void i_should_have_nis(Integer amountOfMoney) {
        am = amountOfMoney;
    }
    @When("I choose one apple")
    public void i_choose_one_apple() {
        //
    }
    @Then("I should have zero NIS")
    public void i_should_have_zero_nis() {
        assertTrue(am==0);
    }
}
