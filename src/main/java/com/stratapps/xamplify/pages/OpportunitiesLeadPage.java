package com.stratapps.xamplify.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.security.Key;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stratapps.xamplify.utils.ActionUtil;
import com.stratapps.xamplify.utils.DropdownUtil;
import com.stratapps.xamplify.utils.ElementUtil;
import com.stratapps.xamplify.utils.ScreenshotUtil;
import com.stratapps.xamplify.utils.WaitUtil;
import com.stratapps.xamplify.utils.xamplifyUtil;

public class OpportunitiesLeadPage {

	WebDriver driver;
	private WebDriverWait wait;
    long timestamp = System.currentTimeMillis();

	public OpportunitiesLeadPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}	
	// Locators
    public static final By opportunities = By.xpath("//*[starts-with(text(),'Opportunities')]");
    public static final By opportunitiesManageLeads = By.xpath("//*[starts-with(text(),'Manage Leads')]");
    public static final By opportunitiesManageDeals = By.xpath("//*[starts-with(text(),'Manage Deals')]");
    public static final By addLeadButton = By.xpath("//button[contains(@class,'button_blue bgcolor-unset p')]");
    public static final By leadForCompany = By.xpath("//select[@class='form-control ng-untouched ng-pristine ng-valid']");
    public static final By pipelineId = By.id("createdForPipelineId");
    public static final By pipelineStageId = By.id("createdForPipelineStageId");
    public static final By addleadCommentId = By.id("leadComment");
    public static final By firstNameId= By.xpath("//input[@id='First_Name']");
    public static final By lastNameId= By.xpath("//input[@id='Last_Name']");
    public static final By companyId= By.xpath("//input[@id='Company']");
    public static final By emailId= By.xpath("//input[@id='Email']");
    public static final By phoneNumberId = By.xpath("//input[@id='Phone_Number']");
    public static final By websiteId = By.xpath("//input[@id='Website']");
    public static final By address = By.xpath("//textarea[@class='form-control ng-tns-c35-3 ng-pristine ng-valid ng-touched']");
    public static final By cityId = By.xpath("//input[@id='City']");
    public static final By stateId = By.xpath("//input[@id='State']");
    public static final By countryId= By.xpath("//input[@id='Country']");
    public static final By postalCodeId = By.xpath("//input[@id='Postal_Code']");
    public static final By jobTitleId = By.xpath("//input[@id='Title']");
    public static final By amountId = By.xpath("//input[@id='Amount']");
    public static final By industryId = By.xpath("//input[@id='Industry']");
    public static final By regionID = By.xpath("//input[@id='Region']");
    public static final By saveLead = By.id("saveLead");
    public static final By campaignView = By.xpath("//i[contains(@class,'fa fa-folder-open p')]");
    public static final By campaignViewleadslist = By.xpath("//a[@data-original-title='View leads for this campaign']/i");
    public static final By listView = By.xpath("//i[contains(@class,'fa fa-th-list p')]");
    public static final By eMailReport = By.xpath("//i[contains(@class,'fas fa-file-export p')]");
    public static final By leadSearch = By.xpath("//input[@class='form-control pr-pl ml ng-untouched ng-pristine ng-valid']");
    public static final By leadsSearchButton = By.xpath("//button[contains(@class,'search-box-item-click top mb_v_search top')]");
    public static final By leadsSearchCancel = By.xpath("//button[contains(@class,'glyphicon glyphicon-remove search-box-item-clear ng-tns-c')]");
    public static final By leadsFilter = By.xpath("//i[contains(@class,'fa fa-filter p')]");
    public static final By leadsFilterAddedBy = By.xpath("(//ejs-dropdownlist[@id='ddlelement'])[1]");
    public static final By leadsFilterAddedByInput = By.xpath("(//ejs-dropdownlist[@id=\"ddlelement\"])[1]//input");

    public static final By leadsFilterStatus = By.xpath("(//select[@name=\"ddlelement\"])[2]");
   // public static final By leadsFilterAddedFor = By.xpath("(//ejs-dropdownlist[@id=\"ddlelement\"])[3]");
    public static final By leadsFilterFormDate = By.xpath("(//input[@id=\"flat-picker\"])[1]");
    public static final By leadsFilterToDate = By.xpath("(//input[@id=\"flat-picker\"])[2]");
    public static final By leadsFilterFormDatePicker = By.xpath("(//span[@aria-label='June 1, 2025'])[5]");
    public static final By leadsFilterToDatePicker = By.xpath("(//span[@aria-label='June 25, 2025'])[6]");

    public static final By totalLeads = By.xpath("(//span[@id='permhref'])[1]");
    public static final By totalLeadCount = By.xpath("//div[@id='deals-page-content-div']//div[@class='number']/span)[1]");
    public static final By wonLeads = By.xpath("//div[@class='desc' and contains(text(),'Won Leads')]");
    public static final By wonLeadsCount = By.xpath("//div[@id='deals-page-content-div']//div[@class='number']/span)[2]");
    public static final By lostLeads = By.xpath("//div[@class='desc' and contains(text(),'Lost Leads')]");
    public static final By lostLeadsCount = By.xpath("//div[@id='deals-page-content-div']//div[@class='number']/span)[1]");
    public static final By convertedLeads = By.xpath("//div[@class='desc' and contains(text(),'Converted Leads')]");
    public static final By convertedLeadsCount = By.xpath("//div[@id='deals-page-content-div']//div[@class='number']/span)[1]");
    // The below values are not unique. To use it uniquely, first search for the lead then perform what action you want to perform. or use index
    public static final By leadView = By.xpath("(//i[@class='fa fa-eye mr5 IconCustomization'])[1]");
    public static final By leadViewComments = By.xpath("//textarea[contains(@class,'ng-pristine ng-valid')]");
    public static final By leadViewCommentsSubmit = By.xpath("//button[contains(@class,'btn btn-primary transition mb')]");
    public static final By leadViewCloseButton = By.xpath("(//i[@class='fa fa-times'])[2]");
    public static final By leadEdit = By.xpath("(//i[@class='fa fa-edit mr5 IconCustomization'])[1]");
    public static final By leadUpdate = By.xpath("//button[@id='saveLead']");
    public static final By leadComments = By.xpath("(//i[@class='fa fa-comments mr5 IconCustomization'])[1]");
    public static final By leadCommentTxtArea = By.xpath("//textarea[contains(@class,'ng-pristine ng-valid')]");
    public static final By leadCommentSubmit = By.xpath("//button[contains(@class,'btn btn-primary transition mb')]");
    public static final By leadCommentWindowClose = By.xpath("//a[@class='close-circle' and @title='Close']");
    public static final By leadDelete = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[4]");
    public static final By leadDeleteReject = By.xpath("//button[@class='swal2-cancel styled']");
    public static final By leadDeleteApprove = By.xpath("//button[contains(@class,'swal2-confirm styled')]");
    public static final By leadReacordCnt = By.xpath("(//span[starts-with(text(),'Total Records :')])[1]"); 
    public static final By leadNextPage = By.xpath("//i[@class='fa fa-angle-right' and contains(@title,'Next')]");
    public static final By leadLastPage = By.xpath("//i[@class='fa fa-angle-double-right' and contains(@title,'Last')]");
    public static final By leadPreviousPage = By.xpath("(//i[@class='fa fa-angle-left' and contains(@title,'Previous')])[1]");
    public static final By leadFirstPage = By.xpath("(//i[@class='fa fa-angle-double-left'])[1]");
    public static final By leadPageCntDrpDwn = By.xpath("(//select[contains(@class,'form-control margin-top pointer mt')])[1]");
    public static final By registerDeal = By.xpath("(//span[contains(text(),'Register Deal')])[3]");
    
    
	/**
	 * @hoverOnOpportunities written by ganesh
	 */
	public void hoverOnOpportunities_ManageLeads() throws Exception {
		Thread.sleep(3000);
		ActionUtil.hover(driver, opportunities); // hover only
		ActionUtil.hoverAndClick(driver, opportunitiesManageLeads); // click after hover
	}

	public void leadSearch() throws InterruptedException {
		WaitUtil.waitAndSendKeys(driver, leadSearch, "lead", 10);
		WaitUtil.waitAndClick(driver, leadsSearchButton, 10);
		ScreenshotUtil.captureScreenshot(driver, "LeadSearch");	
		/* WebElement firstResult = driver.findElement(By.xpath("xpath_for_first_result")).getText(); 
		 * assertTrue(resultText.contains("AutoLead_FN_175"), "Search result validation failed!");*/
    }
	
	public void removeLeadSearch() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadsSearchCancel, 10);
    }
	
	public void leadEmailReport() throws InterruptedException {
		WaitUtil.waitAndClick(driver, eMailReport, 10);
		ScreenshotUtil.captureScreenshot(driver, "eMailReport");		
		/* WebElement respondMessage = driver.findElement(By.xpath("xpath_for_first_result")).getText(); 
		 * assertTrue(resultText.contains("AutoLead_FN_175"), "Search result validation failed!");*/
    }
	
	public void LeadForm() throws InterruptedException {
		WaitUtil.waitAndSendKeys(driver, firstNameId, "Lead", 5);
		WaitUtil.waitAndSendKeys(driver, lastNameId, ""+timestamp, 5);
		WaitUtil.waitAndSendKeys(driver, companyId, "Trend", 5);
		WaitUtil.waitAndSendKeys(driver, emailId, "test.automation@gmail.com", 5);
		WaitUtil.waitAndSendKeys(driver, phoneNumberId, "9998889990", 5);
		WaitUtil.waitAndSendKeys(driver, websiteId, "www.trend.com", 5);
		//WaitUtil.waitAndSendKeys(driver, address, "", 5);
		WaitUtil.waitAndSendKeys(driver, cityId, "Hyderabad", 5);
		WaitUtil.waitAndSendKeys(driver, stateId, "Telangana", 5);
		WaitUtil.waitAndSendKeys(driver, countryId, "India", 5);
		WaitUtil.waitAndSendKeys(driver, postalCodeId, "500085", 5);
		WaitUtil.waitAndSendKeys(driver, jobTitleId, "QA", 5);
		//WaitUtil.waitAndSendKeys(driver, amountId, "", 5);		
		WaitUtil.waitAndSendKeys(driver, industryId, "Fasion", 5);
		WaitUtil.waitAndSendKeys(driver, regionID, "SouthIndia", 5);
	}

	public void addLead() throws InterruptedException {
		WaitUtil.waitAndClick(driver, addLeadButton, 10);
		WaitUtil.waitForDropdownToBeReady(driver, leadForCompany, 5);
		DropdownUtil.selectByVisibleText(driver, leadForCompany, "xAmplify");
		WaitUtil.waitForDropdownToBeReady(driver, pipelineStageId, 5);
		DropdownUtil.selectByVisibleText(driver, pipelineStageId, "Opened");
		WaitUtil.waitAndSendKeys(driver, addleadCommentId, "Lead Created through automation", 5);
		LeadForm();
		WaitUtil.waitAndClick(driver, saveLead, 5);
		Thread.sleep(3000);
		ScreenshotUtil.captureScreenshot(driver, "NewLeadCreated");		
	}
	
	public void leadView() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadView, 5);
		WaitUtil.waitAndSendKeys(driver, leadCommentTxtArea, "comment added", 5);
		Thread.sleep(2000);
		WaitUtil.waitAndClick(driver, leadCommentSubmit, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
		WaitUtil.waitAndClick(driver, leadViewCloseButton, 10);
		Thread.sleep(2000);
		Thread.sleep(3000);
		ScreenshotUtil.captureScreenshot(driver, "leadView");		
	}
	
	public void editLead() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadEdit, 10);
		WaitUtil.waitForDropdownToBeReady(driver, pipelineStageId, 5);
		DropdownUtil.selectByVisibleText(driver, pipelineStageId, "Converted");
		WaitUtil.waitAndSendKeys(driver, addleadCommentId, "Lead updated through automation", 5);
		WaitUtil.waitAndSendKeys(driver, firstNameId, "Leads", 5);
		//WaitUtil.waitAndSendKeys(driver, lastNameId, ""+timestamp, 5);
		WaitUtil.waitAndSendKeys(driver, companyId, "Trends", 5);
		//WaitUtil.waitAndSendKeys(driver, emailId, "test.automation@gmail.com", 5);
		WaitUtil.waitAndSendKeys(driver, phoneNumberId, "9998889999", 5);
		WaitUtil.waitAndSendKeys(driver, websiteId, "www.trends.com", 5);
		//WaitUtil.waitAndSendKeys(driver, address, "", 5);
		WaitUtil.waitAndSendKeys(driver, cityId, "Hyderabad_U", 5);
		WaitUtil.waitAndSendKeys(driver, stateId, "Telangana_U", 5);
		WaitUtil.waitAndSendKeys(driver, countryId, "India_U", 5);
		WaitUtil.waitAndSendKeys(driver, postalCodeId, "500085", 5);
		WaitUtil.waitAndSendKeys(driver, jobTitleId, "QA_U", 5);
		//WaitUtil.waitAndSendKeys(driver, amountId, "", 5);		
		WaitUtil.waitAndSendKeys(driver, industryId, "Fasion_U", 5);
		WaitUtil.waitAndSendKeys(driver, regionID, "SouthIndia_U", 5);
		WaitUtil.waitAndClick(driver, saveLead, 5);
		Thread.sleep(3000);
		ScreenshotUtil.captureScreenshot(driver, "editLeadUpdated");	
	}
	
	public void addcomment() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadComments, 10);
		WaitUtil.waitAndSendKeys(driver, leadCommentTxtArea, "comment added through automation", 10);
		WaitUtil.waitAndClick(driver, leadCommentSubmit, 10);
		WaitUtil.waitAndClick(driver, leadCommentWindowClose, 10);
		Thread.sleep(3000);
		ScreenshotUtil.captureScreenshot(driver, "AddedCommentToLead");
	}
	
	public void leadFilter() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadsFilter, 10);
		Thread.sleep(2000);
		WaitUtil.waitAndClick(driver, leadsFilterAddedBy, 10);
		//WaitUtil.waitAndSendKeys(driver, leadsFilterAddedBy, "partnerautomate@gmail.com", 10);
	//	xamplifyUtil.sendKeyEvent(driver, leadsFilterAddedBy, Key.);
	}
	
	public void deletelead() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadDelete, 10);
		WaitUtil.waitAndClick(driver, leadDeleteApprove, 10);
		Thread.sleep(3000);
		ScreenshotUtil.captureScreenshot(driver, "DeleteLead");
	}
	
	public void CampainView() throws InterruptedException {
		WaitUtil.waitAndClick(driver, campaignView, 10);
		WaitUtil.waitAndClick(driver, campaignViewleadslist, 200);
		ActionUtil.hover(driver, campaignViewleadslist);
		Thread.sleep(3000);
		ScreenshotUtil.captureScreenshot(driver, "campaignViewleadslist");
	}
	  
