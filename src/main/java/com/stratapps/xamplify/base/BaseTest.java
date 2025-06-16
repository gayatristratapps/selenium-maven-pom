// BaseTest.java
package com.stratapps.xamplify.base;

import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.EmailUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
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


	/*
	 * @AfterSuite public void sendReportAfterSuite() { String to =
	 * ConfigReader.getProperty("email.to"); String subject =
	 * ConfigReader.getProperty("email.subject"); String body =
	 * "Hi Team,\n\nPlease find the attached automation report.\n\nRegards,\nQA Automation Team"
	 * ;
	 * 
	 * // Example paths — adjust as per your ExtentReport output location // String
	 * reportPath = "reports/ExtentReport.html"; // Make sure this file is generated
	 * here
	 * 
	 * String reportPath = System.getProperty("user.dir") +
	 * "/test-output/ExtentReport.html";
	 * 
	 * 
	 * // Example screenshot paths — if you don't have, pass an empty list
	 * List<String> screenshots = List.of(); // e.g.,
	 * List.of("screenshots/failure1.png")
	 * 
	 * try { EmailUtil.sendReportEmailWithAttachments(to, subject, body, reportPath,
	 * screenshots); } catch (Exception e) {
	 * System.out.println("❌ Error while sending report email: " + e.getMessage());
	 * e.printStackTrace(); } }
	 */
}
    
    
