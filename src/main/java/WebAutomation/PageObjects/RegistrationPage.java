package WebAutomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebAutomation.GenericUtilities.CommonFunctions;

public class RegistrationPage extends CommonFunctions{
	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='male']")
	WebElement genderMale;

	@FindBy(xpath = "//span[@class='female']")
	WebElement genderFemale;

	@FindBy(id = "FirstName")
	WebElement firstName;

	@FindBy(id = "LastName")
	WebElement lastName;

	@FindBy(xpath = "//*[@name='DateOfBirthDay']")
	WebElement dateOfBirthDay;

	@FindBy(xpath = "//*[@name='DateOfBirthMonth']")
	WebElement dateOfBirthMonth;
	
	@FindBy(xpath = "//*[@name='DateOfBirthYear']")
	WebElement dateOfBirthYear;

	@FindBy(css = "#Email")
	WebElement email;

	@FindBy(css = "#Company")
	WebElement company;

	@FindBy(css = "#Newsletter")
	WebElement newsletter;

	@FindBy(css = "#Password")
	WebElement password;

	@FindBy(css = "#ConfirmPassword")
	WebElement confirmPassword;
	
	@FindBy(name = "register-button")
	WebElement register;
	
	

	public void enterGenderRadioButton(String gender) {
		if (gender.equals("Male")) {
			genderMale.click();
		} else {
			genderFemale.click();
		}
	}

	public RegistrationConfirmationPage performRegistration(String gender, String fName, String lName,String day,String month,String year,String Email,String Company,String Password ,String cnfPassword) {
        
		enterGenderRadioButton(gender);
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		selectDropDownByText(dateOfBirthDay,day);
		selectDropDownByText(dateOfBirthMonth,month);
		selectDropDownByText(dateOfBirthYear,year);
		email.sendKeys(Email);
		company.sendKeys(Company);
		selectCheckBox(newsletter);
		password.sendKeys(Password);
		confirmPassword.sendKeys(cnfPassword);
        register.click();
        return new RegistrationConfirmationPage(driver);
	}

}
