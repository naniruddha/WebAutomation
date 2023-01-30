package WebAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebAutomation.GenericUtilities.CommonFunctions;

public class LoginPage extends CommonFunctions{
	WebDriver driver;

	public LoginPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".email")
	WebElement email;
	@FindBy(css = ".password")
	WebElement password;
	@FindBy(css = ".login-button")
	WebElement login;

	public HomePage userLogin(String emailId, String pwd) {
		email.sendKeys(emailId);
		password.sendKeys(pwd);
		login.click();
		return new HomePage(driver);
	}

}
