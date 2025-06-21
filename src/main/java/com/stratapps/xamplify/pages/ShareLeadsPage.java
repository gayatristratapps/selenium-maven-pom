package com.stratapps.xamplify.pages;

import com.stratapps.xamplify.utils.ActionUtil;
import com.stratapps.xamplify.utils.CSVUtil;
import com.stratapps.xamplify.utils.ElementUtil;
import com.stratapps.xamplify.utils.ScreenshotUtil;
import com.stratapps.xamplify.utils.WaitUtil;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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
	private final By SH_FLAG_CODE = By
			.xpath("//*[@id=\"blur-content-div\"]//app-country-phone-input//div[@class=\"search-box\"]/input");
	private final By SH_FLAG_CODE_SELECT = By
			.xpath("//*[@id=\"blur-content-div\"]//app-country-phone-input//li/span[2]");
	private final By SH_SCROLL = By.xpath("//*[@class='modal-body border-bottom-1px popup_body']");
	private final By SHARE_LEADS_ADD = By.xpath("//div[@id='addContactModal']//button[2]");
	private final By SHARE_LEADS_SAVE = By.xpath("//button[@id=\"sample_editable_1_new\"]/span");
	private final By SHARE_LEADS_ACCEPT = By.xpath("//div[@id=\"tcModal\"]//button[2]");

	private final By SH_CSV_CLICK = By.xpath("//input[@type='file']");
	private final By SEARCH_BAR = By.xpath("//div[@id='manageContacts']//input");
	private final By EDIT_BUTTON = By.xpath("(//*[@id='partner_contact_list']//a[2]/i)[1]");
	private final By EDIT_ICON = By.xpath("(//i[@class='fa fa-pencil-square-o IconCustomization'])[1]");
	// private final By PUBLISH_ICON =
	// By.xpath("(//*[@id='partner_contact_list']//td[7]/div/a[1]/i)[1]");

	private final By DELETE_ICON = By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[1]");
	private final By DELETE_CONFIRM = By.xpath("//button[@class='swal2-confirm styled']");
	private final By DROPDOWN_LOCATOR = By.xpath(
			"//div[@class=\"col-xs-12 col-md-12 col-sm-12\"]//div[@class=\"col-xs-12 col-sm-6 col-lg-6 p0 pull-right\"]//select");

	private final By copyIcon = By.xpath(
			"//table[@id='partner_contact_list']//tr[1]//td//i[contains(@class, 'fa-files-o') and contains(@class, 'IconCustomization')]");

	private final By saveChangesButton = By.xpath(
			"//div[contains(@class,'modal-footer')]//button[.//span[contains(normalize-space(), 'Save changes')]]");

	private By confirmDeleteYes = By.xpath("//button[normalize-space()='Yes']");
	private final By deleteIconLocator = By.xpath("(//i[contains(@class,'fa-trash')])[1]");

	private By allTile = By.xpath("//div[contains(text(),'All')]");

	private By filterIcon = By.xpath("//i[contains(@class,'fa-filter')]");

	private By conditionDropdown = By.xpath("(//select[contains(@class,'form-control')])[2]");
	private By valueInput = By.xpath("//input[@placeholder='value*']");
	private By submitButton = By.xpath("//button[contains(text(),'Submit')]");

	private By searchInput = By.xpath("//input[@placeholder='Search']");
	
	private By emailReportButton = By.xpath("//i[contains(@class, 'fa-file')]");
	private By gearIcon = By.xpath("//span[contains(@class, 'dropdown-toggle')]//i[contains(@class, 'fa-cog')]");
	private By newlist = By.xpath("//a[contains(text(),\" Create New List \")]");
	private By deleteButton = By.id("delete_button");

	private By validTile = By.xpath("//div[normalize-space()='Valid']");

	private By backdrop = By.cssSelector("div.backdrop");

	//private By exportExcelButton = By.id("export-excel");

	private By AllTilesortByDropdown = By
			.xpath("//div[contains(@class, 'sortingContacts')]//select[contains(@class, 'SeclectBoxPaddingsAbj')]");

	private By sortByDropdown = By
			.xpath("//div[contains(@class, 'search-css')]//select[contains(@class, 'SeclectBoxPaddingsAbj')]");

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

		ElementUtil.sendText(By.id("title"), "QA Engineer", driver);

		ElementUtil.sendText(By.id("address"), "123 Street", driver);

		ElementUtil.sendText(By.id("city"), "Hyderabad", driver);

		ElementUtil.sendText(By.id("state"), "Telangana", driver);

		ElementUtil.sendText(By.id("zip"), "500001", driver);

		WebElement scrollableDiv = driver.findElement(SH_SCROLL);
		ActionUtil.scrollInsideElement(driver, scrollableDiv, 500);

		ElementUtil.click(SH_FLAG, driver);

		ElementUtil.sendText(SH_FLAG_CODE, "+91", driver);

		ElementUtil.click(SH_FLAG_CODE_SELECT, driver);

		ElementUtil.sendText(SH_MOBILE_NO, "9876543210", driver);

		ElementUtil.click(SHARE_LEADS_ADD, driver);

	}

	public void uploadCSVLeads() throws Exception {

		List<String[]> data = Arrays.asList(
				new String[] { "Auto", "Test", "Company", "Engineer", "auto.test@test.com", "Street", "City", "ST",
						"12345", "Country", "+91 1234567890" },
				new String[] { "John", "Doe", "TestCorp", "QA", "john.doe@test.com", "456 Road", "Town", "ST", "56789",
						"Country", "+91 1231231234" });

		String path = CSVUtil.generateCSV(data);
		driver.findElement(SH_CSV_CLICK).sendKeys(path);
		ElementUtil.sendText(SH_LEGAL_BASIS, "Legitimate interest - prospect/lead", driver);
		ElementUtil.sendKey(SH_LEGAL_BASIS, Keys.ENTER, driver);
	}

	public void clickSaveAndAccept() throws InterruptedException {
		ElementUtil.click(SHARE_LEADS_SAVE, driver);

		Thread.sleep(1000);
		ElementUtil.click(SHARE_LEADS_ACCEPT, driver);
		Thread.sleep(500);
	}

	public void navigateToManageShareLeads() throws InterruptedException {
		Thread.sleep(1000);
		ActionUtil.hoverAndClick(driver, HOVER_SHARE_LEADS, MANAGE_SHARE_LEADS);

	}

	public void editShareLeadDetails() throws InterruptedException {
		// Thread.sleep(5000);

		WaitUtil.waitForPageToLoad(driver, 70);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, EDIT_BUTTON, 60);

		// Now click safely
		ElementUtil.click(EDIT_BUTTON, driver);

		// ElementUtil.click(EDIT_BUTTON, driver);
		Thread.sleep(2000);
		ElementUtil.click(EDIT_ICON, driver);

		ElementUtil.getById(driver, "lastName").sendKeys("L1");
		WebElement mobileField = driver.findElement(SH_MOBILE_NO);
		mobileField.clear();
		mobileField.sendKeys("+91 9876543210");
		ElementUtil.click(SHARE_LEADS_ADD, driver);
	}

	public void saveEditedLeadAndConfirm() throws InterruptedException {
		Thread.sleep(2000);
		ElementUtil.getById(driver, "save&delete_button").click();
		ElementUtil.getById(driver, "save_button").click();
		Thread.sleep(2000);
		ElementUtil.click(SHARE_LEADS_ACCEPT, driver);
		Thread.sleep(1000);
	}

	public final static By FIRST_PUBLISH_ICON = By
			.xpath("(//i[contains(@class, 'fa-share') and contains(@class, 'IconCustomization')])[1]");
	private final By PUBLISH_viewicon = By
			.xpath("(//i[contains(@class, 'fa-eye') and contains(@class, 'IconCustomization')])[1]");

	public void publishAndDownloadShareLeadFlow() throws InterruptedException {

		WaitUtil.waitForPageToLoad(driver, 80);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, FIRST_PUBLISH_ICON, 60);

		// Now click safely
		ElementUtil.click(FIRST_PUBLISH_ICON, driver);

		// Wait and input text
		WebDriverWait inputWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By searchInput = By.xpath("(//input[@id='sort-text'])[1]");
		inputWait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));

		ElementUtil.sendText(searchInput, "PartnerAuto", driver);
		Thread.sleep(1000);
		ElementUtil.sendKey(searchInput, Keys.ENTER, driver);
		Thread.sleep(1000);

		ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//table//i[@class='fa fa-angle-right']"), driver);
		Thread.sleep(1000);
		ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//table//th[1]//input"), driver);
		Thread.sleep(1000);
		ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//button//span"), driver);
		Thread.sleep(1000);

		ScreenshotUtil.captureScreenshot(driver, "Published_Shareleads");
		Thread.sleep(1000);
		ElementUtil.click(By.xpath("//div[@id='partnerCompaniesPopup']//button[contains(text(),\"Close\")]"), driver);

		// Thread.sleep(58000);

		WaitUtil.waitForPageToLoad(driver, 80);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, PUBLISH_viewicon, 60);

		// Now click safely
		ElementUtil.click(PUBLISH_viewicon, driver);

		// ElementUtil.click(PUBLISH_viewicon, driver);

		Thread.sleep(2000);
		ElementUtil.click(By.xpath(
				"//div[contains(@class,'d-flex') and contains(@class,'justify-content-between')] //a[contains(@class,'close-circle')]"),
				driver);
		Thread.sleep(3000);

		ElementUtil.click(By.xpath("(//i[@class='fa fa-download IconCustomization'])[1]"), driver);
		Thread.sleep(2000);
	}

	public void searchShareLead(String query) throws InterruptedException {

		WaitUtil.waitForPageToLoad(driver, 60);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, SEARCH_BAR, 60);

		// Now click safely
		// ElementUtil.click(validTile, driver);

		ElementUtil.sendText(SEARCH_BAR, query, driver);
		ElementUtil.sendKey(SEARCH_BAR, Keys.ENTER, driver);
		Thread.sleep(3000);
	}

	public void publishAndDownloadShareLead() throws InterruptedException {
		ElementUtil.click(FIRST_PUBLISH_ICON, driver);
		Thread.sleep(2000);
	}

	// Actions
	public void clickCopyIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(copyIcon)).click();
	}

	public void clickSaveAsButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
	}

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
		} catch (InterruptedException ignored) {
		}
	}

	public void clickAllTile() throws InterruptedException {

		WaitUtil.waitForPageToLoad(driver, 80);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, allTile, 60);

		ElementUtil.click(allTile, driver);
		Thread.sleep(3000);
	}

	public void clickFilterIcon() throws InterruptedException {
		Thread.sleep(4000);
		ElementUtil.click(filterIcon, driver);
		Thread.sleep(2000);
	}

	private By fieldNameDropdown = By.xpath("(//select[contains(@class,'form-control')])[1]");

	public void selectFieldName(String fieldName) {
		ElementUtil.selectDropdownByVisibleText(fieldNameDropdown, fieldName, driver);
	}

	public void selectCondition(String fieldName) {
		ElementUtil.selectDropdownByVisibleText(conditionDropdown, fieldName, driver);
	}

	public void sendValue(String fieldName) {
		ElementUtil.sendText(valueInput, fieldName, driver);

	}

	public void applyFilter() throws InterruptedException {

		// Submit
		ElementUtil.click(submitButton, driver);
		Thread.sleep(3000);
	}

	// Actions

	public void filterSearch(String searchText) {
		ElementUtil.sendText(searchInput, searchText, driver);
		ElementUtil.sendKey(searchInput, Keys.ENTER, driver);
	}

	public void sortByIndex(int index) {
		try {
			WebElement dropdown = driver.findElement(By.id("sortDropdown")); // Use actual dropdown ID
			Select select = new Select(dropdown);
			select.selectByIndex(index);
			// logger.info("Selected sort option by index: " + index);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void sortTilesAndEmailReport() throws InterruptedException {
		List<String> sortOptions = Arrays.asList("Email (A-Z)", "Email (Z-A)", "First name (ASC)", "First name (DESC)",
				"Last name (ASC)");
		for (String option : sortOptions) {
			ElementUtil.selectDropdownByVisibleText(sortByDropdown, option, driver);
			try {
				Thread.sleep(2000); // wait for UI to update
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thread.sleep(1000); // wait for UI to update

		// ElementUtil.click(emailReportButton, driver);
	}

	public void EmailReport() throws InterruptedException {
		Thread.sleep(1000);
		ElementUtil.click(emailReportButton, driver);

	}

	private By campaignNameInput = By.id("campaignName");
	private By legalBasisDropdown = By.xpath("//*[@id=\"multiselectelement\"]//span[3]/input");
	private By saveButton = By.xpath("(//div[@id='saveAsModal']//button)[3]"); // update with actual value or property

	private By manageshFilterSelect = By.xpath("//input[@id='checkAllExistingContacts']");

	public void gearIconFromTiles() {
		ElementUtil.click(manageshFilterSelect, driver);

		ElementUtil.click(gearIcon, driver);
		ElementUtil.click(newlist, driver);

	}

	public void enterListName(String baseName) throws InterruptedException {
		Thread.sleep(2000);
		baseName = "test";
		String nameWithTimestamp = baseName + System.currentTimeMillis();
		ElementUtil.sendTextdriver(driver, campaignNameInput, nameWithTimestamp);
	}

	public void selectLegalBasis(String option) {
		ElementUtil.sendTextdriver(driver, legalBasisDropdown, option);
		ElementUtil.sendKeydriver(driver, legalBasisDropdown, Keys.ENTER);
	}

	public void clickSave() throws Exception {
		Thread.sleep(1000);
		ElementUtil.click(saveButton, driver);
	}

	public void clickValidTile() {

		WaitUtil.waitForPageToLoad(driver, 80);

		// Wait for backdrop (overlay/spinner) to disappear
		WaitUtil.waitForInvisibilityOfElement(backdrop, driver, 60);

		// Wait for the tile to be visible
		WaitUtil.waitForVisibility(driver, validTile, 60);

		// Now click safely
		ElementUtil.click(validTile, driver);

	}

	public void sortBy(String visibleText) {

		// By dropdownLocator = By.xpath("//select[contains(@class,'form-control') and
		// contains(@class,'SelectBoxPaddingsAbJ')]");

		List<String> sortOptions = new ArrayList<>();

		// Step 1: Collect all dropdown option texts initially
		Select initialSelect = new Select(driver.findElement(sortByDropdown));
		for (WebElement option : initialSelect.getOptions()) {
			String optionText = option.getText().trim();
			if (!optionText.equalsIgnoreCase("Sort by") && !optionText.isEmpty()) {
				sortOptions.add(optionText);
			}
		}

		// Step 2: Iterate through the options by visible text
		for (String optionText : sortOptions) {
			WaitUtil.waitForVisibility(driver, sortByDropdown, 40);
			Select select = new Select(driver.findElement(sortByDropdown));
			select.selectByVisibleText(optionText);

			System.out.println("Selected Sort Option: " + optionText);

			// Optional: Wait for list to update (use AJAX or full load wait)
			WaitUtil.waitForPageToLoad(driver, 40);
			// Optionally: Wait for specific element/table to become stale and reload
		}

		/*
		 * WaitUtil.waitForVisibility(driver, sortByDropdown, 50); WebElement dropdown =
		 * driver.findElement(sortByDropdown); Select select = new Select(dropdown);
		 * //select.selectByVisibleText(visibleText);
		 * 
		 * List<WebElement> options = select.getOptions(); for (WebElement option :
		 * options) { String optionText = option.getText().trim();
		 * 
		 * 
		 * System.out.println("Selecting sort option: " + optionText);
		 * select.selectByVisibleText(optionText);
		 * 
		 * }
		 */

	}

	public void searchList(String searchTerm) {
		WaitUtil.waitForVisibility(driver, searchInput, 50);
		WebElement searchBox = driver.findElement(searchInput);
		searchBox.clear();
		searchBox.sendKeys(searchTerm);
		searchBox.sendKeys(Keys.ENTER); // Or trigger a button if needed
	}

	public boolean isExcludeTileEnabled() {
		try {
			By excludeTileLocator = By.xpath("(//button[contains(@class, 'yellow-gold')])[1]");
			WebElement excludeTile = WaitUtil.waitForElementPresent(driver, excludeTileLocator, 40);
			return excludeTile.isEnabled();
		} catch (TimeoutException e) {
			System.out.println("Exclude tile not present: " + e.getMessage());
			return false;
		}
	}

	public void clickExcludeTile() {
		try {
			By excludeTileLocator = By.xpath("(//button[contains(@class, 'yellow-gold')])[1]");
			WebElement excludeTile = WaitUtil.waitForElementClickable(driver, excludeTileLocator, 40);
			excludeTile.click();
		} catch (TimeoutException e) {
			System.out.println("Exclude tile not clickable: " + e.getMessage());
		}
	}

	private final By undeliverableTile = By.xpath("(//button[contains(@class, 'yellow-gold')])[2]");

	public boolean isUndeliverableTileEnabled() {
		try {
			WebElement tile = WaitUtil.waitForElementPresent(driver, undeliverableTile, 30);
			return tile.isEnabled();
		} catch (TimeoutException e) {
			System.out.println("Undeliverable tile not present: " + e.getMessage());
			return false;
		}
	}

	public void clickUndeliverableTile() {
		try {
			By UndeliverableTileLocator = By.xpath("(//button[contains(@class, 'yellow-gold')])[1]");
			WebElement excludeTile = WaitUtil.waitForElementClickable(driver, UndeliverableTileLocator, 40);
			excludeTile.click();
		} catch (TimeoutException e) {
			System.out.println("UndeliverableTile tile not clickable: " + e.getMessage());
		}
	}

	private final By unsubscribeTile = By.xpath("//button[@class='btn-block dashboard-stat red']");

	public boolean isunsubscribeTileEnabled() {
		try {
			WebElement tile = WaitUtil.waitForElementPresent(driver, unsubscribeTile, 30);
			return tile.isEnabled();
		} catch (TimeoutException e) {
			System.out.println("unsubscribeTile tile not present: " + e.getMessage());
			return false;
		}
	}

	public void clickUnsubscribeTile() {
		try {
			By UnsubscribeTileLocator = By.xpath("//button[@class='btn-block dashboard-stat red']");
			WebElement excludeTile = WaitUtil.waitForElementClickable(driver, UnsubscribeTileLocator, 40);
			excludeTile.click();
		} catch (TimeoutException e) {
			System.out.println("Unsubscribe tile count is 0 & button is disabled, cannot click.");
		}
	}

	public By getDropdownLocator() {
		return sortByDropdown;
	}

	/*
	 * public void sortAllOptions(WebDriver driver) { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(60));
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));
	 * 
	 * Select dropdown = new Select(driver.findElement(sortByDropdown));
	 * List<WebElement> options = dropdown.getOptions();
	 * 
	 * for (int i = 1; i < options.size(); i++) { // skip "Sort by"
	 * dropdown.selectByIndex(i);
	 * 
	 * // Optional: log selected System.out.println("Selected: " +
	 * dropdown.getFirstSelectedOption().getText());
	 * 
	 * // Optional: wait for data refresh WaitUtil.waitForLoaderToDisappear(driver,
	 * 25);
	 * 
	 * try { Thread.sleep(1000); // short UI settle time } catch
	 * (InterruptedException e) { e.printStackTrace(); } }
	 * 
	 * }
	 */

	public void sortAllOptions(WebDriver driver) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		// Ensure dropdown is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));

		Select dropdown = new Select(driver.findElement(sortByDropdown));
		List<WebElement> options = dropdown.getOptions();

		for (int i = 1; i < options.size(); i++) {
			dropdown.selectByIndex(i);

			// Wait for loader to disappear after each sort
			WaitUtil.waitForLoaderToDisappear(driver, 40);

			// Wait for first publish icon as an indicator of loaded result
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//i[@class='fa fa-trash-o trashIconCustomization'])[1]")));

			System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());
			Thread.sleep(1000); // Optional short wait for UI animation
		}
	}

	public void sortAllTileOptions(WebDriver driver) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		// ✅ Loop through dropdown options, refreshing elements each time
		for (int i = 1;; i++) {
			try {
				// 🔁 Re-locate dropdown and reinitialize Select to avoid stale element
				wait.until(ExpectedConditions.visibilityOfElementLocated(AllTilesortByDropdown));
				Select Alldropdown = new Select(driver.findElement(AllTilesortByDropdown));

				List<WebElement> options = Alldropdown.getOptions();

				// 🔁 Break the loop if we reach the end of options
				if (i >= options.size())
					break;

				// ✅ Select option by index
				Alldropdown.selectByIndex(i);

				// ✅ Wait for loader to disappear (custom utility)
				WaitUtil.waitForLoaderToDisappear(driver, 40);

				// ✅ Wait for results table to appear as confirmation of reload
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='sample_editable_1']/thead/tr")));

				// ✅ Print the selected option
				System.out.println("Selected: " + Alldropdown.getFirstSelectedOption().getText());

				Thread.sleep(1000); // Optional: short delay for UI animation
			} catch (StaleElementReferenceException e) {
				System.out.println("⚠️ Caught stale element - retrying index " + i);
				i--; // 🔁 Retry the same index
			}
		}
	}

}