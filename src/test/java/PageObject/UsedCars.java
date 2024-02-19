package PageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UsedCars extends BasePage {

	public UsedCars(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//a[@href='/used-car']")
	private WebElement usedCarDropDown;

	@FindBy(xpath = "//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")
	private WebElement location;

	@FindBy(xpath = "//label[normalize-space()='Under 10 Lakhs']")
	private WebElement brand;

	@FindBy(xpath = "//div[text()='Popular Models']/following-sibling::div[1]//li/label")
	private List<WebElement> popularBrands;

	@FindBy(xpath = "//span[normalize-space()='Home']")
	private WebElement homeLink;

	// Actions
	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
	}

	public void usedCars() {
		Actions act = new Actions(driver);
		act.moveToElement(usedCarDropDown).build().perform();
	}

	public void clickOnChennai() {
		location.click();
	}

	public void brand() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", brand);
	}

	public String[] brandPopular() {
		int size = popularBrands.size();
		String[] brands = new String[size];
		for (int i = 0; i < size; i++) {
			String brand = popularBrands.get(i).getText();
			System.out.println(brand);
			brands[i] = brand;
		}
		return brands;

	}

	public void home() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", homeLink);
	}

	// Assert method
	public boolean usedCarsAssert() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usedCarDropDown.isDisplayed();
	}
}
