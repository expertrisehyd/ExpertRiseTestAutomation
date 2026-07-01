package com.expertrise.automation.stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Assertions;

public class APITestStepDef {

    private static String createdUserId;
    private Response response;

    @Given("the API service is running")
    public void apiServiceRunning() {
        RestAssured.baseURI = "http://localhost:3000";
        RestAssured.basePath = "/users";
        RestAssured.requestSpecification = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    @When("I create a new user with firstName {string}, lastName {string}, email {string}, and role {string}")
    public void createUser(String firstName, String lastName, String email, String role) {
        String requestBody = String.format(
                "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"email\": \"%s\", \"role\": \"%s\" }",
                firstName, lastName, email, role);

        response = given()
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().response();

        createdUserId = response.jsonPath().getString("id");
    }

    @Then("the user is created successfully")
    public void verifyUserCreated() {
        Assertions.assertNotNull(createdUserId);
    }

    @When("I retrieve the created user")
    public void getUser() {
        response = given()
                .when()
                .get("/" + createdUserId)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Then("the user details should match with email {string}")
    public void verifyUserDetails(String email) {
        Assertions.assertEquals(createdUserId, response.jsonPath().getString("id"));
        Assertions.assertEquals(email, response.jsonPath().getString("email"));
    }

    @When("I update the user email to {string}")
    public void updateUser(String newEmail) {
        response = given()
                .body("{ \"email\": \"" + newEmail + "\" }")
                .when()
                .patch("/" + createdUserId)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Then("the user email should be updated successfully")
    public void verifyUserUpdated() {
        Assertions.assertTrue(response.jsonPath().getString("email").contains("updated"));
    }

    @When("I delete the user")
    public void deleteUser() {
        given()
                .when()
                .delete("/" + createdUserId)
                .then()
                .statusCode(200);
    }

    @Then("the user should not be found")
    public void verifyUserDeleted() {
        given()
                .when()
                .get("/" + createdUserId)
                .then()
                .statusCode(404);
    }
}
