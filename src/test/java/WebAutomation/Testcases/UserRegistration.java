package WebAutomation.Testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import WebAutomation.PageObjects.HomePage;
import WebAutomation.PageObjects.LandingPage;
import WebAutomation.PageObjects.LoginPage;
import WebAutomation.PageObjects.RegistrationConfirmationPage;
import WebAutomation.PageObjects.RegistrationPage;
import WebAutomation.TestUtilities.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserRegistration extends BaseTest {

	@Test(dataProvider = "registerDetails", priority = -1)
	public void userRegister(HashMap<String, String> input) {
		RegistrationPage rpage = landingPage.clickOnRegister();
		RegistrationConfirmationPage cpage = rpage.performRegistration(input.get("gender"), input.get("firstname"),
				input.get("lastname"), input.get("day"), input.get("month"), input.get("year"), input.get("email"),
				input.get("company"), input.get("password"), input.get("cnfpassword"));
		String actualMessage = cpage.getMessage();
		Assert.assertEquals(actualMessage, prop.getProperty("registerSuccess"));
		cpage.continueButton.click();
	}

	@Test(dataProvider = "loginDetails",groups= {"Regression"})
	public void userLogin(HashMap<String, String> input) {
		LoginPage lpage = landingPage.clickOnLogin();
		HomePage hpage = lpage.userLogin(input.get("email"), input.get("password"));
		Assert.assertTrue(hpage.getLoginStatus());
	}

	@DataProvider()
	public Object[][] registerDetails() throws IOException {
		List<HashMap<String, String>> data = readDataFromJSON(new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\WebAutomation\\TestData\\RegistrationTestData.json"));
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	@DataProvider()
	public Object[][] loginDetails() throws IOException {
		List<HashMap<String, String>> data = readDataFromJSON(new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\WebAutomation\\TestData\\LoginTestData.json"));
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
