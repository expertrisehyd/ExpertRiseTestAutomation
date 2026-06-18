package com.expertrise.automation.seleniumTraining.webelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DatePickerHandlingTest {

    public WebDriver driver;
    @BeforeTest
    public void setupBrowserConfiguration() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
    }

    @Test(enabled = false)
   public void HandleDatePicker1(){
      WebElement datePicker1 = driver.findElement(By.id("datepicker"));
        datePicker1.click();
        datePicker1.sendKeys("25/05/1991");
        WebElement startdate =  driver.findElement(By.id("start-date"));
        startdate.click();

   }

    @Test(enabled = true)
    public void HandleDatePicker2(){
        WebElement datePicker2 = driver.findElement(By.id("txtDate"));
        datePicker2.click();
        driver.findElement(By.xpath("//a[normalize-space()='18']")).click();
        WebElement startdate =  driver.findElement(By.id("start-date"));
        startdate.click();

    }


    @AfterTest
    public void tearDownBrowser() {

        if (driver != null) {
            // driver.quit();
        }
    }

}
