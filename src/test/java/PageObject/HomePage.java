package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//LOCATOR
	
	@FindBy(xpath="//a[normalize-space()='New Bikes']")
	private WebElement newBikeDropDown;
	
	@FindBy(xpath="//span[normalize-space()='Upcoming Bikes']")
	private WebElement upComingBikeOption;
	

	//ACTIONS
	
	public void hoverOnNewBike() {
		Actions act = new Actions(driver);
		act.moveToElement(newBikeDropDown).build().perform();
	}
	
	public void clickOnUpcomingBike() {
		upComingBikeOption.click();
	}
	
	//Assert Methods
	public boolean newBikesAssert() {
		return newBikeDropDown.isDisplayed();
	}
	
	public boolean upcomingBikesAssert() {
		return upComingBikeOption.isDisplayed();
	}
	
}
