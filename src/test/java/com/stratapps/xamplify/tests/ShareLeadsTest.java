package com.stratapps.xamplify.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.stratapps.xamplify.base.BaseTest;
import com.stratapps.xamplify.pages.LoginPage;
import com.stratapps.xamplify.pages.ShareLeadsPage;
import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.DropdownUtil;
import com.stratapps.xamplify.utils.ScreenshotUtil;

public class ShareLeadsTest extends BaseTest {

	private ShareLeadsPage shareleadsPage;
	private static final Logger logger = LogManager.getLogger(ShareLeadsTest.class);

	@BeforeClass
	public void setUpClass() {
		super.setUp();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		shareleadsPage = new ShareLeadsPage(driver);
		logger.info("ShareleadsTest setup completed");
	}

	@Test(priority = 1, description = "Create share lead one at a time", enabled = false)
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

	@Test(priority = 2, description = "Upload CSV for share leads", enabled = false)
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

	@Test(priority = 3, enabled = false)
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

	@Test(priority = 4, description = "Search share leads", enabled = false)
	public void testSearchShareLeads() {
		try {
			// shareleadsPage.navigateToManageShareLeads();
			shareleadsPage.searchShareLead("Auto");
			ScreenshotUtil.captureScreenshot(driver, "SearchShareLead");
		} catch (Exception e) {
			logger.error("Error in testSearchShareLeads", e);
		}
	}

	@Test(priority = 5, enabled = false)
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

	@Test(priority = 6, enabled = false)
	public void manageShareleadsPublishDownload() throws InterruptedException {
		shareleadsPage.navigateToManageShareLeads();
		shareleadsPage.publishAndDownloadShareLeadFlow();
	}

	@Test(priority = 7, enabled = false)
	public void manageShareleadsCopy() throws InterruptedException, AWTException, IOException {
		logger.info("Hovering on Shared Leads.");

		logger.info("Clicking 'Copy' icon.");
		shareleadsPage.clickCopyIcon();

		logger.info("Clicking 'Save As' button.");
		shareleadsPage.clickSaveAsButton();
	}

	@Test(priority = 8, enabled = false)
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
		  //shareleadsPage.sortTilesAndEmailReport();
	      
	    }

	    @Test(priority = 11, enabled = false)
	    public void manageShareleadstilesExportexcel() throws Exception {
	    
	    	
	    	shareleadsPage.gearIconFromTiles();
	    	 
	    	shareleadsPage.enterListName("AutoSlist");
	        Thread.sleep(1000);
	        
	        shareleadsPage.selectLegalBasis("Legitimate interest - prospect/lead");
	        Thread.sleep(1000);
	        
	        shareleadsPage.clickSave();
	        Thread.sleep(1000);
	    	
	    	
	    	
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@AfterClass
	public void tearDownClass() {
		super.tearDown();
		logger.info("ShareleadsTest teardown completed");
	}
}