package com.stratapps.xamplify.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import com.stratapps.xamplify.base.BaseTest;
import com.stratapps.xamplify.pages.LoginPage;
import com.stratapps.xamplify.pages.ShareLeadsPage;
import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.DropdownUtil;
import com.stratapps.xamplify.utils.ScreenshotUtil;

public class ShareLeadsTest extends BaseTest {

	private ShareLeadsPage shareleadsPage;
	private static final Logger logger = LogManager.getLogger(ShareLeadsTest.class);
	private WebDriverWait wait;

	@BeforeClass
	public void setUpClass() {
		super.setUp();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		shareleadsPage = new ShareLeadsPage(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		logger.info("ShareleadsTest setup completed");
	}

	@Test(priority = 1, description = "Create share lead one at a time", enabled = true)
	public void testCreateOneAtATimeShareLead() {
		logger.info("Starting test: Create share lead one at a time");
		try {
			shareleadsPage.hoverOnShareLeads();
			shareleadsPage.createOneAtATimeLead();
			shareleadsPage.clickSaveAndAccept();
			ScreenshotUtil.captureScreenshot(driver, "OneAtATime_ShareLead");
		} catch (Exception e) {
			logger.error("Error in testCreateOneAtATimeShareLead", e);
		}
	}

	@Test(priority = 2, description = "Upload CSV for share leads", enabled = true)
	public void testUploadCSVShareLead() {
		logger.info("Starting test: Upload CSV for share leads");
		try {
			shareleadsPage.hoverOnShareLeads();
			shareleadsPage.uploadCSVLeads();
			shareleadsPage.clickSaveAndAccept();
			ScreenshotUtil.captureScreenshot(driver, "UploadCSV_ShareLead");
		} catch (Exception e) {
			logger.error("Error in testUploadCSVShareLead", e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void manageshareleadsEditAddsharelead() throws Exception {
		Thread.sleep(2000);
		shareleadsPage.navigateToManageShareLeads();

		Thread.sleep(65000); // Wait to load

		// Edit share lead details
		shareleadsPage.editShareLeadDetails();

		// Upload CSV data
		shareleadsPage.uploadCSVLeads();
		Thread.sleep(2000);

		shareleadsPage.saveEditedLeadAndConfirm();

		logger.debug("Done creation sharelead using edit option through csv");
	}

	@Test(priority = 4, description = "Search share leads", enabled = true)
	public void testSearchShareLeads() {
		try {
			// shareleadsPage.navigateToManageShareLeads();
			shareleadsPage.searchShareLead("Auto");
			ScreenshotUtil.captureScreenshot(driver, "SearchShareLead");
		} catch (Exception e) {
			logger.error("Error in testSearchShareLeads", e);
		}
	}

	@Test(priority = 5, enabled = true)
	public void shareleadsDropdown() throws InterruptedException {
		Thread.sleep(3000);

		// Selecting multiple indices with a wait using DROPDOWN_LOCATOR from page class
		int[] indices = { 6, 5, 4 };
		for (int index : indices) {
			DropdownUtil.selectByIndex(driver, shareleadsPage.getDropdownLocator(), index);
			Thread.sleep(1000); // Optional short wait for UI update
		}

		Thread.sleep(2000);
	}

	@Test(priority = 6, enabled = true)
	public void manageShareleadsPublishDownload() throws InterruptedException {
		shareleadsPage.navigateToManageShareLeads();
		shareleadsPage.publishAndDownloadShareLeadFlow();
	}

	@Test(priority = 7, enabled = true)
	public void manageShareleadsCopy() throws InterruptedException, AWTException, IOException {
		logger.info("Hovering on Shared Leads.");

		logger.info("Clicking 'Copy' icon.");
		shareleadsPage.clickCopyIcon();

		logger.info("Clicking 'Save As' button.");
		shareleadsPage.clickSaveAsButton();
	}

	@Test(priority = 8, enabled = true)
	public void manageShareleadsDelete() {
		logger.info("🧪 Starting test: manageShareleadsDelete");

		boolean isDeleted = shareleadsPage.clickDeleteIconWithRetry();

		Assert.assertTrue(isDeleted, "❌ Failed to click delete icon after retries.");
		logger.info("✅ Delete icon was clicked successfully.");
	}

	@Test(priority = 9, enabled = true)
	public void testManageShareleadsAllTilesFilterSearch() throws InterruptedException {
		shareleadsPage.navigateToManageShareLeads();
		Thread.sleep(55000); // Optional: Replace with explicit wait if needed
		shareleadsPage.clickAllTile();
		shareleadsPage.clickFilterIcon();
		shareleadsPage.selectFieldName("Job Title");
		shareleadsPage.selectCondition("Contains");
		shareleadsPage.sendValue("qa");
		shareleadsPage.applyFilter();
	}

	@Test(priority = 10, enabled = true)
	public void manageShareleadsAlltilesSortEmailreports() throws Exception {

		shareleadsPage.sortByIndex(1); // Use appropriate index for email sorting
		shareleadsPage.EmailReport();

	}

	@Test(priority = 11, enabled = true)
	public void manageShareleadsAlltileCreateList() throws Exception {

		shareleadsPage.gearIconFromTiles();

		shareleadsPage.enterListName("AutoSlist");

		shareleadsPage.selectLegalBasis("Legitimate interest - prospect/lead");

		shareleadsPage.clickSave();

	}

	@Test(priority = 12, enabled = true)
	public void manageShareleadsValidtiles() throws Exception {
		// Navigate to Manage Share Leads and wait for page to load
		shareleadsPage.navigateToManageShareLeads();

		shareleadsPage.clickValidTile();

		shareleadsPage.clickFilterIcon();
		shareleadsPage.selectFieldName("Job Title");
		shareleadsPage.selectCondition("Contains");
		shareleadsPage.sendValue("qa");
		shareleadsPage.applyFilter();

		shareleadsPage.EmailReport();

		shareleadsPage.sortBy("Email (A-Z)");

		shareleadsPage.searchList("Test1");

	}

	@Test(priority = 13, enabled = true)
	public void manageShareleadsExcludetiles() throws Exception {
		// Navigate to Manage Share Leads and wait for page to load
		shareleadsPage.navigateToManageShareLeads();

		// Attempt to click the Exclude tile only if it's enabled
		if (shareleadsPage.isExcludeTileEnabled()) {
			shareleadsPage.clickExcludeTile();

			shareleadsPage.clickFilterIcon();
			shareleadsPage.selectFieldName("Job Title");
			shareleadsPage.selectCondition("Contains");
			shareleadsPage.sendValue("qa");
			shareleadsPage.applyFilter();

			shareleadsPage.EmailReport();

			shareleadsPage.sortBy("Email (A-Z)");

			shareleadsPage.searchList("Test1");

		} else {
			System.out.println("Excluded count is 0 & button is disabled, cannot click.");
		}
	}

	@Test(priority = 14, enabled = true)
	public void manageShareleadsUndeliverabletiles() throws Exception {
		// Navigate to Manage Share Leads and wait for page to load
		shareleadsPage.navigateToManageShareLeads();
		if (shareleadsPage.isUndeliverableTileEnabled()) {
			shareleadsPage.clickUndeliverableTile();
			shareleadsPage.applyFilter();

			shareleadsPage.EmailReport();
		}

		else {
			System.out.println("Undeliverable count is 0 & button is disabled, cannot click.");
		}

	}

	@Test(priority = 15, enabled = true)
	public void manageShareleadsUnsubscribetiles() throws Exception {
		// Navigate to Manage Share Leads and wait for page to load
		shareleadsPage.navigateToManageShareLeads();

		if (shareleadsPage.isunsubscribeTileEnabled()) {
			shareleadsPage.clickUnsubscribeTile();

			shareleadsPage.applyFilter();

			shareleadsPage.EmailReport();
		} else {
			System.out.println("Unsubscribetile count is 0 & button is disabled, cannot click.");
		}

	}

	@AfterClass
	public void tearDownClass() {
		super.tearDown();
		logger.info("ShareleadsTest teardown completed");
	}
}