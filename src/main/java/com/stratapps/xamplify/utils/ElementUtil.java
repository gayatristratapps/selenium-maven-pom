package com.stratapps.xamplify.utils;

import org.openqa.selenium.*;

public class ElementUtil {

    public static WebElement getById(WebDriver driver, String id) {
        return driver.findElement(By.id(id));
    }

    public static void sendText(By locator, String text, WebDriver driver) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void click(By locator, WebDriver driver) {
        driver.findElement(locator).click();
    }

    public static void sendKey(By locator, Keys key, WebDriver driver) {
        driver.findElement(locator).sendKeys(key);
    }
}
