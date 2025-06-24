package com.stratapps.xamplify.pages;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.stratapps.xamplify.utils.ActionUtil;
import com.stratapps.xamplify.utils.CSVUtil;
import com.stratapps.xamplify.utils.ElementUtil;
import com.stratapps.xamplify.utils.WaitUtil;

public class TeamVendorPage {

    private WebDriver driver;

    // Locators
    private By teamMenu;
    private By addButton;
    private By firstNameField;
    private By lastNameField;
    private By emailIdField;
    private By groupDropdown;
    private By saveButton;
    private By inviteButton;
    private By inviteEmailField;
    private By inviteSendButton;
    private By uploadCSVInput;
    private By saveCSVButton;
    private By searchField;
    private By searchIcon;
    private By deleteUploadIcon;
    private By exportExcelButton;
    private By refreshButton;
    private By filterButton;
    private By fromDate;
    private By toDate;
    private By dateField;
    private By toDateField;
    private By applyButton;
    private By clearFilter;
    private By previewIcon;
    private By editIcon;
    private By updateButton;
    private By resendEmailIcon;
    private By yesSendIt;
    private By deleteIcon;
    private By yesDelete;
    private By backdrop;
    private By invitecancel;

    public TeamVendorPage(WebDriver driver) {
    	this.driver = driver;
		//this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        initLocators();
    }

    private void initLocators() {
        teamMenu = By.xpath("//span[normalize-space()='Team']");
        addButton = By.xpath("//section[@class='clearfix invite_team_btns d-flex']//span[normalize-space()='Add']");
        firstNameField = By.xpath("//input[@id='firstName']");
        lastNameField = By.xpath("//input[@name='lastName']");
        emailIdField = By.xpath("//input[@name='emailId']");
        groupDropdown = By.xpath("//form[@class='form-horizontal form-bordered form-label-stripped ng-untouched ng-pristine ng-valid']//div[4]/div/div/select");
        saveButton = By.xpath("//span[normalize-space()='Save']");
        inviteButton = By.xpath("//button[normalize-space()='Invite A Team Member']");
        inviteEmailField = By.xpath("(//input[@placeholder='Enter email address(es)'])[2]");
        inviteSendButton = By.xpath("(//span[contains(text(), 'Send')])[2]");
        invitecancel=By.xpath("//div[@id=\"invite_team_member_modal\"]/div/div/div[1]/a/i");
        uploadCSVInput = By.xpath("//input[@type='file']");
        saveCSVButton = By.xpath("//button[@id='right']");
        searchField = By.xpath("(//input[@placeholder='Search'])[1]");
        searchIcon = By.xpath("(//button[@class='search-box-item-click']//i[@class='fa fa-search'])[2]");
        deleteUploadIcon = By.xpath("//a[@data-placement='bottom']//i[@class='fa fa-trash-o trashIconCustomization']");
        exportExcelButton = By.xpath("//button[@class='btn btn-xs  hidden-xs l-g-view']");
        refreshButton = By.xpath("//i[contains(@class,'fa fa-refresh')]");
        filterButton = By.xpath("(//i[contains(@class,'fa fa-filter')])[2]");
        fromDate = By.xpath("//div[contains(@class,'open')]//span[@class='flatpickr-day today']/preceding-sibling::span[2]");
        toDate = By.xpath("//div[contains(@class,'open')]//span[@class='flatpickr-day today']");
        dateField = By.xpath("(//input[@id='flat-picker'])[1]");
        toDateField = By.xpath("(//input[@id='flat-picker'])[2]");
        applyButton = By.xpath("//button[normalize-space()='Apply']");
        clearFilter = By.xpath("//a[normalize-space()='Clear Filter']");
        previewIcon = By.xpath("(//i[@class='fa fa-eye'])[1]");
        editIcon = By.xpath("(//i[@class='fa fa-pencil-square-o IconCustomization'])[2]");
        updateButton = By.xpath("//span[normalize-space()='Update']");
        resendEmailIcon = By.xpath("(//i[@class='fa fa-envelope IconCustomization'])[2]");
        yesSendIt = By.xpath("//button[normalize-space()='Yes, send it!']");
        deleteIcon = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[3]");
        yesDelete = By.xpath("//button[normalize-space()='Yes, delete it!']");
        backdrop = By.cssSelector("div.backdrop");
    }

    public void hoverTeam() {
        ActionUtil.hoverAndClick(driver, teamMenu);
    }

