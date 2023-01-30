package WebAutomation.Testcases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import WebAutomation.PageObjects.HomePage;
import WebAutomation.PageObjects.LoginPage;
import WebAutomation.TestUtilities.BaseTest;

public class CommunityPollTest extends BaseTest {
	@Test(dataProvider="pollDetails",groups= {"Regression"})
	public void communityPollwithoutLogin(HashMap<String,String> input) {
		String message = landingPage.getPollErrorMessage(input.get("option"));
		Assert.assertEquals(message, prop.getProperty("pollErrorMessage"));
	}
	
	@Test(dataProvider="pollDetails")
	public void communityPollwithLogin(HashMap<String,String> input) {
		LoginPage lpage = landingPage.clickOnLogin();
		HomePage hpage = lpage.userLogin(input.get("email"), input.get("password"));
		String message = hpage.getPollMessage(input.get("option"));
		Assert.assertTrue(message.contains("vote"));
	}
	
	@DataProvider()
 	public Object[][] pollDetails() throws IOException{
 		List<HashMap<String, String>> data = readDataFromJSON(new File(System.getProperty("user.dir")
 				+ "\\src\\test\\java\\WebAutomation\\TestData\\CommunityPollTestData.json"));
 		return new Object[][] { {data.get(0)}, {data.get(1)} };
 	}
	

}
