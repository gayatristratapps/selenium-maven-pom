package com.stratapps.xamplify.tests;

import com.stratapps.xamplify.pages.LoginPage;
import com.stratapps.xamplify.utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.stratapps.xamplify.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    
    @Test
    public void testValidLogin() {
        logger.info("Starting test: testValidLogin");
        
        LoginPage loginPage = new LoginPage(driver);
        
        // Get credentials from config file
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        
        logger.debug("Using username from config: {}", username);
        
        loginPage.login(username, password);
        
        boolean isWelcomeVisible = loginPage.isWelcomeDisplayed();
        logger.info("Welcome message displayed: {}", isWelcomeVisible);
        
        Assert.assertTrue(isWelcomeVisible, "Login failed: Welcome message not visible.");
        
        logger.info("Test 'testValidLogin' completed successfully.");
    }
}
