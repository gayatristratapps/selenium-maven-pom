package com.stratapps.xamplify.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stratapps.xamplify.utils.DamUtil;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.AWTException;
import java.time.Duration;



public class ManageAssetsPage {
	
	private static final Logger logger = LogManager.getLogger(ManageAssetsPage.class);
    WebDriver driver;
    private WebDriverWait wait;
  
 
     
        public ManageAssetsPage(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // optional if not used in POM directly
            DamUtil.init(driver); // ✅ This is essential to avoid NullPointerException
        }



    // All Locators with direct XPaths
    private final By contentLeftMenu = By.xpath("//span[normalize-space()='Content']");
    private final By manageAssetsMenuItem = By.xpath("//span[normalize-space()='Manage Assets']");
    private final By sortDropdown = By.xpath("//*[@id=\"alignline-m-width\"]");
    private final By searchField = By.xpath("//div[@id=\"alignline-m-serch\"]/input");
    private final By downloadAssetButton = By.xpath("(//i[@class='fa fa-download IconCustomization'])[1]");
    private final By assetPreviewButton = By.xpath("(//div/a[2]/i[@class='fa fa-eye IconCustomization'])[1]");
    private final By assetPreviewClose = By.xpath("//div[1]/a[contains(@class, 'close-circle ref-mar')]/span");
    private final By editIcon = By.xpath("(//i[@class='fa fa-edit IconCustomization'])[1]");
    private final By nameField = By.xpath("(//input[contains(@placeholder,'Enter Name')])[1]");
    private final By updateButton = By.xpath("//span[@class='btn btn-primary transition']");
    private final By successMessage = By.xpath("//span[@id='responseMessage']");
    private final By analyticsIcon = By.xpath("(//i[@class='fa fa-line-chart IconCustomization'])[1]");
    private final By analyticsCloseButton = By.xpath("(//i[@class='fa fa-times'])[2]");
    private final By refreshButton = By.xpath("//i[@class='fa fa-refresh']");
    private final By videoAssetEdit = By.xpath("(//i[@class='fa fa-edit IconCustomization'])[1]");
    private final By replaceButton = By.xpath("//span[contains(text(),'Replace')]");
    private final By browseButton = By.xpath("//span[contains(text(),'Browse')]");
    private final By replaceUpdate = By.xpath("(//span[contains(text(),'Update')])[2]");
    private final By previewVideoAsset = By.xpath("(//i[@class='fa fa-eye IconCustomization'])[1]");
    private final By manageAssetBreadcrumb = By.xpath("//a[normalize-space()='Manage Assets']");
    private final By videoAssetAnalytics = By.xpath("(//i[@class='fa fa-line-chart IconCustomization'])[1]");
    private final By filterIcon = By.xpath("//i[contains(@class,'fa fa-filter p')]");
    private final By fieldNameDropdown = By.xpath("//div[contains(@class,'col-md')]/div[1]/select");
    private final By fieldNameDropdown2 = By.xpath("(//div[contains(@class,'col-md')]/div[1]/select)[2]");
    private final By fieldNameDropdown3 = By.xpath("(//div[contains(@class,'col-md')]/div[1]/select)[3]");
    private final By fieldNameDropdown4 = By.xpath("(//div[contains(@class,'col-md')]/div[1]/select)[4]");
    private final By fieldNameDropdown5 = By.xpath("(//div[contains(@class,'col-md')]/div[1]/select)[5]");
    private final By conditionDropdown = By.xpath("(//div[contains(@class,'col-md')]/select)[2]");
    private final By conditionDropdown2 = By.xpath("(//div[contains(@class,'mobileTopandBottomSpace')]/select)[2]");
    private final By conditionDropdown3 = By.xpath("(//div[contains(@class,'mobileTopandBottomSpace')]/select)[3]"); // guessed
    private final By conditionDropdown4 = By.xpath("(//div[contains(@class,'mobileTopandBottomSpace')]/select)[4]");
    private final By conditionDropdown5 = By.xpath("(//div[contains(@class,'mobileTopandBottomSpace')]/select)[5]");
    private final By assetTypeDropdown = By.xpath("//div[3]/select[contains(@class,'form-control ng-untouched ng-pristine ng-valid')]");
    private final By valueField = By.xpath("//input[@placeholder='value*']");
    private final By submitFilterButton = By.xpath("//button[normalize-space()='Submit']");
    private final By addConditionButton = By.xpath("//i[@class='fa fa-plus-square']");
    private final By removeFilterButton = By.xpath("//a[@type='button']//i[@class='fa fa-times']");
    private final By listViewIcon = By.xpath("//button[contains(@class,'fa fa-th-list btn btn-xs l-g-view mr')]");
    private final By gridViewIcon = By.xpath("//i[contains(@class,'fa fa-th-large p')]");
    private final By folderGridView = By.xpath("//i[contains(@class,'fa fa-folder p')]");
    private final By folderListView = By.xpath("(//i[contains(@class,'fa fa-th p')])");
    private final By gridViewAssetHover = By.xpath("(//div[@class='grid-box min-h default-cursor mHeight'])[1]");
    private final By gridViewAssetPreview = By.xpath("(//div/a[2])[1]");
	private DamUtil damUtil;



