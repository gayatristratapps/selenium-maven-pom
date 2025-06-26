package com.stratapps.xamplify.pages;


import java.awt.AWTException;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.stratapps.xamplify.utils.ActionUtil;
import com.stratapps.xamplify.utils.ElementUtil;
import com.stratapps.xamplify.utils.WaitUtil;
import com.stratapps.xamplify.utils.DropdownUtil;

public class ManageTracksPage {
    private WebDriver driver;

    private By contentMenu = By.xpath("//span[normalize-space()='Content']");
    private By manageTracks = By.xpath("//span[contains(text(),'Manage Tracks')]");
    private By editTrack = By.xpath("(//a[contains(@class,'custom-icon')]//i[contains(@class,'fa fa-pencil-square-o IconCustomization')])[1]");
    private By clearEndDate = By.xpath("//i[@class='fa fa-refresh npcfs']");
    private By endDateInput = By.xpath("//input[@id='flat-picker']");
    private By selectEndDate = By.xpath("//div[contains(@class,'open')]//span[@class='flatpickr-day today']/following-sibling::span[5]");
    private By endHourInput = By.xpath("//div[contains(@class,'open')]//input[@class='numInput flatpickr-hour']");
    private By endMinuteInput = By.xpath("//div[contains(@class,'open')]//input[@class='numInput flatpickr-minute']");
    private By assetsSection = By.xpath("//*[@id='step-3']/div//h5[normalize-space()='Assets']");
    private By addOnAsset = By.xpath("(//ul[@class='content__list mContentWid']/li/div/div[1]/span[@class='select-box-asset'])[4]");
    private By shareSection = By.xpath("//h5[normalize-space()='Share']");
    private By updateTrack = By.xpath("//span[normalize-space()='Update']");
    private By refreshButton = By.xpath("//i[@class='fa fa-refresh']");

    private By unpublishIcon = By.xpath("(//i[@class='fa fa-reply IconCustomization'])[1]");
    private By otherRadio = By.xpath("//input[@value='Other']");
    private By unpublishButton = By.xpath("//span[normalize-space()='Unpublish']");
    private By publishIcon = By.xpath("(//i[@class='fa fa-share IconCustomization'])[1]");
    private By confirmPublish = By.xpath("//button[normalize-space()='Yes']");

    private By previewTrack = By.xpath("(//i[@class='fa fa-eye IconCustomization'])[1]");
    private By addToPlaybook = By.xpath("//button[normalize-space()='Add To PlayBooks']");
    private By previewClose = By.xpath("//span[@class='glyphicon glyphicon-remove asset_cross_align']");
    private By trackNavigation = By.xpath("//a[normalize-space()='Manage Tracks']");

    private By trackAnalytics = By.xpath("(//i[@class='fa fa-line-chart IconCustomization'])[1]");
    private By companyAnalytics = By.xpath("(//i[@class='fa fa-line-chart IconCustomization'])[1]");
    private By analyticsSearch = By.xpath("(//input[@placeholder='Search...'])[2]");
    private By partnerAnalytics = By.xpath("(//i[@class='fa fa-line-chart IconCustomization'])[1]");
    private By partnerAnalyticsPopupClose = By.xpath("//div[@id='analytics-list']//i[@class='fa fa-times']");
    private By partnerAnalyticsClose = By.xpath("//a[@class='close-circle xamp-margin-left-icon']//i[@class='fa fa-times']");

    private By sortByDropdown = By.xpath("//div[contains(@class, 'float-left')]/select");
    private By searchTrack = By.xpath("//input[@placeholder='search...']");
    private By deleteTrack = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[2]");
    private By confirmDelete = By.xpath("//button[normalize-space()='Yes, delete it!']");
    private By clearSearch = By.xpath("//button[@class='glyphicon glyphicon-remove search-box-item-clear']");

    private By trackGroupDropdown = By.xpath("//div[@class='group-dropdown']");
    private By gridViewBtn = By.xpath("(//span[@class='btn btn-xs l-g-view margin-space'])[1]");
    private By gridAsset = By.xpath("(//img[@class='img-grid-track'])[1]");
    private By editGridTrack = By.xpath("(//i[@class='fa fa-pencil-square-o'])[1]");
    private By thumbnailIcon = By.xpath("//i[@class='fa fa-camera icon']");
    private By uploadButton = By.xpath("//div[contains(@class, 'fileinput-button-css')]");
    private By cropImageSave = By.xpath("//div[@id='cropImage']/div/div/div[2]/div[3]/div[2]/button/span");
    private By folderView = By.xpath("//div[@class='bg-folder']");
    private By viewFolderTracks = By.xpath("//a[@class='Iconhover custom-icon']");
    private By goBackArrow = By.xpath("//i[contains(@class, 'fa fa-arrow-up ')]");
    private By folderGridDropdown = By.xpath("//span[@class='btn btn-xs l-g-view margin-space folder-list-span-custom-width']");
    private By folderSortDropdown = By.xpath("(//select[@id='select-dropdown'])[1]");
    private By folderSearchTypeDropdown = By.xpath("(//select[@id='select-dropdown'])[2]");
    private By folderSearchInput = By.xpath("//input[@placeholder='Search']");
    private By folderArrowIcon = By.xpath("//i[@id='fa-folder-custom-size']");
    private By listViewBtn = By.xpath("//span[@class='btn btn-xs l-g-view']");
    private By homeLink = By.xpath("//a[normalize-space()='Home']");
	private By backdrop = By.cssSelector("div.backdrop");

