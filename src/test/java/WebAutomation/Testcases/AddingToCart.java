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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import WebAutomation.PageObjects.HomePage;
import WebAutomation.PageObjects.LoginPage;
import WebAutomation.PageObjects.MyCartPage;
import WebAutomation.TestUtilities.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddingToCart extends BaseTest {
	@Test(dataProvider="cartDetails")
	public void AddItemToCart(HashMap<String,String> input) {
		LoginPage lpage = landingPage.clickOnLogin();
		HomePage hpage = lpage.userLogin(input.get("email"), input.get("password"));
		Assert.assertTrue(hpage.getLoginStatus());
		hpage.searchElementWithName(input.get("searchtext"));
		hpage.selectElementByName(input.get("displaytext"));
		hpage.getToastMessage();
		Assert.assertEquals(hpage.getToastMessage(), prop.getProperty("addedToCartMessage"));
		hpage.waitForElementToDisappear(hpage.toastMessage);
		MyCartPage mcpage = hpage.goToCart();
		Assert.assertEquals(mcpage.getProduct(),input.get("displaytext"));
	}
	
	@DataProvider()
	public Object[][] cartDetails() throws IOException {
		List<HashMap<String, String>> data = readDataFromJSON(new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\WebAutomation\\TestData\\AddingToCartTestData.json"));
		return new Object[][] { {data.get(0)} };

	}
}
