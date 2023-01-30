package WebAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebAutomation.GenericUtilities.CommonFunctions;

public class MyCartPage extends CommonFunctions {
	WebDriver driver;
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".product-name")
	WebElement productName;
	
	public String getProduct() {
		return productName.getText();
	}
	

}
