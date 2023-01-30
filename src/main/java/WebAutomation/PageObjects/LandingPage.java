package WebAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebAutomation.GenericUtilities.CommonFunctions;

public class LandingPage extends CommonFunctions {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ico-register")
	public WebElement registerButton;

	@FindBy(css = "#customerCurrency")
	public WebElement currencySelection;

	@FindBy(css = ".actual-price")
	public WebElement priceTag;

		
	
	@FindBy(css = ".poll-vote-error")
	WebElement pollErrorMessage;

	@FindBy(css = ".ico-login")
	public WebElement loginButton;

	public RegistrationPage clickOnRegister() {
		registerButton.click();
		return new RegistrationPage(driver);
	}

	public LoginPage clickOnLogin() {
		loginButton.click();
		return new LoginPage(driver);
	}

	public void selectCurrency(String curr) {
		selectDropDownByText(currencySelection, curr);
	}

	public String getCurrencySymbol() {
		String price = priceTag.getText();
		return Character.toString(price.charAt(0));
	}
	
	public String getPollErrorMessage(String option) {
		selectPollOption(option);
		waitForElementToAppear(pollErrorMessage);
		return pollErrorMessage.getText();
		
	}

	
	

}