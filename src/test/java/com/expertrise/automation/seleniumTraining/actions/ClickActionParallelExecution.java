package com.expertrise.automation.seleniumTraining.actions;

import com.expertrise.automation.testscripts.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ClickActionParallelExecution extends BaseClass {

    @Test(groups = {"GridTest"})
    public void doubleClickMe() throws Exception {
        WebElement doubleClickMe = getDriver().findElement(By.xpath("//button[normalize-space()='Double Click Me']"));
        Actions action = new Actions(getDriver());
        action.doubleClick(doubleClickMe).build().perform();
     /*   new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(
                ExpectedConditions.alertIsPresent());*/
    }

}
