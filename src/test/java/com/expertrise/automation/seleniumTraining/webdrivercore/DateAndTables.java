package com.expertrise.automation.seleniumTraining.webdrivercore;

import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DateAndTables {

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
    public void test(){


        dropDown();
        datePicker();
        tableprint();
        pagination();

        driver.quit();
    }
    public void dropDown(){

        WebElement dd = driver.findElement(By.id("country"));
        Select drop = new Select(dd);

        drop.selectByVisibleText("India");




    }
    public void datePicker(){
        WebElement date = driver.findElement(By.id("datepicker"));
        date.click();
        wait.until(ExpectedConditions.visibilityOf(date));
        date.sendKeys("06/15/2025");


        WebElement date2 = driver.findElement(By.id("txtDate"));
        date2.click();
        Select year = new Select(driver.findElement(By.xpath("//select[@aria-label='Select year']")));
        year.selectByVisibleText("2025");
        Select month = new Select(driver.findElement(By.xpath("//select[@aria-label='Select month']")));
        month.selectByVisibleText("Apr");


        driver.findElement(By.xpath("//a[normalize-space()='15']")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//input[@id='start-date']")).sendKeys("01-10-2003");
        driver.findElement(By.xpath("//input[@id='end-date']")).sendKeys("1-10-2009");










    }

    public void tableprint(){
        WebElement tbody =driver.findElement(By.xpath("//*[@id=\"HTML1\"]/div[1]/table/tbody"));

        wait.until(d -> !tbody.findElements(By.tagName("tr")).isEmpty());
        List<WebElement> tables =tbody.findElements(By.tagName("tr"));
        for (WebElement table: tables){
            List<WebElement> MasterRow = table.findElements(By.tagName("th"));
            List<WebElement> row = table.findElements(By.tagName("td"));
            for (WebElement ele : MasterRow){
                System.out.print(ele.getText()+" ");
            }

            for (WebElement ele : row){
                System.out.print(ele.getText()+" ");
            }
            System.out.println();
        }
    }
    public void pagination(){

        int totalPages =  driver.findElements(By.xpath("//ul[@id='pagination']//a")).size();

        for(int i =1; i<=totalPages; i++){
            System.out.println("Current page " + i);
            WebElement element =  driver.findElement(By.xpath("//ul[@id='pagination']//a[text()='"+i+"']"));
            element.click();
            wait.until(ExpectedConditions.attributeContains(element,"class","active"));


            page();

        }


    }
    public  void page(){
        WebElement headerPage =driver.findElement(By.xpath("//*[@id=\"productTable\"]/thead"));
        WebElement paginationtable =driver.findElement(By.xpath("//*[@id=\"productTable\"]/tbody"));


        wait.until(d -> !headerPage.findElements(By.tagName("tr")).isEmpty());
        List<WebElement> ptables =paginationtable.findElements(By.tagName("tr"));
        List<WebElement> htables = headerPage.findElements(By.tagName("tr"));
        for (WebElement table: htables){

            List<WebElement> row = table.findElements(By.tagName("th"));


            for (WebElement ele : row){
                System.out.print(ele.getText()+" ");
            }
            System.out.println();
        }

        for (WebElement table: ptables){

            List<WebElement> row = table.findElements(By.tagName("td"));


            for (WebElement ele : row){
                System.out.print(ele.getText()+" ");
            }
            System.out.println();
        }
    }




}