    public ManageTracksPage(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateToContentAndManageTracks() {
        ElementUtil.click(contentMenu, driver);
        //ElementUtil.click(manageTracks, driver);
        WaitUtil.waitAndClick(driver, manageTracks, 60);
    }

    public void editTrackDetails() {
    	WaitUtil.waitForPageToLoad(driver, 70);
		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);
		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, editTrack, 60);
        ElementUtil.click(editTrack, driver);
        WaitUtil.waitAndClick(driver, clearEndDate, 60);
    	WaitUtil.waitForPageToLoad(driver, 70);

        WaitUtil.waitAndClick(driver, endDateInput, 60);
        ElementUtil.click(selectEndDate, driver);

        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        if (hours < 12) {
            ElementUtil.sendText(endHourInput, "1", driver);
            ElementUtil.sendText(endMinuteInput, "11", driver);
        } else {
            ElementUtil.sendText(endHourInput, "11", driver);
            ElementUtil.sendText(endMinuteInput, "59", driver);
        }
        
        WaitUtil.waitAndClick(driver, assetsSection, 60);
        //ElementUtil.click(assetsSection, driver);
        WaitUtil.waitForPageToLoad(driver, 60);
        WaitUtil.waitAndClick(driver, addOnAsset, 60);
        //ElementUtil.click(addOnAsset, driver);
        WaitUtil.waitAndClick(driver, shareSection, 60);

        //ElementUtil.click(shareSection, driver);
        ElementUtil.click(updateTrack, driver);
        WaitUtil.waitForPageToLoad(driver, 60);
        WaitUtil.waitAndClick(driver, refreshButton, 60);
    }

    public void unpublishTrack() {
    	
        WaitUtil.waitAndClick(driver, unpublishIcon, 60);
        WaitUtil.waitForPageToLoad(driver, 60);
        WaitUtil.waitAndClick(driver, otherRadio, 60);
        ElementUtil.click(unpublishButton, driver);
    }
    
  public void publishTrack() {
      WaitUtil.waitAndClick(driver, publishIcon, 60);
      WaitUtil.waitAndClick(driver, confirmPublish, 60);

  ElementUtil.click(confirmPublish, driver);
}


    public void previewTrackAndReturn() {
    	
       //WaitUtil.waitForPageToLoad(driver, 60);
     // Wait for backdrop (overlay/spinner) to disappear
     		WaitUtil.waitAndClick(driver, previewTrack, 50);
            WaitUtil.waitForPageToLoad(driver, 60);

        //ElementUtil.click(previewTrack, driver);
        WaitUtil.waitAndClick(driver, addToPlaybook, 60);

        //ElementUtil.click(addToPlaybook, driver);
        ElementUtil.click(previewTrack, driver);
        ElementUtil.click(previewClose, driver);
        ElementUtil.click(trackNavigation, driver);
    }

    public void viewTrackAnalytics() {
        ElementUtil.click(trackAnalytics, driver);
        ElementUtil.click(companyAnalytics, driver);
        ElementUtil.sendText(analyticsSearch, "Automate", driver);
        driver.findElement(analyticsSearch).sendKeys(Keys.ENTER);
        ElementUtil.click(partnerAnalytics, driver);
        ElementUtil.click(partnerAnalyticsPopupClose, driver);
        ElementUtil.click(partnerAnalyticsClose, driver);
        ElementUtil.click(trackNavigation, driver);
    }

    public void sortAndDeleteTrack() {
        DropdownUtil.selectByValue(driver, sortByDropdown, "1: Object");
        ElementUtil.sendText(searchTrack, "track",driver);
        driver.findElement(searchTrack).sendKeys(Keys.ENTER);
        ElementUtil.click(deleteTrack, driver);
        ElementUtil.click(confirmDelete, driver);
        ElementUtil.click(clearSearch, driver);
    }

    public void trackViews(String localFilePath) {
        ElementUtil.click(trackGroupDropdown, driver);
        ElementUtil.click(gridViewBtn, driver);
        ElementUtil.click(gridAsset, driver);
        ElementUtil.click(editGridTrack, driver);

        ElementUtil.click(thumbnailIcon, driver);
        WebElement fileInput = driver.findElement(uploadButton);
        fileInput.sendKeys(localFilePath);

        ElementUtil.click(cropImageSave, driver);
        ElementUtil.click(shareSection, driver);
        ElementUtil.click(updateTrack, driver);
        ElementUtil.click(refreshButton, driver);

        ElementUtil.click(trackGroupDropdown, driver);
        ElementUtil.click(gridViewBtn, driver);
        ElementUtil.click(folderView, driver);
        ElementUtil.click(viewFolderTracks, driver);
        ElementUtil.click(goBackArrow, driver);

        ElementUtil.click(trackGroupDropdown, driver);
        ElementUtil.click(folderGridDropdown, driver);

        DropdownUtil.selectByVisibleText(driver, folderSortDropdown, "Name(Z-A)");
        DropdownUtil.selectByVisibleText(driver, folderSearchTypeDropdown, "Search In Folder");

        ElementUtil.sendText(folderSearchInput, "default", driver);
        driver.findElement(folderSearchInput).sendKeys(Keys.ENTER);

        ElementUtil.click(goBackArrow, driver);
        ElementUtil.click(folderArrowIcon, driver);
        ElementUtil.click(trackGroupDropdown, driver);
        ElementUtil.click(listViewBtn, driver);
        ElementUtil.click(homeLink, driver);
    }
}
