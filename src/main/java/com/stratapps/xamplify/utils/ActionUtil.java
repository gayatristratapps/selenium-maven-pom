package com.stratapps.xamplify.utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtil {

	public static void hoverAndClick(WebDriver driver, By hoverLocator, By clickLocator) throws Exception {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // ⏳ increased timeout

	    try {
	    	
			/*
			 * // ✅ Ensure page is loaded (optional but safer if called early)
			 * WaitUtil.waitForPageToLoad(driver, 60);
			 * WaitUtil.waitForInvisibilityOfElement(By.
			 * cssSelector(".loader, .backdrop, .spinner"), driver, 60);
			 */
	        
	        
	        // ✅ First wait for element to be present in the DOM
	        wait.until(ExpectedConditions.presenceOfElementLocated(hoverLocator));

	        
	        
	        
	        // Wait for hover element to be visible
	        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(hoverLocator));

			/*
			 * // Scroll into view (avoids off-screen issues in headless/CI)
			 * ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);", hoverElement);
			 * Thread.sleep(500); // short buffer time
			 */
	        // Hover
	        Actions actions = new Actions(driver);
	        actions.moveToElement(hoverElement).pause(Duration.ofMillis(500)).perform();

	        

	        // ✅ Wait for clickable target
	        wait.until(ExpectedConditions.presenceOfElementLocated(clickLocator));
	        // Wait for click element to be clickable
	        WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(clickLocator));

	        // Scroll to and click
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickElement);
	        actions.moveToElement(clickElement).click().perform();
	        
			/*
			 * // ✅ Try click via Actions try {
			 * actions.moveToElement(clickElement).click().perform(); } catch (Exception
			 * clickFailure) { // ⛔ Fallback to JS click if Action fails
			 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			 * clickElement); }
			 */
	    } catch (TimeoutException e) {
	        System.out.println("Timed out waiting for hover or click element.");
	        ScreenshotUtil.captureScreenshot(driver, "hover_click_timeout_" + System.currentTimeMillis());
	        throw e;

	    
	    } catch (Exception e) {
	        System.out.println("Unexpected error in hoverAndClick: " + e.getMessage());
	        throw e;
	    }
	}

	
	
	public static void scrollInsideElement(WebDriver driver, WebElement element,int pixels) { 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += arguments[1];", element, pixels);
	
	}
	
	
	
	
	
	/*
	 * public static void hoverAndClick(WebDriver driver, By hoverLocator, By
	 * clickLocator) { WebDriverWait wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(60));
	 * 
	 * // Wait for hover and click elements to be visible and clickable WebElement
	 * hoverElement =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(hoverLocator));
	 * WebElement clickElement =
	 * wait.until(ExpectedConditions.elementToBeClickable(clickLocator));
	 * 
	 * // Perform hover and click using Actions Actions actions = new
	 * Actions(driver); actions.moveToElement(hoverElement) .pause(500)
	 * .click(clickElement) .build().perform(); }
	 * 
	 * public static void scrollInsideElement(WebDriver driver, WebElement element,
	 * int pixels) { ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollTop += arguments[1];", element,
	 * pixels); }
	 */


// ✅ NEW METHOD: Click with retry logic and better wait handling
public static void clickWithRetry(WebDriver driver, By locator, int maxRetries) {
    int retry = 0;
    while (retry < maxRetries) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            // ✅ Scroll into view if not visible (helps hidden elements become clickable)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            element.click();
            return;
        } catch (Exception e) {
            retry++;
            System.out.println("Retry " + retry + ": Failed to click " + locator);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }
    throw new RuntimeException("Failed to click after " + maxRetries + " attempts: " + locator);
}
}