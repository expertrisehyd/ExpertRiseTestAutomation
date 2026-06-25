package com.expertrise.automation.testscripts;



import com.expertrise.automation.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class LoginTestFeature {

    public WebDriver driver;

    @BeforeTest
    public void setupBrowserConfiguration()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        System.out.println("Title : " + driver.getTitle());
        System.out.println("URL   : " + driver.getCurrentUrl());
    }


    @Test
    @Parameters({"userName","password"})
    public void testLoginFeature(String userName, String password ) {
        LoginPage loginPage =new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterLoginEmail(userName);
        loginPage.enterLoginPassword(password);
        loginPage.clickLoginButton();
        loginPage.clickLogout();
    }
    @AfterTest
    public void tearDownBrowser()
    {
        //driver.close();
        driver.quit();
    }
}
