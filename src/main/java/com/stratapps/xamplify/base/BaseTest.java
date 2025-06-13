package com.stratapps.xamplify.base;

import com.stratapps.xamplify.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    public WebDriver getDriver() {
        return driver;
    }

    private static WebDriver staticDriver;
    
    @BeforeClass
    public void setUp() {
        // Only initialize if the static driver is null
        if (staticDriver == null) {
            String browser = ConfigReader.get("browser");
            logger.info("Setting up WebDriver. Browser: {}", browser);
    
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                staticDriver = new ChromeDriver();
                logger.debug("Initialized ChromeDriver");
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                staticDriver = new FirefoxDriver();
                logger.debug("Initialized FirefoxDriver");
            } else {
                logger.error("Unsupported browser: {}", browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
    
            staticDriver.manage().window().maximize();
            logger.info("Browser window maximized");
    
            String url = ConfigReader.get("url");
            staticDriver.get(url);
            logger.info("Navigated to URL: {}", url);
        }
        
        // Always assign the static driver to the instance driver
        driver = staticDriver;
    }

    @AfterClass
    public void tearDown() {
        // Keep the browser open between tests
        if (driver != null) {
            logger.info("Test completed, browser remains open");
            // Do not quit the driver to keep the browser open
        }
    }
}
