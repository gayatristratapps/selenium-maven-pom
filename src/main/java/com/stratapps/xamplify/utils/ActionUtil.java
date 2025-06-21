package com.stratapps.xamplify.utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtil {

public static void hoverAndClick(WebDriver driver, By hoverLocator, By clickLocator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    // Wait for hover and click elements to be visible and clickable
    WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(hoverLocator));
    WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(clickLocator));

    // Perform hover and click using Actions
    Actions actions = new Actions(driver);
    actions.moveToElement(hoverElement)
           .pause(500)
           .click(clickElement)
           .build().perform();
}

public static void scrollInsideElement(WebDriver driver, WebElement element, int pixels) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += arguments[1];", element, pixels);
}



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