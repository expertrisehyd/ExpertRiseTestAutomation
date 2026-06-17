package com.expertrise.automation.seleniumTraining.webdrivercore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeBrowserTest {
    public WebDriver driver;

    @BeforeTest
    public void setupBrowserConfiguration()
    {
        System.out.println("Setting up Chrome Browser configuration");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

   @Test
    public void launchChromeBrowser()
    {
        driver.get("https://www.google.com");
        System.out.println("Title : " + driver.getTitle());
        System.out.println("URL   : " + driver.getCurrentUrl());
    }

    @AfterTest
    public void tearDownBrowser()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}