    public void addTeammember() {
        ElementUtil.click(addButton, driver);
        WaitUtil.waitForVisibility(driver, firstNameField,30);
        ElementUtil.sendText(firstNameField, "Mounika", driver);
        ElementUtil.sendText(lastNameField, "K", driver);
        ElementUtil.sendText(emailIdField, "Oneteam" + System.currentTimeMillis() + "@test.com", driver);
        ElementUtil.click(groupDropdown, driver);
        WaitUtil.waitForVisibility(driver, groupDropdown,30);
        ElementUtil.selectDropdownByVisibleText(groupDropdown, "Sales Account Manager", driver);
        
        WaitUtil.waitForPageToLoad(driver, 70);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, saveButton, 60);
        ElementUtil.click(saveButton, driver);
		

    }

    public void inviteTeammember() {
    	WaitUtil.waitForVisibility(driver, inviteButton,60);
        ElementUtil.click(inviteButton, driver);
        WaitUtil.waitForPageToLoad(driver, 70);
		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, inviteEmailField, 60);
        ElementUtil.sendText(inviteEmailField, "invteam" + System.currentTimeMillis() + "@test.com", driver);
        ElementUtil.click(inviteSendButton, driver);
        WaitUtil.waitForPageToLoad(driver, 70);
     		// Wait for backdrop (overlay/spinner) to disappear
     	WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);
     		// Wait for the tile to be visible
     	WaitUtil.waitForVisibility(driver, invitecancel, 60);
        ElementUtil.click(invitecancel, driver);

    }
//    
//    public void uploadCSVTeamMembers() throws Exception {
//        // Step 1: Define team member test data (based on your CSV headers)
//        List<String[]> data = Arrays.asList(
//            new String[] {"mounika.k@xamplify.com","Mounika", "K"},
//            new String[] {"john.doe@xamplify.com", "John", "Doe" }
//        );
//
//        // Step 2: Generate the CSV file dynamically
//        String path = CSVUtil.generateCSV(data); // Assume CSVUtil saves and returns the file path
//
//        // Step 3: Upload the CSV using sendKeys to file input
//        
//        WaitUtil.waitForVisibility(driver, uploadCSVInput, 60);
//       
//        driver.findElement(uploadCSVInput).sendKeys(path);
//        WaitUtil.waitForPageToLoad(driver, 70);
//
//      		// Wait for backdrop (overlay/spinner) to disappear
//      		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);
//
//      		// Wait for the tile to be visible
//      		WaitUtil.waitForVisibility(driver, groupDropdown, 60);
//        
//        ElementUtil.click(groupDropdown, driver);
//        WaitUtil.waitForVisibility(driver, groupDropdown,30);
//        ElementUtil.selectDropdownByVisibleText(groupDropdown, "Sales Account Manager", driver);
//        
//        WaitUtil.waitForPageToLoad(driver, 70);
//
//		// Wait for backdrop (overlay/spinner) to disappear
//		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);
//
//		// Wait for the tile to be visible
//		//WaitUtil.waitForVisibility(driver, saveButton, 60);
//        WaitUtil.waitForVisibility(driver, saveCSVButton, 15);
//        ElementUtil.click(saveCSVButton, driver);
//        
//        
//
//        // Optional: Wait for upload success toast or refresh UI
//        // WaitUtil.waitForInvisibilityOfElement(LOADER_SPINNER, driver, 30); // if needed
//    }


    public void uploadCSVTeamMembers() throws Exception {

		List<String[]> data = Arrays.asList(
				new String[] {"auto.test@test.com", "Auto", "Test"},
			new String[] {"john.doe@test.com", "John", "Doe"});

		String path = CSVUtil.generateCSV(data);
		driver.findElement(uploadCSVInput).sendKeys(path);
		//ElementUtil.sendText(SH_LEGAL_BASIS, "Legitimate interest - prospect/lead", driver);
		 ElementUtil.click(groupDropdown, driver);
	}
    
    
    public void searchTeamMember() {
        ElementUtil.sendText(searchField, "oneteam", driver);
        ElementUtil.click(searchIcon, driver);
    }

    public void deleteSearchedMember() {
        ElementUtil.click(deleteUploadIcon, driver);
    }

    public void exportTeamMembers() {
        ElementUtil.click(exportExcelButton, driver);
    }

    public void filterByEmailAndDate(String email) {
        ElementUtil.click(refreshButton, driver);
        ElementUtil.click(filterButton, driver);
        ElementUtil.click(dateField, driver);
        ElementUtil.click(fromDate, driver);
        ElementUtil.click(toDateField, driver);
        ElementUtil.click(toDate, driver);
        ElementUtil.click(applyButton, driver);
    }

    public void PreviewTeamMember() {
        ElementUtil.click(previewIcon, driver);
    }

    public void editTeammember(String editedName) {
        ElementUtil.click(editIcon, driver);
        ElementUtil.sendText(firstNameField, editedName, driver);
        ElementUtil.click(updateButton, driver);
    }

    public void resendEmailNotification() {
        ElementUtil.click(resendEmailIcon, driver);
        ElementUtil.click(yesSendIt, driver);
    }

    public void deleteTeammember() {
        ElementUtil.click(deleteIcon, driver);
        ElementUtil.click(yesDelete, driver);
    }
}
