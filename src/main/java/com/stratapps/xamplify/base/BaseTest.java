package com.stratapps.xamplify.base;

import com.stratapps.xamplify.pages.LogoutPage;
import com.stratapps.xamplify.pages.LoginPage;
import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.EmailUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.*;

public class BaseTest {
    protected WebDriver driver;
    public static List<String> failedScreenshotPaths = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static WebDriver staticDriver;

    // 🔁 CHANGED: Vendor test class names
    private static final Set<String> VENDOR_TEST_CLASSES = new HashSet<>(Arrays.asList(
        "ShareLeadsTest",
        "AddTracksTest",
        "ManageTracksTest",
        "TeamVendorTest"
    ));

    private static int vendorCompletedCount = 0;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
        logger.info("🛠 BaseTest.setUp() - START");

        if (staticDriver == null) {
            String browser = ConfigReader.getProperty("browser.name");

            if (browser == null) {
                throw new IllegalStateException("Browser property is missing in config.properties");
            }

            logger.info("Setting up WebDriver. Browser: {}", browser);

            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-gpu");
                options.addArguments("--start-maximized");
                options.addArguments("--force-device-scale-factor=1");

                if (System.getenv("CI") != null) {
                    options.addArguments("--headless=new");
                }

                String uniqueProfile = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis();
                options.addArguments("--user-data-dir=" + uniqueProfile);

                staticDriver = new ChromeDriver(options);
                staticDriver.manage().window().maximize();
                logger.info("✅ ChromeDriver initialized and window maximized.");
            }

            String url = ConfigReader.getProperty("url");
            if (url == null) {
                throw new IllegalStateException("URL property is missing in config.properties");
            }

            logger.info("🌐 Navigating to URL: {}", url);
            staticDriver.get(url);
        } else {
            logger.info("🔁 Reusing existing WebDriver session");
        }

        driver = staticDriver;

        // 🔸 Highlighted Check
        if (driver == null) {
            logger.error("🚨 WebDriver is NULL after setup!");
            throw new RuntimeException("WebDriver not initialized!");
        }

        logger.info("✅ BaseTest.setUp() - COMPLETE");
    }

    protected void logoutIfLoggedIn() {
        try {
            LogoutPage logoutPage = new LogoutPage(driver);
            logoutPage.logout();
            logger.info("👋 Logout successful.");
        } catch (Exception e) {
            logger.warn("⚠️ Logout failed or not needed: {}", e.getMessage());
        }
    }

    // 🔁 CHANGED: Intelligent logout after all vendor tests complete
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        String className = this.getClass().getSimpleName();

        if (VENDOR_TEST_CLASSES.contains(className)) {
            vendorCompletedCount++;
            logger.info("✅ Vendor class '{}' completed. Progress: {}/{}", className,
                        vendorCompletedCount, VENDOR_TEST_CLASSES.size());

            if (vendorCompletedCount == VENDOR_TEST_CLASSES.size()) {
                logger.info("🎯 All vendor test classes finished. Logging out...");
                logoutIfLoggedIn();
            } else {
                logger.info("⏳ Waiting for remaining vendor test classes. Logout skipped for now.");
            }
        } else {
            // For non-vendor (e.g., partner) classes — log out immediately
            logoutIfLoggedIn();
        }
    }

    // ✅ NEW: Session checker to be used in test classes
    protected boolean isLoggedIn() {
        try {
            return new LoginPage(driver).isWelcomeDisplayed();
        } catch (Exception e) {
            logger.warn("⚠️ isLoggedIn() failed: {}", e.getMessage());
            return false;
        }
    }
}
