package com.stratapps.xamplify.pages;

import com.stratapps.xamplify.utils.ActionUtil;
import com.stratapps.xamplify.utils.CSVUtil;
import com.stratapps.xamplify.utils.ElementUtil;
import com.stratapps.xamplify.utils.ScreenshotUtil;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ShareLeadsPage {
    WebDriver driver;
	private WebDriverWait wait;

    public ShareLeadsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    }

    // Locators
    private final By HOVER_SHARE_LEADS = By.xpath("//*[starts-with(text(),'Share Leads')]");
    private final By ADD_SHARE_LEADS = By.xpath("//span[normalize-space()='Add Share Leads']");
    private final By MANAGE_SHARE_LEADS = By.xpath("//span[normalize-space()='Manage Share Leads']");
    private final By SH_ONE_AT_TIME = By.xpath("//span[normalize-space()=\"One at a Time\"]");
    private final By SH_EMAIL_ID = By.xpath("//input[@id='email1']");
    private final By SH_FIRST_NAME = By.xpath("//input[@id='firstName']");
    private final By SH_LAST_NAME = By.xpath("//input[@id='lastName']");
    private final By SH_COMPANY = By.xpath("//input[@type='company']");
    private final By SH_LEGAL_BASIS = By.xpath("//*[@id=\"multiselectelement\"]//span[3]/input");
    private final By SH_MOBILE_NO = By.xpath("//*[@id=\"blur-content-div\"]//app-country-phone-input//input");
    private final By SH_FLAG = By.xpath("//*[@id=\"blur-content-div\"]//app-country-phone-input//div/span[1]");
    private final By SH_FLAG_CODE = By.xpath("//*[@id=\"blur-content-div\"]//app-country-phone-input//div[@class=\"search-box\"]/input");
    private final By SH_FLAG_CODE_SELECT = By.xpath("//*[@id=\"blur-content-div\"]//app-country-phone-input//li/span[2]");
    private final By SH_SCROLL = By.xpath("//*[@class='modal-body border-bottom-1px popup_body']");
    private final By SHARE_LEADS_ADD = By.xpath("//div[@id='addContactModal']//button[2]");
    private final By SHARE_LEADS_SAVE = By.xpath("//button[@id=\"sample_editable_1_new\"]/span");
    private final By SHARE_LEADS_ACCEPT = By.xpath("//div[@id=\"tcModal\"]//button[2]");
    private final By SH_CSV_CLICK = By.xpath("//input[@type='file']");
    private final By SEARCH_BAR = By.xpath("//div[@id='manageContacts']//input");
    private final By EDIT_BUTTON = By.xpath("(//*[@id='partner_contact_list']//a[2]/i)[1]");
    private final By EDIT_ICON = By.xpath("(//i[@class='fa fa-pencil-square-o IconCustomization'])[1]");
    private final By PUBLISH_ICON = By.xpath("(//*[@id='partner_contact_list']//td[7]/div/a[1]/i)[1]");
    private final By DELETE_ICON = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[1]");
    private final By DELETE_CONFIRM = By.xpath("//button[@class='swal2-confirm styled']");
    private final By DROPDOWN_LOCATOR = By.xpath("//div[@class=\"col-xs-12 col-md-12 col-sm-12\"]//div[@class=\"col-xs-12 col-sm-6 col-lg-6 p0 pull-right\"]//select");

    private final By copyIcon = By.xpath("//table[@id='partner_contact_list']//tr[1]//td//i[contains(@class, 'fa-files-o') and contains(@class, 'IconCustomization')]");
    //private final By saveAsButton = By.xpath("//button[contains(text(),'Save As')]");
    private final By saveChangesButton = By.xpath("//div[contains(@class,'modal-footer')]//button[.//span[contains(normalize-space(), 'Save changes')]]");
    //private By deleteIcon = By.xpath("(//i[contains(@class,'fa-trash')])[1]");  // Adjust if needed
    private By confirmDeleteYes = By.xpath("//button[normalize-space()='Yes']");
    private final By deleteIconLocator = By.xpath("(//i[contains(@class,'fa-trash')])[1]");


    
    
    
    public void hoverOnShareLeads() throws InterruptedException {
        Thread.sleep(3000);
        ActionUtil.hoverAndClick(driver, HOVER_SHARE_LEADS, ADD_SHARE_LEADS);
        ElementUtil.sendText(By.id("contactListName"), "AutoSlist" + System.currentTimeMillis(), driver);
    }

    public void createOneAtATimeLead() throws Exception {
        ElementUtil.click(SH_ONE_AT_TIME, driver);
        ElementUtil.sendText(SH_EMAIL_ID, "AutoLead" + System.currentTimeMillis() + "@test.com", driver);
        ElementUtil.sendText(SH_FIRST_NAME, "Auto", driver);
        ElementUtil.sendText(SH_LAST_NAME, "Test", driver);
        ElementUtil.sendText(SH_COMPANY, "Xamplify", driver);
        ElementUtil.sendText(SH_LEGAL_BASIS, "Legitimate interest - prospect/lead", driver);
        ElementUtil.sendKey(SH_LEGAL_BASIS, Keys.ENTER, driver);

        ElementUtil.getById(driver, "title").sendKeys("QA Engineer");
        ElementUtil.getById(driver, "address").sendKeys("123 Street");
        ElementUtil.getById(driver, "city").sendKeys("Hyderabad");
        ElementUtil.getById(driver, "state").sendKeys("Telangana");
        ElementUtil.getById(driver, "zip").sendKeys("500001");

        WebElement scrollableDiv = driver.findElement(SH_SCROLL);
        ActionUtil.scrollInsideElement(driver, scrollableDiv, 500);

        driver.findElement(SH_FLAG).click();
        Thread.sleep(1000);
        driver.findElement(SH_FLAG_CODE).sendKeys("+91");
        Thread.sleep(1000);
        driver.findElement(SH_FLAG_CODE_SELECT).click();
        driver.findElement(SH_MOBILE_NO).sendKeys("9876543210");

        driver.findElement(SHARE_LEADS_ADD).click();
    }

    public void uploadCSVLeads() throws Exception {
        // FIXED: Replaced with proper String[] array for CSV rows
    	
    	List<String[]> data = Arrays.asList(
    		    new String[]{"Auto", "Test", "Company", "Engineer", "auto.test@test.com", "Street", "City", "ST", "12345", "Country", "+91 1234567890"},
    		    new String[]{"John", "Doe", "TestCorp", "QA", "john.doe@test.com", "456 Road", "Town", "ST", "56789", "Country", "+91 1231231234"}
    		);

    	
    	
    	String path = CSVUtil.generateCSV(data);
        driver.findElement(SH_CSV_CLICK).sendKeys(path);
        ElementUtil.sendText(SH_LEGAL_BASIS, "Legitimate interest - prospect/lead", driver);
        ElementUtil.sendKey(SH_LEGAL_BASIS, Keys.ENTER, driver);
    }

    public void clickSaveAndAccept() throws InterruptedException {
        ElementUtil.click(SHARE_LEADS_SAVE, driver);
        Thread.sleep(1000);
        ElementUtil.click(SHARE_LEADS_ACCEPT, driver);
    }

    public void navigateToManageShareLeads() throws InterruptedException {
        Thread.sleep(1000);
        ActionUtil.hoverAndClick(driver, HOVER_SHARE_LEADS, MANAGE_SHARE_LEADS);
    }

    public void editShareLeadDetails() throws InterruptedException {
        Thread.sleep(5000);
        ElementUtil.click(EDIT_BUTTON, driver);
        Thread.sleep(2000);
        ElementUtil.click(EDIT_ICON, driver);
        ElementUtil.getById(driver, "lastName").sendKeys("1");
        WebElement mobileField = driver.findElement(SH_MOBILE_NO);
        mobileField.clear();
        mobileField.sendKeys("+91 9876543210");
        ElementUtil.click(SHARE_LEADS_ADD, driver);
    }
    
    public void saveEditedLeadAndConfirm() throws InterruptedException {
        ElementUtil.getById(driver, "save&delete_button").click();
        ElementUtil.getById(driver, "save_button").click();
        Thread.sleep(2000);

        ElementUtil.click(By.xpath("//div[@id=\"tcModal\"]//button[2]"), driver);
        Thread.sleep(1000);
    }
    
    
    
    
    
    
    
    public void publishAndDownloadShareLeadFlow() throws InterruptedException {
        Thread.sleep(65000);

        ElementUtil.click(By.xpath("//table[@id='partner_contact_list']//tr[1]//td//i[contains(@class, 'fa-share') and contains(@class, 'IconCustomization')]"), driver);
        Thread.sleep(3000);

        ElementUtil.sendText(By.xpath("(//input[@id='sort-text'])[1]"), "PartnerAuto", driver);
        Thread.sleep(2000);
        ElementUtil.sendKey(By.xpath("(//input[@id='sort-text'])[1]"), Keys.ENTER, driver);
        Thread.sleep(2000);

        ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//table//i[@class='fa fa-angle-right']"), driver);
        Thread.sleep(1000);
        ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//table//th[1]//input"), driver);
        Thread.sleep(1000);
        ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//button//span"), driver);
        Thread.sleep(1000);

        ScreenshotUtil.captureScreenshot(driver, "Published_Shareleads");
        Thread.sleep(1000);
        ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//button[contains(text(),\"Close\")]"), driver);

        Thread.sleep(55000);
        ElementUtil.click(By.xpath("(//i[@class=\"fa fa-eye table-eye-circle IconCustomization\"])[1]"), driver);
        Thread.sleep(2000);
        ElementUtil.click(By.xpath("//div[@id=\"listSharedDetailsModal\"]//a[@class=\"close-circle\"]"), driver);
        Thread.sleep(3000);

        ElementUtil.click(By.xpath("(//i[@class='fa fa-download IconCustomization'])[1]"), driver);
        Thread.sleep(2000);
    }

    
    
    
    
    

    public void searchShareLead(String query) throws InterruptedException {
        ElementUtil.sendText(SEARCH_BAR, query, driver);
        ElementUtil.sendKey(SEARCH_BAR, Keys.ENTER, driver);
        Thread.sleep(3000);
    }

    public void publishAndDownloadShareLead() throws InterruptedException {
        ElementUtil.click(PUBLISH_ICON, driver);
        Thread.sleep(2000);
    }

	/*
	 * public void deleteShareLead() throws InterruptedException {
	 * ElementUtil.click(DELETE_ICON, driver); Thread.sleep(1000);
	 * ElementUtil.click(DELETE_CONFIRM, driver); }
	 */

	public By getDropdownLocator() {
		// TODO Auto-generated method stub
	    return DROPDOWN_LOCATOR;
	}
	
	
	
	// Actions
    public void clickCopyIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(copyIcon)).click();
    }

    public void clickSaveAsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
    }
	
   /* public void clickDeleteIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
    }*/

    public void confirmDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteYes)).click();
    }
	
    public boolean clickDeleteIconWithRetry() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        for (int attempt = 1; attempt <= 5; attempt++) {
            try {
                WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(deleteIconLocator));
                deleteIcon.click();
                System.out.println("✅ Clicked delete icon on attempt " + attempt);
     
                
                ElementUtil.click(DELETE_CONFIRM, driver);

                
                System.out.println("✅ Delete icon clicked successfully on attempt " + attempt);
                return true;
            } catch (StaleElementReferenceException e) {
                System.out.println("⚠️ StaleElementReferenceException on attempt " + attempt);
                sleep(1000);
            } catch (TimeoutException e) {
                System.out.println("⏳ Timeout waiting for delete icon on attempt " + attempt);
                break;
            } catch (Exception e) {
                System.out.println("❌ Exception during delete icon click: " + e.getMessage());
                break;
            }
        }

        return false;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
    // Add other reusable methods for share leads features here
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	
