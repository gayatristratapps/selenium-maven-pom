package com.stratapps.xamplify.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.awt.AWTException;

public class DamUtil {
	 
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;

    // ✅ Use a static method to initialize
    public static void init(WebDriver driver) {
        DamUtil.driver = driver;
        DamUtil.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        DamUtil.action = new Actions(driver);
    }
    public static WebElement getById(WebDriver driver, String id) {
        return driver.findElement(By.id(id));
    }

    public static void sendText(By locator, String text, WebDriver driver) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void callClickEvent(By locator, WebDriver driver) {
        driver.findElement(locator).click();
    }

    public static void click(By locator, WebDriver driver) {
        driver.findElement(locator).click();
    }

  
	
    public static void hover(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        action.moveToElement(element).build().perform();
    }

    
    public static void selectDropdownByText(By locator, String visibleText, WebDriver driver) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }
    public static void selectDropdownByValue(By locator, String value, WebDriver driver) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }
	
    public static void sendKey(By locator, Keys key, WebDriver driver) {
        driver.findElement(locator).sendKeys(key);
    }

    public static void hover(By locator, WebDriver driver) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }

    public static void clickWithWait(By locator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }


    public static void selectByVisibleText(By locator, String text, WebDriver driver) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public static void selectByValue(By locator, String value, WebDriver driver) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public static void sendTextWithTimestamp(By locator, String text, WebDriver driver) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text + "_" + System.currentTimeMillis());
    }

    public static void uploadFile(String filePath) throws AWTException {
        StringSelection ss = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = new Robot();
        robot.delay(1000);

        // Press CTRL+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public static void scrollToElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean isElementPresent(By locator, WebDriver driver) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementVisible(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void waitForElementPresence(By locator, WebDriver driver, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementVisibility(By locator, WebDriver driver, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementClickable(By locator, WebDriver driver, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static String getText(By locator, WebDriver driver) {
        return driver.findElement(locator).getText();
    }

    public static void clearText(By locator, WebDriver driver) {
        driver.findElement(locator).clear();
    }

    public static void clickByJavaScript(By locator, WebDriver driver) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void setAttribute(By locator, String attributeName, String value, WebDriver driver) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].setAttribute(arguments[1], arguments[2])", 
            element, attributeName, value);
    }
    public static void scrollToUp(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }


    public static String getAttribute(By locator, String attributeName, WebDriver driver) {
        return driver.findElement(locator).getAttribute(attributeName);
    }

   


}
