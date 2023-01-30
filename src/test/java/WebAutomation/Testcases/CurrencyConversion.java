package WebAutomation.Testcases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import WebAutomation.TestUtilities.BaseTest;

public class CurrencyConversion extends BaseTest{
	
     @Test(dataProvider="currencyDetails",groups= {"Regression"})
     public void currencyConvTest(HashMap<String,String> input) {
    	 landingPage.selectCurrency(input.get("currency"));
    	 Assert.assertEquals(landingPage.getCurrencySymbol(),input.get("symbol"));
     }
     
     @DataProvider()
 	public Object[][] currencyDetails() throws IOException{
 		List<HashMap<String, String>> data = readDataFromJSON(new File(System.getProperty("user.dir")
 				+ "\\src\\test\\java\\WebAutomation\\TestData\\CurrencyConversionData.json"));
 		return new Object[][] { {data.get(0)}, {data.get(1)} };
 	}
	
}
