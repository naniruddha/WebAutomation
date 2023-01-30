package WebAutomation.GenericUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import WebAutomation.PageObjects.MyCartPage;

public class CommonFunctions {
	WebDriver driver;

	public CommonFunctions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	@FindBy(css = ".cart-label")
	WebElement myCart;
	@FindBy(css = "#pollanswers-1")
	WebElement pollExcellentButton;

	@FindBy(css = "#pollanswers-2")
	WebElement pollGoodButton;

	@FindBy(css = "#pollanswers-3")
	WebElement pollPoorButton;

	@FindBy(css = "#pollanswers-4")
	WebElement pollVeryBadButton;

	@FindBy(css = "#vote-poll-1")
	public WebElement pollSubmit;
	
	
	public void selectCheckBox(WebElement element) {
		if (element.isSelected() == false) {
			element.click();
		}
	}

	public void unselectCheckBox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public void selectDropDownByText(WebElement element, String value) {
		Select ss = new Select(element);
		ss.selectByVisibleText(value);
	}

	public boolean waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	public boolean waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
		return true;
	}

	public void scrollToElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}

	public MyCartPage goToCart() {
		myCart.click();
		return new MyCartPage(driver);
	}
	
	public void selectPollOption(String option) {
		if (option.equalsIgnoreCase("Excellent")) {
			pollExcellentButton.click();
		} else if (option.equalsIgnoreCase("Good")) {
			pollGoodButton.click();
		} else if (option.equalsIgnoreCase("Poor")) {
			pollPoorButton.click();
		} else if (option.equalsIgnoreCase("Very Bad")) {
			pollVeryBadButton.click();
		}
		pollSubmit.click();
	}
}
