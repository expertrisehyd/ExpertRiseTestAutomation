package com.expertrise.automation.seleniumTraining;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EdgeBrowserTest {
    public WebDriver driver;

    @BeforeTest
    public void setupBrowserConfiguration()
    {
        System.out.println("Setting up Chrome Browser configuration");
      //  WebDriverManager.edgedriver().setup();
        System.setProperty("webdriver.edge.driver", "C:\\Users\\admin\\OneDrive\\Documents\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void launchEdgeBrowser()
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
