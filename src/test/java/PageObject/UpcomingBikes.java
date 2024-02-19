package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import Utilities.Screenshots;

public class UpcomingBikes extends BasePage {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public UpcomingBikes(WebDriver driver) {
		super(driver);
	}

	// Locators

	@FindBy(xpath = "//select[@id='makeId']")
	private WebElement manufacturers;

	@FindBy(xpath = "//*[@data-track-label='view-more-models-button']")
	private WebElement viewMore;

	@FindBy(xpath = "//a[@data-track-label='model-name']")
	private List<WebElement> ListOfModelName;

	@FindBy(xpath = "//*[@id=\"modelList\"]/li/div/div[3]/div[1]")
	private List<WebElement> ListOfBikePrices;

	@FindBy(xpath = "//*[@id=\"modelList\"]/li/div/div[3]/div[2]")
	private List<WebElement> ListOfBikeLaunchDate;

	

	@FindAll(@FindBy(xpath = "//div[@class='p-15 pt-10 mke-ryt rel']/parent::div"))
	private List<WebElement> bikeDiv;

	
	//Actions
	
	public void selectManufacturer(String bikeManufacturer) {
		Select manufacturerDrpDown = new Select(manufacturers);
		manufacturerDrpDown.selectByVisibleText(bikeManufacturer);
	}
	
	//Assert Method
		public boolean manufacturerAssert() {
			return manufacturers.isDisplayed();
		}
	
		
	public void viewMore() {

		js.executeScript("arguments[0].scrollIntoView();", viewMore);

		js.executeScript("arguments[0].click();", viewMore);
	}

	List<String[]> bikeDetails = new ArrayList<>();
	String[] bike;
	double priceDouble = 0.0;
	WebElement currentBike;

	public List<String[]> getDetailsOfBikes() throws InterruptedException {
		int size = ListOfBikePrices.size();

		for (int i = 0; i < size; i++) {
			bike = new String[3];
			currentBike = bikeDiv.get(i);

			js.executeScript("arguments[0].scrollIntoView();", currentBike);

			String price = ListOfBikePrices.get(i).getText();
			String[] words = price.split(" ");

			if (price.contains("Lakh"))
				priceDouble = Double.parseDouble(words[1]);
			else {
				String notLakh = words[1].replaceAll(",", "");
				priceDouble = Double.parseDouble(notLakh);
				priceDouble = priceDouble / 100000;
			}
			if (priceDouble <= 4.0) {
				bike[0] = ListOfModelName.get(i).getText();
				bike[1] = ListOfBikePrices.get(i).getText();
				bike[2] = ListOfBikeLaunchDate.get(i).getText();
				bikeDetails.add(bike);
				printBike(bike);
				
				//Screenshots.captureScreen(currentBike, bike[0].trim(), "Bikes");
				Thread.sleep(3000);
			}

		}
		return bikeDetails;

	}

	void printBike(String[] bike) {
		for (String b : bike) {
			System.out.println(b);
		}
	}
	
	
	

}
