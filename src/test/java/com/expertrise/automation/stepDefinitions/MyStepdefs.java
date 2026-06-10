package com.expertrise.automation.utils.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("This is a placeholder")
    public void thisIsAPlaceholder() {
        System.out.println(" This is a placeholder step definition.");
    }

    @When("I run the test")
    public void iRunTheTest() {
        System.out.println("running the test...");

    }

    @Then("It should pass successfully")
    public void itShouldPassSuccessfully() {
        System.out.println("Test passed successfully!");
    }
}
