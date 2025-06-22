package com.stratapps.xamplify.base;

import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.EmailUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // 🔁 CHANGED
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    public static List<String> failedScreenshotPaths = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static WebDriver staticDriver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
        if (staticDriver == null) {
            String browser = ConfigReader.getProperty("browser.name");

            if (browser == null) {
                throw new IllegalStateException("Browser property is missing in config.properties");
            }

            logger.info("Setting up WebDriver. Browser: {}", browser);

            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();

                // 🔁 CHANGED: Headless mode setup for GitHub Actions
                ChromeOptions options = new ChromeOptions();
               // options.addArguments("--headless"); // run without UI
                options.addArguments("--no-sandbox"); // required in CI
                options.addArguments("--disable-dev-shm-usage");// prevent shared memory crash
                options.addArguments("--remote-allow-origins=*");// optional but prevents newer driver errors
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                
                
             
                staticDriver = new ChromeDriver(options); // 🔁 CHANGED
                logger.debug("Initialized ChromeDriver in headless mode");

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

            String url = ConfigReader.getProperty("url");
            if (url == null) {
                throw new IllegalStateException("URL property is missing in config.properties");
            }
            staticDriver.get(url);
            logger.info("Navigated to URL: {}", url);
        }

        driver = staticDriver;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            logger.info("Test completed, browser remains open");
            // staticDriver.quit(); // Uncomment to close after each test class
        }
    }


}
