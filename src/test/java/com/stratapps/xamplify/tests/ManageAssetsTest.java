package com.stratapps.xamplify.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.stratapps.xamplify.base.BaseTest;
import com.stratapps.xamplify.pages.LoginPage;
import com.stratapps.xamplify.pages.ManageAssetsPage;
import com.stratapps.xamplify.utils.ConfigReader;

public class ManageAssetsTest extends BaseTest {
    private ManageAssetsPage manageAssetsPage;
    private static final Logger logger = LogManager.getLogger(ManageAssetsTest.class);

    @BeforeClass
    public void setUpTest() {
        try {
            super.setUp(); 

            if (driver == null) {
                throw new IllegalStateException("WebDriver not initialized.");
            }

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

            manageAssetsPage = new ManageAssetsPage(driver);
            logger.info("ManageAssetsTest setup completed");
        } catch (Exception e) {
            logger.error("Setup failed", e);
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @Test(priority = 1)
    public void testNavigateToManageAssets() {
        logger.info("Running testNavigateToManageAssets");
        manageAssetsPage.navigateToManageAssets();
    }

    @Test(priority = 2)
    public void testSearchAndSortAsset() {
        logger.info("Starting search and sort test");
        try {
            manageAssetsPage.searchAndSortAsset("png");
            logger.info("Search and sort test completed successfully");
        } catch (Exception e) {
            logger.error("Search and sort test failed", e);
            throw new RuntimeException("Search and sort test failed", e);
        }
    }

    @Test(priority = 3)
    public void testAssetActions() {
        logger.info("Starting asset actions test");
        try {
            manageAssetsPage.performAssetActions("UpdatedAssetName");
            logger.info("Asset actions test completed successfully");
        } catch (Exception e) {
            logger.error("Asset actions test failed", e);
            throw new RuntimeException("Asset actions test failed", e);
        }
    }

    @Test(priority = 4)
    public void testAnalytics() {
        logger.info("Starting analytics test");
        try {
            manageAssetsPage.handleAnalytics();
            logger.info("Analytics test completed successfully");
        } catch (Exception e) {
            logger.error("Analytics test failed", e);
            throw new RuntimeException("Analytics test failed", e);
        }
    }

    @Test(priority = 5)
    public void testVideoAssetActions() {
        logger.info("Starting video asset actions test");
        try {
            manageAssetsPage.handleVideoAssetActions();
            logger.info("Video asset actions test completed successfully");
        } catch (Exception e) {
            logger.error("Video asset actions test failed", e);
            throw new RuntimeException("Video asset actions test failed", e);
        }
    }

    @Test(priority = 6)
    public void testAssetViews() {
        logger.info("Starting asset views test");
        try {
            manageAssetsPage.handleAssetViews();
            logger.info("Asset views test completed successfully");
        } catch (Exception e) {
            logger.error("Asset views test failed", e);
            throw new RuntimeException("Asset views test failed", e);
        }
    }

    @Test(priority = 7)
    public void testAssetFilters() {
        logger.info("Starting asset filters test");
        try {
            manageAssetsPage.filterAssets();
            logger.info("Asset filters test completed successfully");
        } catch (Exception e) {
            logger.error("Asset filters test failed", e);
            throw new RuntimeException("Asset filters test failed", e);
        }
    }

    @AfterMethod
    public void handleTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test failed: " + result.getName());
            // You can add screenshot logic here
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                super.tearDown();
                logger.info("Driver closed successfully");
            }
        } catch (Exception e) {
            logger.error("Failed to close driver", e);
        }
    }
}
