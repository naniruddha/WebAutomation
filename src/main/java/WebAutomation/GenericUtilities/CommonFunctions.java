package WebAutomation.GenericUtilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions {
	
	public void selectCheckBox(WebElement element) {
		if(element.isSelected()==false) {
			element.click();
		}
	}
	public void unselectCheckBox(WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public void selectDropDownByText(WebElement element,String value) {
		Select ss=new Select(element);
		ss.selectByVisibleText(value);
	}

}