    // Navigation Methods
    public void navigateToManageAssets() {
        logger.info("Navigating to Manage Assets");
        try {
            DamUtil.hover(contentLeftMenu);
            DamUtil.callClickEvent(contentLeftMenu, driver);
            wait.until(ExpectedConditions.elementToBeClickable(manageAssetsMenuItem)).click();
        } catch (Exception e) {
            logger.error("Failed to navigate to Manage Assets", e);
            throw new RuntimeException(e);
        }
    }

    
    // Search and Sort Methods
    public void searchAndSortAsset(String assetName) {
        logger.info("Searching and sorting asset: " + assetName);
        try {
            WebElement sortElement = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
            sortElement.click();
            DamUtil.selectDropdownByText(sortDropdown, "Created On(ASC)", driver);

            
            WebElement searchElement = wait.until(ExpectedConditions.elementToBeClickable(searchField));
            searchElement.sendKeys(assetName);
            searchElement.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            logger.error("Failed to search and sort asset", e);
            throw new RuntimeException(e);
        }
    }

    // Asset Action Methods
    public void performAssetActions(String assetName) {
        logger.info("Performing asset actions");
        try {
            downloadAsset();
            previewAsset();
            editAsset(assetName);
        } catch (Exception e) {
            logger.error("Failed to perform asset actions", e);
            throw new RuntimeException(e);
        }
    }

