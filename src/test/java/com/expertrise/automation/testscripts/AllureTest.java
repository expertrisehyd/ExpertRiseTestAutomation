package com.expertrise.automation.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class AllureTest {

    WebDriver driver;
    @BeforeTest
    public void setup(@Optional String browser) throws Exception{

        // Initiate browser driver
        driver = new ChromeDriver();

        // adding implicit wait of 12 secs
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Opening the webpage
        driver.get("https://www.tutorialspoint.com/selenium/practice/login.php");
    }
    @Test(priority = 1)
    public void verifyWelcomePageHeading() {

        // identify header then get text
        WebElement header = driver.findElement
                (By.xpath("//*[@id='signInForm']/h1"));
        String text = header.getText();

        // assertion to verify login page header
        assertEquals("Welcome, Login In", text);
    }
    @Test(priority = 2)
    public void moveToRegisterPage() {

        // identify button then click
        WebElement btn = driver.findElement
                (By.xpath("//*[@id='signInForm']/div[3]/a"));
        btn.click();
    }
    @Test(priority = 3)
    public void verifyRegisterPageHeading() {

        // identify header then get text
        WebElement heder = driver.findElement
                (By.xpath("//*[@id='signupForm']/h1"));
        String text = heder.getText();

        // assertion to verify register page header
        assertEquals("Welcome,Register", text);
    }

    @AfterTest
    public void teardown() {

        // quitting browser
        driver.quit();
    }
}