public void leadsPaginationandPageCount() throws InterruptedException {
		WaitUtil.waitAndClick(driver, leadNextPage, 10);
		Thread.sleep(2000);ScreenshotUtil.captureScreenshot(driver, "leadNextPage");		
		WaitUtil.waitAndClick(driver, leadLastPage, 10);
		Thread.sleep(2000);ScreenshotUtil.captureScreenshot(driver, "leadLastPage");		
		WaitUtil.waitAndClick(driver, leadPreviousPage, 10);
		Thread.sleep(2000);ScreenshotUtil.captureScreenshot(driver, "leadPreviousPage");		
		WaitUtil.waitAndClick(driver, leadFirstPage, 10);
		Thread.sleep(2000);ScreenshotUtil.captureScreenshot(driver, "leadFirstPage");
		/*
		 * try { DropdownUtil.selectByValue(driver, leadPageCntDrpDwn, "1");
		 * ScreenshotUtil.captureScreenshot(driver, "leadPageCnt-1");
		 * DropdownUtil.selectByValue(driver, leadPageCntDrpDwn, "2");
		 * ScreenshotUtil.captureScreenshot(driver, "leadPageCnt-2"); } catch (Exception
		 * e) { ScreenshotUtil.captureScreenshot(driver, "No of pages available"); }
		 */
	}
    
    public void leadTiles() {
    	String TotalRecords;
    System.out.println("test1");
    	WaitUtil.waitAndClick(driver, totalLeads, 60);
		 String TotalLeadCount = driver.findElement(totalLeadCount).getText(); 
		 TotalRecords = driver.findElement(leadReacordCnt).getText(); 
		 assertEquals(TotalLeadCount, TotalRecords);
	System.out.println("test2");
	    WaitUtil.waitAndClick(driver, wonLeads, 60);
		 String TotalWonLeadsCount = driver.findElement(wonLeadsCount).getText(); 
		 TotalRecords = driver.findElement(leadReacordCnt).getText(); 
		 assertEquals(wonLeadsCount, TotalWonLeadsCount);
    System.out.println("test3");
		 WaitUtil.waitAndClick(driver, lostLeads, 60);
		 String TotalLostLeadsCount = driver.findElement(lostLeadsCount).getText(); 
		 TotalRecords = driver.findElement(leadReacordCnt).getText(); 
		 assertEquals(TotalLostLeadsCount, TotalRecords);
    System.out.println("test3");
		 WaitUtil.waitAndClick(driver, convertedLeads, 60);
		 String TotalConvertedLeadsCount = driver.findElement(convertedLeadsCount).getText(); 
		 TotalRecords = driver.findElement(leadReacordCnt).getText(); 
		 assertEquals(TotalConvertedLeadsCount, TotalRecords);		 		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //############################################ DEAL #############################################################
    public static final By addDealButton = By.xpath("//button[contains(@class,'button_blue bgcolor-unset p0 btn-transitions mrgt')]");
    public static final By dealCompany = By.xpath("//select[@id='createdForCompanyId']");
    public static final By dealPipeline = By.xpath("//select[@id='createdForPipelineId' and @placeholder='pipelineId']");
    public static final By dealPipeLineStage = By.xpath("//select[@id='createdForPipelineStageId']");
    public static final By dealComment = By.xpath("//textarea[@id='dealComment']");
    public static final By dealAttachdrpDwn = By.xpath("//button[@class='btn Btn-Green mb_btn']");
    public static final By attachLead = By.xpath("//a[contains(text(),'Attach a Lead')]");
    public static final By attachContact = By.xpath("//a[contains(text(),'Attach a Contact')]");
    public static final By attachAddLeadBtn = By.xpath("//i[@class='fa fa-plus  mb-btns']");
    public static final By addLeadPipeline = By.xpath("//select[@id='pipelineId']");
    public static final By addLeadPipelineStage = By.xpath("(//select[@id='createdForPipelineStageId'])[3]");
    public static final By saveLeadButton= By.xpath("(//button[@id='saveLead' and @class='button bgcolor-unset'])[2]");
    public static final By dealCommentSubmit = By.xpath("//button[contains(@class,'btn btn-primary transition mb') and contains(text(),'Submit')]");
//    public static final By = By.xpath("");
    //# below xpath, if we update the numeric value, corresponding record will we get
    public static final By dealAttachaContact = By.xpath("//input[contains(@id,'contactCheckBox')]");
    public static final By dealAttachaLead = By.xpath("//input[contains(@id,'leadCheckBox')]");
    //# Attach Contact index+1 is more or less index value
    public static final By dealContactMoreOrLess = By.xpath("(//button[@class='border-btn-small lightgray-btn'])[5]");
    public static final By dealAttachSelectedButton = By.xpath("//button[@class='button_blue button_disabled bgcolor-unset']");
    public static final By closeAttachContactWindow = By.xpath("//button[@class='close-circle']");
    public static final By searchContact = By.xpath("//input[@class='form-control pr-pl ml ng-pristine ng-valid ng-touched']");
    public static final By closeSearchContact = By.xpath("//button[@class='search-box-item-click top mb_v_search']");
    public static final By changeContact = By.xpath("//button[@class='button_blue bgcolor-unset p0 btn-transitions']");
    public static final By detachContact = By.xpath("(//button[contains(@class,'btn Btn-Gray neg_mrg_top')])[2]");
    public static final By dealAttachLead = By.xpath("//input[contains(@id,'leadCheckBox')]");
    public static final By viewLead = By.xpath("(//a[@id='lead'])[2]");
    public static final By attachSelectedLeadButton = By.xpath("//button[@id='saveLead']");
    public static final By attachSelectedContactButton = By.xpath("//button[@class='button_blue button_disabled bgcolor-unset']");
    public static final By searchLead = By.xpath("//input[@class='form-control pr-pl ml ng-untouched ng-pristine ng-valid']");
    public static final By searchLeadButton = By.xpath("//button[contains(@class,'search-box-item-click top mb_v_search')]");
    public static final By searchCancel = By.xpath("//button[contains(@class,'glyphicon-remove search-box-item-clear')]");
    public static final By dealType = By.xpath("//input[@id='Deal_Type']");
    public static final By dealName = By.xpath("//input[@id='Deal_Name']");
    public static final By dealAmount = By.xpath("//input[@id='Amount']");
    public static final By saveDeal = By.xpath("//button[@id='saveDeal']");
    public static final By dealCampaignView = By.xpath("//i[contains(@class,'fa fa-folder-open p')]");
    public static final By dealListView = By.xpath("//i[contains(@class,'fa fa-th-list p')]");
    public static final By dealsEmailReport = By.xpath("//i[contains(@class,'fas fa-file-export p')]");
    public static final By wonDeals = By.xpath("//div[contains(text(),'Won Deals')]");
    public static final By lostDeals = By.xpath("//div[contains(text(),'Lost Deals')]");
    public static final By totalDeals = By.xpath("//div[contains(text(),'Total Deals')]");
    public static final By clickHeretoEmailReport = By.xpath("//button[@class='btn btn-xs hidden-xs l-g-view']");
    public static final By searchDeals = By.xpath("// input[contains(@class,' ng-pristine ng-valid') and @placeholder='Search']");
    public static final By clickSearchDealIcon = By.xpath("//button[@class='search-box-item-click']");
    public static final By clickCloseSearchDealIcon = By.xpath("//button[@class='glyphicon glyphicon-remove search-box-item-clear']");
    public static final By viewDeal = By.xpath("(//a[@id='deal'])[1]");
    public static final By closeViewDeal = By.xpath("//a[contains(@class,'pull-right close-circle')]");
    public static final By editDeal = By.xpath("(//a[@id='deal'])[2]");
    public static final By deleteDeal = By.xpath("(//a[@id='deal'])[3]");
    public static final By cancelDeleteDeal = By.xpath("//button[contains(text(),'Cancel') and @class='swal2-cancel styled']");
    public static final By acceptDeleteDeal = By.xpath("//button[contains(text(),'Yes, delete it!')]");
    public static final By commentDeal = By.xpath("(//a[@class='custom-icon' and @data-rel='fancybox-button'])[4]");
    public static final By commentHistoryDeal = By.xpath("//textarea[@placeholder='Write your comment here' and @name='comment']");
    public static final By editDealStage = By.xpath("(//a[@data-rel='fancybox-button'])[1]");
    public static final By dealStageValue = By.xpath("//select[contains(@class,'form-control ng-')]");
    public static final By dealStageComment = By.xpath("//textarea[contains(@class,'form-control ng-untouched ng-pristine ng-valid')]");
    public static final By dealStageUpdateBtn = By.xpath("//span[@class='btn btn-primary transition d-inline' and contains(text(),'Update')]");
    public static final By dealStageCancelBtn = By.xpath("(//button[contains(@class,'btn Btn-Gray neg_mrg_top4') and contains(text(),'Cancel')])[2]");
    public static final By totalDealRecords = By.xpath("(//span[normalize-space()='Total Records :'])[1]");
    public static final By dealNextPg = By.xpath("(//i[@title='Next'])[1]");
    public static final By dealLastPg = By.xpath("(//i[@class='fa fa-angle-double-right'])[1]");
    public static final By dealFirstPg = By.xpath("(//i[@class='fa fa-angle-double-left'])[1]");
    public static final By dealPreviousPg = By.xpath("(//i[@class='fa fa-angle-left'])[1]");
    public static final By dealRecordPageCnt = By.xpath("(//span[normalize-space()='Total Records :'])[1]");
    public static final By dealCloseCommentWindow = By.xpath("(//a[@class='close-circle'])[2]");
    public static final By dealCloseDate = By.xpath("//div[contains(@class,'flatpickr-calendar animate showTimeInput open arrow')]//div[@class='dayContainer']//span[@class='flatpickr-day ']");
    public static final By selectCloseDate = By.xpath("(//input[@id='flat-picker'])[1]");
    public static final By selectDealNextMonth = By.xpath("(//span[@class='flatpickr-next-month'])[2]");
    public static final By selectFromDate = By.xpath("(//input[@id='flat-picker'])[1]");
    public static final By selecttoDate = By.xpath("(//input[@id='flat-picker'])[2]");
    public static final By selectPreviousMonth = By.xpath("(//span[@class='flatpickr-prev-month'])[1]");
    public static final By selectDealFromDate = By.xpath("//div[contains(@class,'flatpickr-calendar animate ')]//div[@class='dayContainer']//span[contains(@class,'flatpickr-day ')]");
    //public static final By selectDealToDate = By.xpath("");
    public static final By dealAddedby = By.xpath("(//ejs-dropdownlist[@id='ddlelement'])[1]");
    public static final By dealStatusSelect = By.xpath("(//span[@class='e-input-group e-control-wrapper e-ddl e-lib e-keyboard'])");
    public static final By dealAddedSelect = By.xpath("");
    public static final By dealAddedfor = By.xpath("(//ejs-dropdownlist[@id='ddlelement'])[3]");
    public static final By dealAddedForSelect = By.xpath("(//ejs-dropdownlist[@id='ddlelement'])[3]");
    public static final By dealAddedByInput = By.xpath("(//input[@class='e-input'])[1]");
    public static final By dealAddedForInput = By.xpath("(//input[@class='e-input'])[3]");
    public static final By dealStatusInput = By.xpath("(//input[@class='e-input'])[2]");
    public static final By dealStatus = By.xpath("(//select[@id='ddlelement_hidden'])[2]");
    public static final By applyDealFilter = By.xpath("//span[contains(@class,'btn Btn-Green transition txt_pd') and contains(text(),'Filter')]");
    public static final By cancelDealFilter = By.xpath("//button[@class='close-circle']");
    public static final By viewLeadsfortheseCamp = By.xpath("(//i[@class='circled-number fa fa-users IconCustomization'])[1]");
    public static final By viewLeadsfortheseCampSearchBox = By.xpath("//input[contains(@class,'form-control pr-pl ml ng-')]");
    public static final By viewLeadsfortheseCampIcon = By.xpath("//button[contains(@class,'search-box-item-click top mb_v_search top')]");
    public static final By viewLeadsfortheseCampClose = By.xpath("//button[contains(@class,'glyphicon glyphicon-remove search-box-item-clear ng-tns-c')]");
    public static final By campLeadNextPg = By.xpath("(//i[@class='fa fa-angle-right' and contains(@title,'Next')])[2]");
    public static final By campLeadPreviousPg = By.xpath("(//i[@class='fa fa-angle-left'])[2]");
    public static final By campLeadLastPg = By.xpath("(//i[@class='fa fa-angle-double-right' and contains(@title,'Last')])[2]");
    public static final By campLeadFirstPg = By.xpath("(//i[@class='fa fa-angle-double-left'])[2]");
    public static final By campViewLeadEdit = By.xpath("(//i[contains(@class,'fa fa-edit mr5 IconCustomization')])[1]");
    //public static final By campViewLeadEdit = By.xpath("(//a[@id='lead'])[2]");
    public static final By campViewLeadPreview = By.xpath("(//i[@class='fa fa-eye mr5 IconCustomization'])[1]");
    public static final By campViewLeadComment = By.xpath("(//i[@class='fa fa-comments IconCustomization'])[1]");
    public static final By campViewLeadDelete = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[1]");
    public static final By campPageDrpDwn = By.xpath("(//select[contains(@class,'form-control margin-top pointer mt_')])[2]");
    public static final By campViewLeadUpdate = By.xpath("(//button[@id='saveLead'])[1]");
    public static final By campViewDealSearchBox = By.xpath("(//input[contains(@class,'form-control ng-')])[2]");
    public static final By campViewDealSearchIcon = By.xpath("// button[contains(@class,'search-box-item-click')]");
    public static final By campViewDealSearchclear = By.xpath("//button[@class='glyphicon glyphicon-remove search-box-item-clear']");
    public static final By viewDealforThisCampaign = By.xpath("(//i[@class='circled-number fa fa-users IconCustomization'])[1]");
    public static final By editDealStageStatus = By.xpath("(//span[@class='fa fa-edit IconCustomization'])[1]");
    public static final By campViewEditDealStage = By.xpath("//select[@id='createdForPipelineStageId']");
    public static final By campViewEditAddComment = By.xpath("//textarea[@id='dealComment']");
    public static final By campViewUpdateDeal = By.xpath("//span[contains(text(),'Update Deal')]");
    public static final By campViewEditClose = By.xpath("//a[contains(@class,'pull-right close-circle mrg')]");
    public static final By campViewDealDelete = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[1]");
    public static final By campViewConfirmDelete = By.xpath("//button[@class='swal2-confirm styled' and contains(text(),'Yes, delete it!')]");
    public static final By campViewCancelDelete = By.xpath("//button[@class='swal2-cancel styled' and contains(text(),'Cancel')]");
    public static final By campViewDealNextPg = By.xpath("(//i[@class='fa fa-angle-right' and @title='Next'])[2]");
    public static final By campViewDealPreviousPg = By.xpath("(//i[@class='fa fa-angle-left'])[2]");
    public static final By campViewDealLastPg = By.xpath("(//i[@class='fa fa-angle-double-right' and @title='Last'])[2]");
    public static final By campViewDealFirstPg = By.xpath("(//i[@class='fa fa-angle-double-left' and @title='First'])[2]");
    public static final By campViewDealDrpDwn = By.xpath("(//select[contains(@class,'form-control margin-top pointer mt')])[2]");
//    public static final By = By.xpath("");
//    public static final By = By.xpath("");

}
