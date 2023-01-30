package WebAutomation.TestUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import WebAutomation.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	String browser;
	public LandingPage landingPage;
	public Properties prop;

	@BeforeMethod(alwaysRun=true)
	public void initDriver() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\WebAutomation\\GenericUtilities\\General.properties");
		prop = new Properties();
		prop.load(fis);
		browser = prop.getProperty("browser");
		System.out.println(browser);
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
//			driver = new ChromeDriver();
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
//					+ "\\src\\main\\java\\WebAutomation\\GenericUtilities\\chromedriver.exe");

		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.get(prop.getProperty("landingPage"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		landingPage = new LandingPage(driver);
	}

	public List<HashMap<String, String>> readDataFromJSON(File path) throws IOException {
		String content = FileUtils.readFileToString(path, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(content,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyyHHmm");  
	    Date date = new Date();  
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+"_"+formatter.format(date)+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+"_"+formatter.format(date)+".png";
		//return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
