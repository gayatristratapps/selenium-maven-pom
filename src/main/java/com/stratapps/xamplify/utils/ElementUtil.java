package com.stratapps.xamplify.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ElementUtil {


	public static WebDriver extractDriver(Object testClassInstance) {
	    try {
	        return (WebDriver) testClassInstance.getClass().getField("driver").get(testClassInstance);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	
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
    
    
    public static void selectDropdownByVisibleText(By locator, String visibleText, WebDriver driver) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    
    
    
    
    public static void sendTextdriver(WebDriver driver, By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public static void sendKeydriver(WebDriver driver, By locator, Keys key) {
        driver.findElement(locator).sendKeys(key);
    }

    

    
    
    
    
    
    
    
    
    
    
    
    
    
}
