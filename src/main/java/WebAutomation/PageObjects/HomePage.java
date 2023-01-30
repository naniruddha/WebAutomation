package WebAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebAutomation.GenericUtilities.CommonFunctions;

public class HomePage extends CommonFunctions {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ico-account")
	WebElement myAccount;

	@FindBy(css = ".search-box-text")
	WebElement searchBox;

	@FindBy(css = ".search-box-button")
	WebElement searchButton;

	@FindBy(css = ".item-box")
	WebElement product;

	@FindBy(css = ".item-box")
	List<WebElement> productList;

	@FindBy(css = ".bar-notification-container")
	public WebElement toastMessage;

	@FindBy(xpath = "//span[@class='poll-total-votes']")
	public WebElement pollMessage;

	By productTitle = By.cssSelector(".product-title");
	By addToCart = By.cssSelector(".product-box-add-to-cart-button");

	public boolean getLoginStatus() {
		return waitForElementToAppear(myAccount);
	}

	public void searchElementWithName(String item) {
		searchBox.sendKeys(item);
		searchButton.click();
		scrollToElement(product);
	}

	public void selectElementByName(String Name) {

		for (WebElement w : productList) {
			String p = w.findElement(productTitle).getText();
			if (p.equalsIgnoreCase(Name)) {
				w.findElement(addToCart).click();
				break;
			}
		}

	}

	public String getToastMessage() {
		waitForElementToAppear(toastMessage);
		return toastMessage.getText();
	}

	public String getPollMessage(String option) {
			selectPollOption(option);
			waitForElementToAppear(pollMessage);
			return pollMessage.getText();
	}

}
