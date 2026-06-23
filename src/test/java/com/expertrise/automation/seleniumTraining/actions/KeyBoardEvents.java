package com.expertrise.automation.seleniumTraining.actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KeyBoardEvents {

/*
    perform keyboard events operations
     like key up,
      and down,
      enter multiple characters in other operations,
      and copy and paste operations using the Actions class
 /*
    Methods

keyDown(CharSequence key) − perform a modifier key press, passed as a parameter.
keyDown(WebElement e, CharSequence key) − perform a modifier key press post focusing an element. The webElement e, and key to be pressed are passed as parameters.
keyUp(CharSequence key) − perform a modifier key release, passed as a parameter.
keyUp(WebElement e, CharSequence key) − perform a modifier key release post focusing an element. The webElement e, and key to be released are passed as parameters.
sendKeys(CharSequence key) −  to send keys to elements in focus. The key to be sent is passed as a parameter.
sendKeys(WebElement e, CharSequence key) −  to send keys to the webElement passed as parameter.
build() − This method is used to create a combination of actions having all the actions to be carried on.
perform() − This method is used to perform actions without invoking the build() first.

 */

    public WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       // driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/register.php");
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
    }

    @Test
    public void copyPasteActionTest() throws InterruptedException {
      WebElement fullName = driver.findElement(By.xpath("//input[@name='fullname']"));
      WebElement email    = driver.findElement(By.xpath("//input[@name='email']"));
        fullName.sendKeys("Expertrise@test.com");
        Actions action = new Actions (driver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys("a");
        action.keyDown(Keys.CONTROL);
        action.build().perform();

        // copy the text
        action.keyDown(Keys.CONTROL);
        action.sendKeys("c");
       action.keyUp(Keys.CONTROL);
        action.build().perform();

       // move to next tab called email
        action.sendKeys(Keys.TAB);
        action.build().perform();

        //past the text
        action.keyDown(Keys.CONTROL);
        action.sendKeys("v");
        action.keyUp(Keys.CONTROL);
        action.build().perform();
        Thread.sleep(3000);

       String copiedtxt = email.getAttribute("value");

       System.out.println("The copied text is -- >"+ copiedtxt);


    }


    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
