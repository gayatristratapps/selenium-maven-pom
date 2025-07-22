package com.stratapps.xamplify.utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {


	public static WebDriver extractDriver(Object testClassInstance) {
	    try {
	        return (WebDriver) testClassInstance.getClass().getField("driver").get(testClassInstance);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	//Mounika
	public static void clickWithRetry(By locator, WebDriver driver, int retries) {
	    for (int i = 0; i < retries; i++) {
	        try {
	            WebElement element = driver.findElement(locator);
	            new WebDriverWait(driver, Duration.ofSeconds(10))
	                .until(ExpectedConditions.elementToBeClickable(element));
	            element.click();
	            return;
	        } catch (Exception e) {
	            System.out.println("Retrying click... Attempt: " + (i + 1));
	            
	        }
	    }
	    throw new RuntimeException("Unable to click after retries: " + locator);
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
