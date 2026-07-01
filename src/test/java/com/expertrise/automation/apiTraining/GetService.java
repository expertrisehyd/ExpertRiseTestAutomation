package com.expertrise.automation.apiTraining;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GetService implements ITestListener {

    private static final Log log = LogFactory.getLog(GetService.class);
    String response;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public ExtentTest test;
    static String repName;

    @BeforeTest
    public static void configureService() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void validateGetService() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 2a04ed01177bfaa")
                .get("/booking/2")
                .then()
                .statusCode(200)
                .extract().response();

        log.info(" The required get booking details are :" + response.jsonPath().prettyPrint());
    }

    @AfterTest
    public void clearTheService() {

        log.info(" clean up the service");

    }

}
