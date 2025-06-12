package com.stratapps.xamplify.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class ActionUtil {

    public static void hoverAndClick(WebDriver driver, By hoverLocator, By clickLocator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(hoverLocator))
               .pause(500)
               .click(driver.findElement(clickLocator))
               .build().perform();
    }

    public static void scrollInsideElement(WebDriver driver, WebElement element, int pixels) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += arguments[1];", element, pixels);
    }
}
