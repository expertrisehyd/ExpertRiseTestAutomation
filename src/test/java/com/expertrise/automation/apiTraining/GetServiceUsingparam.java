package com.expertrise.automation.apiTraining;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class GetServiceUsingparam implements ITestListener {

    private static final Log log = LogFactory.getLog(GetServiceUsingparam.class);
    String response;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public ExtentTest test;
    static String repName;
    String additionalNeeds;

    @BeforeTest
    public static void configureService() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void validateGetService() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 2a04ed01177bfaa")
                .pathParam("bookingid", 3)
                .get("/booking/{bookingid}")
                .then()
                .statusCode(200)
                .extract().response();
        additionalNeeds = response.jsonPath().get("additionalneeds");


        log.info(" The required get booking details are :" + response.jsonPath().prettyPrint());
    }

    @Test
    public void validateServiceResponseFields() {


        Assert.assertEquals("Breakfast", additionalNeeds);

    }

    @AfterTest
    public void clearTheService() {

        log.info(" clean up the service");

    }

}
