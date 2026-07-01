package com.expertrise.automation.apiTraining;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostService {

    private static final Log log = LogFactory.getLog(GetService.class);
    String payload = "{"
            + "\"firstname\" : \"Jim\","
            + "\"lastname\" : \"Brown\","
            + "\"totalprice\" : 111,"
            + "\"depositpaid\" : true,"
            + "\"bookingdates\" : {"
            + "    \"checkin\" : \"2026-01-01\","
            + "    \"checkout\" : \"2026-01-01\""
            + "},"
            + "\"additionalneeds\" : \"Breakfast\""
            + "}";


    @BeforeTest
    public static void configureService() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

    }

    @Test
    public void validatePostService() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                //  .header("Authorization", "Bearer 2a04ed01177bfaa")
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .log().all()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().response();

        log.info(" The required get booking details are :" + response.jsonPath().prettyPrint());

    }

    @Test
    public void validatePostServiceResponseFeilds() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                //  .header("Authorization", "Bearer 2a04ed01177bfaa")
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .log().all()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Jim"))   // <-- corrected path
                .body("booking.lastname", equalTo("Brown"))
                .extract().response();
        log.info(" The required get booking details are :" + response.jsonPath().prettyPrint());

    }

    @AfterTest
    public void clearTheService() {

        log.info(" clean up the service");

    }

}
