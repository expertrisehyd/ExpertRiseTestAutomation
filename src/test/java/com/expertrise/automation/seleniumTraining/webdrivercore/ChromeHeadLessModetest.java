package com.expertrise.automation.seleniumTraining.webdrivercore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeHeadLessModetest {

    public WebDriver driver;

    @BeforeTest
    public void setupBrowserConfiguration()
    {
        System.out.println("Setting up Chrome Browser configuration");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void launchChromeBrowserHeadlessTest()
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
