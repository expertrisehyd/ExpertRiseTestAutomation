package com.expertrise.automation.seleniumTraining.webelements;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertHandlingTest {
    public WebDriver driver;

    @BeforeTest
    public void setupBrowserConfiguration() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

    }

    @Test
    public void HandleSimpleAlert() {

        WebElement simpleAlertButton = driver.findElement(By.id("alertBtn"));
        simpleAlertButton.click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void HandleConfirmationAlert() {

        WebElement confirmationAlertButton = driver.findElement(By.id("confirmBtn"));
        confirmationAlertButton.click();
        //  driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void HandlePromptAlert() throws InterruptedException {

        WebElement promptAlertButton = driver.findElement(By.id("promptBtn"));
        promptAlertButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Automation Testing");
        Thread.sleep(5000);
        alert.accept();
    }

    @AfterTest
    public void tearDownBrowser() {
        if (driver != null) {
            // driver.quit();
        }
    }
}