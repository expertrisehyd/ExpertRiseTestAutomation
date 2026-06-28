package com.expertrise.automation.seleniumTraining.webelements;

import com.expertrise.automation.testscripts.BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;



public class AlertHandlingTestParallelTest extends BaseClass {


    @Test(groups = {"GridTest"})
    public void HandleSimpleAlert() {
        getDriver().navigate().to("https://testautomationpractice.blogspot.com/");
        WebElement simpleAlertButton = getDriver().findElement(By.id("alertBtn"));
        simpleAlertButton.click();
        getDriver().switchTo().alert().accept();
    }

    @Test(groups = {"GridTest"})
    public void HandleConfirmationAlert() {
        getDriver().navigate().to("https://testautomationpractice.blogspot.com/");
        WebElement confirmationAlertButton = getDriver().findElement(By.id("confirmBtn"));
        confirmationAlertButton.click();
        //  driver.switchTo().alert().accept();
        getDriver().switchTo().alert().dismiss();
    }
}
