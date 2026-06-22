package com.expertrise.automation.seleniumTraining.webdrivercore;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Login {
    static WebDriver driver;
    static WebDriverWait wait;


    @BeforeAll
    public static void BrowserConfig(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");

    }
    @Test
    public void loginTest() throws InterruptedException {
        BrowserNavigation();
        userName();
        password();
        clickButton();
        verifyDashBoard();
        clickProfile();
        logOut();


    }

    public void BrowserNavigation() throws InterruptedException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        System.out.println("Navigated to Login page");

    }

    public void userName(){
        driver.findElement(By.name("username")).sendKeys("Admin");

    }

    public void password(){
        driver.findElement(By.name("password")).sendKeys("admin123");
    }

    public void clickButton() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        wait.until(
                ExpectedConditions.urlContains("dashboard"));
        System.out.println(driver.getCurrentUrl());

    }

    public void verifyDashBoard(){
        WebElement dashboard = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6")));
        System.out.println(dashboard.getText());

        boolean status = dashboard.isDisplayed();
        System.out.println("Logged in ?? : "+ status);
    }


    public void clickProfile(){
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(By.className("oxd-topbar-header-userarea")));
        profile.click();
        System.out.println("Profile clicked");
        List<WebElement> menuItems = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.className("oxd-dropdown-menu")));

        for(WebElement item : menuItems){
            System.out.println(item.getText());
        }

    }

    public void logOut(){
        driver.findElement(
                        By.xpath("//a[text()='Logout']"))
                .click();
        System.out.println("Logged out");
    }

    @AfterAll
    static void tearDown() {


    }

}