    private void downloadAsset() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadAssetButton)).click();
    }

    private void previewAsset() {
        wait.until(ExpectedConditions.elementToBeClickable(assetPreviewButton)).click();
    }

    private void editAsset(String assetName) {
        wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();
        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameInput.clear();
        nameInput.sendKeys(assetName + "_" + System.currentTimeMillis());
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
        verifyUpdateSuccess();
    }

    
    // Analytics Methods
    public void handleAnalytics() {
        logger.info("Handling asset analytics");
        try {
            openAnalytics();
            closeAnalytics();
            updateAssetAfterAnalytics();
        } catch (Exception e) {
            logger.error("Failed to handle analytics", e);
            throw new RuntimeException(e);
        }
    }

    private void openAnalytics() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(analyticsIcon)).click();
        Thread.sleep(2000);
    }

    private void closeAnalytics() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(analyticsCloseButton)).click();
        Thread.sleep(4000);
    }

    private void updateAssetAfterAnalytics() throws InterruptedException {
        editAsset("AssetNameUpdate");
    }

    // Video Asset Methods
    public void handleVideoAssetActions() throws InterruptedException, AWTException {
        logger.info("Handling video asset actions");
        try {
            refreshAssetView();
            editVideoAsset();
            replaceVideoFile();
            handleVideoPreviewAndAnalytics();
        } catch (Exception e) {
            logger.error("Failed to handle video asset actions", e);
            throw new RuntimeException(e);
        }
    }

    private void refreshAssetView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
        Thread.sleep(6000);
    }

    private void editVideoAsset() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(videoAssetEdit)).click();
        Thread.sleep(6000);
    }

    private void replaceVideoFile() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(replaceButton)).click();
        Thread.sleep(4000);

        WebElement browse = wait.until(ExpectedConditions.elementToBeClickable(browseButton));
        browse.click();
        Thread.sleep(3000);

        damUtil.uploadFile("D:\\git\\xAmplifyQA\\xAmplifyQA\\files\\replace_mp4_file.mp4");
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(replaceUpdate)).click();
        Thread.sleep(2000);
    }

    private void handleVideoPreviewAndAnalytics() throws InterruptedException {
        // Double refresh as per requirement
        refreshAssetView();
        refreshAssetView();

        // Preview video
        wait.until(ExpectedConditions.elementToBeClickable(previewVideoAsset)).click();
        Thread.sleep(7000);

        // Navigate using breadcrumb
        wait.until(ExpectedConditions.elementToBeClickable(manageAssetBreadcrumb)).click();
        Thread.sleep(5000);

        // Handle analytics clicks
        for (int i = 0; i < 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(videoAssetAnalytics)).click();
            Thread.sleep(2000);
        }

        wait.until(ExpectedConditions.elementToBeClickable(manageAssetBreadcrumb)).click();
        Thread.sleep(5000);
    }

    // View Methods
    public void handleAssetViews() throws InterruptedException {
        logger.info("Handling different asset views");
        try {
            handleListView();
            handleGridView();
            handleFolderGridView();
            handleFolderListView();
        } catch (Exception e) {
            logger.error("Failed to handle asset views", e);
            throw new RuntimeException(e);
        }
    }

    private void handleListView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(listViewIcon)).click();
        Thread.sleep(1000);
    }

    private void handleGridView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(gridViewIcon)).click();
        Thread.sleep(6000);
        
        damUtil.scrollToBottom(driver);
        Thread.sleep(2000);
        
        handleGridViewPreview();
    }

    private void handleGridViewPreview() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(gridViewAssetHover)).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(gridViewAssetPreview)).click();
        Thread.sleep(2000);
        
        damUtil.scrollToTop(driver);
        Thread.sleep(3000);
        
        wait.until(ExpectedConditions.elementToBeClickable(assetPreviewClose)).click();
        Thread.sleep(2000);
    }

    private void handleFolderGridView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(folderGridView)).click();
        Thread.sleep(3000);
    }

    private void handleFolderListView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(folderListView)).click();
        Thread.sleep(3000);
    }
    
    
    
    public void applyNameFilter(String name) throws InterruptedException {
        logger.info("Applying name filter: " + name);
        try {
            // Select Asset Name from field dropdown
            DamUtil.click(fieldNameDropdown, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(fieldNameDropdown, "Asset Name", driver);
            Thread.sleep(2000);

            // Select Contains condition
            DamUtil.click(conditionDropdown, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(conditionDropdown, "Contains", driver);
            Thread.sleep(2000);

            // Enter value
            DamUtil.sendText(valueField, name, driver);
            Thread.sleep(2000);

            // Submit filter
            DamUtil.click(submitFilterButton, driver);
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error("Failed to apply name filter", e);
            throw new RuntimeException("Failed to apply name filter", e);
        }
    }

    public void applyFolderFilter(String folderName) throws InterruptedException {
        logger.info("Applying folder filter: " + folderName);
        try {
            // Click add condition
            DamUtil.click(addConditionButton, driver);
            Thread.sleep(1000);

            // Select Folder from field dropdown
            DamUtil.click(fieldNameDropdown2, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(fieldNameDropdown2, "Folder", driver);
            Thread.sleep(2000);

            // Select Contains condition
            DamUtil.click(conditionDropdown2, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(conditionDropdown2, "Contains", driver);
            Thread.sleep(2000);

            // Enter value
            DamUtil.sendText(valueField, folderName, driver);
            Thread.sleep(2000);

            // Submit filter
            DamUtil.click(submitFilterButton, driver);
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error("Failed to apply folder filter", e);
            throw new RuntimeException("Failed to apply folder filter", e);
        }
    }

    public void applyTypeFilter(String type) throws InterruptedException {
        logger.info("Applying type filter: " + type);
        try {
            // Click add condition
            DamUtil.click(addConditionButton, driver);
            Thread.sleep(1000);

            // Select Type from field dropdown
            DamUtil.click(fieldNameDropdown3, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(fieldNameDropdown3, "Type", driver);
            Thread.sleep(2000);

            // Select type from asset type dropdown
            DamUtil.click(assetTypeDropdown, driver);
            Thread.sleep(1000);
            DamUtil.selectByValue(assetTypeDropdown, type, driver);
            Thread.sleep(2000);

            // Submit filter
            DamUtil.click(submitFilterButton, driver);
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error("Failed to apply type filter", e);
            throw new RuntimeException("Failed to apply type filter", e);
        }
    }

    public void applyTagsFilter(String tags) throws InterruptedException {
        logger.info("Applying tags filter: " + tags);
        try {
            // Click add condition
            DamUtil.click(addConditionButton, driver);
            Thread.sleep(1000);

            // Select Tags from field dropdown
            DamUtil.click(fieldNameDropdown4, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(fieldNameDropdown4, "Tags", driver);
            Thread.sleep(2000);

            // Select Contains condition
            DamUtil.click(conditionDropdown4, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(conditionDropdown4, "Contains", driver);
            Thread.sleep(2000);

            // Enter value
            DamUtil.sendText(valueField, tags, driver);
            Thread.sleep(2000);

            // Submit filter
            DamUtil.click(submitFilterButton, driver);
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error("Failed to apply tags filter", e);
            throw new RuntimeException("Failed to apply tags filter", e);
        }
    }

    public void applyCreatedByFilter(String creator) throws InterruptedException {
        logger.info("Applying created by filter: " + creator);
        try {
            // Click add condition
            DamUtil.click(addConditionButton, driver);
            Thread.sleep(1000);

            // Select Created By from field dropdown
            DamUtil.click(fieldNameDropdown5, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(fieldNameDropdown5, "Created By", driver);
            Thread.sleep(2000);

            // Select equals condition
            DamUtil.click(conditionDropdown5, driver);
            Thread.sleep(2000);
            DamUtil.selectByVisibleText(conditionDropdown5, "=", driver);
            Thread.sleep(2000);

            // Enter value
            DamUtil.sendText(valueField, creator, driver);
            Thread.sleep(2000);

            // Submit filter
            DamUtil.click(submitFilterButton, driver);
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error("Failed to apply creator filter", e);
            throw new RuntimeException("Failed to apply creator filter", e);
        }
    }

    // Method to apply all filters in sequence
    public void applyAllFilters() throws InterruptedException {
        logger.info("Applying all filters");
        try {
            openFilterPanel();
            applyNameFilter("png");
            applyFolderFilter("default");
            applyTypeFilter("png");
            applyTagsFilter("re");
            applyCreatedByFilter("vendor automate");
            reviewAndClearFilters();
        } catch (Exception e) {
            logger.error("Failed to apply all filters", e);
            throw new RuntimeException("Failed to apply all filters", e);
        }
    }

    // Filter Methods
    public void filterAssets() throws InterruptedException {
        logger.info("Applying asset filters");
        try {
            openFilterPanel();
            applyNameFilter("png");
            applyFolderFilter("default");
            applyTypeFilter("png");
            applyTagsFilter("re");
            applyCreatedByFilter("vendor automate");
            reviewAndClearFilters();
        } catch (Exception e) {
            logger.error("Failed to apply filters", e);
            throw new RuntimeException(e);
        }
    }

    private void openFilterPanel() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(filterIcon)).click();
        Thread.sleep(3000);
    }

    private void applyFilter(String fieldName, String condition, String value, By dropdownLocator)
            throws InterruptedException {
        WebElement fieldDropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        fieldDropdown.click();
        Thread.sleep(2000);

        DamUtil.selectByVisibleText(dropdownLocator, fieldName, driver);
        Thread.sleep(2000);

        WebElement conditionElement = wait.until(ExpectedConditions.elementToBeClickable(conditionDropdown));
        conditionElement.click();
        Thread.sleep(2000);

        DamUtil.selectByVisibleText(conditionDropdown, condition, driver);
        Thread.sleep(2000);

        WebElement valueElement = wait.until(ExpectedConditions.elementToBeClickable(valueField));
        valueElement.sendKeys(value);
        Thread.sleep(2000);

        submitFilter();
    }

    

    private void submitFilter() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(submitFilterButton)).click();
        Thread.sleep(4000);
    }

    private void reviewAndClearFilters() throws InterruptedException {
        damUtil.scrollToBottom(driver);
        Thread.sleep(4000);
        damUtil.scrollToTop(driver);
        Thread.sleep(4000);
        
        wait.until(ExpectedConditions.elementToBeClickable(removeFilterButton)).click();
        Thread.sleep(4000);
    }

    // Verification Methods
    private void verifyUpdateSuccess() {
        try {
            String actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
            String expectedResult = "Updated Successfully";
            if (!expectedResult.equals(actualResult)) {
                logger.error("Asset update verification failed");
                throw new AssertionError("Asset update verification failed");
            }
            logger.info("Asset updated successfully");
        } catch (Exception e) {
            logger.error("Failed to verify update success", e);
            throw new RuntimeException(e);
        }
    }
}

    
    
