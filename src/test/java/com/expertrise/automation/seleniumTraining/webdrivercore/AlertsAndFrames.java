package com.expertrise.automation.seleniumTraining.webdrivercore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertsAndFrames {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void driverConfig(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");



    }
    @Test
    public void testing(){
        alert();
        windows();

        driver.quit();
    }

    public void alert(){
        driver.findElement(By.id("alertBtn")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert");
        alert.accept();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("confirmBtn")).click();
        Alert alert1 = driver.switchTo().alert();
        System.out.println("Alert");
        alert1.accept();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("confirmBtn")).click();
        Alert alert2 = driver.switchTo().alert();
        System.out.println("Alert");
        alert2.dismiss();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("promptBtn")).click();
        Alert prompalert= driver.switchTo().alert();
        prompalert.sendKeys("Hello Guys");
        System.out.println("Alert");
        prompalert.accept();




    }

    public void windows(){
        String mainPage = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[normalize-space()='New Tab']")).click();
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for(String handle : driver.getWindowHandles()){
            if (!handle.equals(mainPage)){
                driver.switchTo().window(handle);
                break;
            }
        }


        System.out.println("New Page Title: " + driver.getTitle());


        driver.close();
        driver.switchTo().window(mainPage);



        driver.findElement(By.id("PopUp")).click();
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for(String handle : driver.getWindowHandles()){
            if (!handle.equals(mainPage)){
                driver.switchTo().window(handle);
                break;
            }
        }


        System.out.println("New Page Title: " + driver.getTitle());


        driver.close();
        driver.switchTo().window(mainPage);



    }


}
