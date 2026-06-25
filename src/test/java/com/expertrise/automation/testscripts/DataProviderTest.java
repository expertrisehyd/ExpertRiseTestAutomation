package com.expertrise.automation.testscripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderTest {
    public WebDriver driver;
    @BeforeTest
    public void setupBrowserConfiguration()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("Title : " + driver.getTitle());
        System.out.println("URL   : " + driver.getCurrentUrl());
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) throws InterruptedException {
        // Perform login
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(Long.parseLong("3000"));
        // Validation logic
        boolean loginSuccess;
        try {
            driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")); // element visible only if login succeeds
            loginSuccess = true;
        } catch (Exception e) {
            loginSuccess = false;
        }
        // Compare with expected result from Excel
        if (expectedResult.equalsIgnoreCase("Success")) {
            Assert.assertTrue(loginSuccess, "Login should succeed but failed for user: " + username);
        } else {
            Assert.assertFalse(loginSuccess, "Login should fail but succeeded for user: " + username);
        }
    }
    // DataProvider to fetch data from Excel
    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        String excelPath = "src/test/resources/data.xlsx"; // path to your Excel file
        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }
        workbook.close();
        fis.close();
        return data;
    }

    @AfterTest
    public void tearDownBrowser()
    {
        //driver.close();
        driver.quit();
    }

}
