package com.stratapps.xamplify.tests;

import com.stratapps.xamplify.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.stratapps.xamplify.base.BaseTest;

public class LoginTest extends BaseTest {

	@Test
	public void testValidLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("agayatri@stratapps.com", "Xamplify@11");
        Assert.assertTrue(loginPage.isWelcomeDisplayed(), "Login failed: Welcome message not visible.");


	}
}
