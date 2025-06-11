package com.stratapps.xamplify.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
	WebDriver driver;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	private By emailField = By.xpath("//input[@id='username']");
	private By passwordField = By.xpath("//input[@id='password']");
	private By loginButton = By.xpath("//button[@id='submitBitton']");

	private By welcomeMessage = By.xpath("//h4//span[contains(text(), 'Welcome')]");

	// Actions
	public void login(String email, String password) {
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();

		// Wait for welcome message
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
	}

	public boolean isWelcomeDisplayed() {
		return driver.findElement(welcomeMessage).isDisplayed();
	}

}
