package WebAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage{
	
	WebDriver driver;

	public LandingPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
    @FindBy(css=".ico-register")
    public WebElement registerButton;
    
    @FindBy(css=".ico-login")
    public WebElement loginButton;
    
    public RegistrationPage clickOnRegister() {
    	registerButton.click();
    	return new RegistrationPage(driver);
    }
    
    public LoginPage clickOnLogin() {
    	loginButton.click();
    	return new LoginPage();
    }
}