package WebAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationConfirmationPage {
	
	WebDriver driver;
		
	RegistrationConfirmationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".result")
	WebElement message;
	
	@FindBy(css = ".register-continue-button")
	public WebElement continueButton;

	
	public String getMessage() {
		return message.getText();
	}
}
