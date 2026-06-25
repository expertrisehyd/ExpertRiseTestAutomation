package com.expertrise.automation.testscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ExtentReportTest {

    public WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/ExtentReport.html");
        reporter.config().setReportName("Alert Handling");
        reporter.config().setDocumentTitle("Test Execution Report");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", "Windows");
    }

    @BeforeTest
    public void setUp() throws Exception {

        if (extent != null) {
            test = extent.createTest("Browser set up ");

        }

        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/buttons.php");
            test.pass(" Required Application Browser launched successfully");
        } catch (Exception e) {
            if (test != null) {
                test.fail("Failed to launch browser: " + e.getMessage());
            }
        }
    }

    @Test(enabled = true)
    public void doubleClickMe() throws Exception {
        test = extent.createTest(" double click testing started");
        try {
            WebElement doubleClickMe = driver.findElement(By.xpath("//button[normalize-space()='Double Click Me']"));
            Actions action = new Actions(driver);
            action.doubleClick(doubleClickMe).build().perform();
            Thread.sleep(3000);
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            Assert.fail("Unable to fetch price range element");
        }
    }


    @Test(enabled = false)
    public void doubleClickTest() throws Exception {

        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement copyText = driver.findElement(By.xpath("//button[normalize-space()='Copy Text']"));
        Actions action = new Actions(driver);
        action.doubleClick(copyText).perform();
        Thread.sleep(3000);
    }


    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        } else {
            if (test != null) {
                test.warning("Driver was null in tearDown; nothing to quit.");
            }
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        System.out.println("ExtentReport generated successfully at: ./reports/ExtentReport.html");
    }


}
