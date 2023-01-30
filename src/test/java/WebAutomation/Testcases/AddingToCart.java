package WebAutomation.Testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import WebAutomation.TestUtilities.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddingToCart extends BaseTest {
  @Test
  public void AddItemToCart() {
		
		 driver.findElement(By.cssSelector(".ico-login")).click();
		 driver.findElement(By.cssSelector(".email")).sendKeys("torwo@gmail.com");
		 driver.findElement(By.cssSelector(".password")).sendKeys("hurtlocker");
		 driver.findElement(By.cssSelector(".login-button")).click();
		 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ico-account"))));	
		 driver.findElement(By.cssSelector(".search-box-text")).sendKeys("htc one");
		 driver.findElement(By.cssSelector(".search-box-button")).click();
		 WebElement ele=driver.findElement(By.cssSelector(".item-box"));
		 Actions a = new Actions(driver);
		 a.moveToElement(ele).perform();
		 List<WebElement> product=driver.findElements(By.cssSelector(".item-box"));
		 for(WebElement w : product) {
			 System.out.println(w.getText());
			 String p=w.findElement(By.cssSelector(".product-title")).getText();
			 System.out.println(p);
			 if(p.equalsIgnoreCase("HTC One Mini Blue")) {
				 w.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
				 break;
			 }
		 }
		 
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".bar-notification-container"))));
		 String message=driver.findElement(By.cssSelector(".bar-notification-container")).getText();
		 Assert.assertEquals(message,"The product has been added to your shopping cart");
		 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".bar-notification-container"))));
		 driver.findElement(By.cssSelector(".cart-label")).click();
		 Assert.assertEquals(driver.findElement(By.cssSelector(".product-name")).getText(), "HTC One Mini Blue");
		 
		 
  }
}
