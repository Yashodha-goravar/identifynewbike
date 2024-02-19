package TestCase;

import java.io.IOException;
import org.testng.annotations.Test;

import PageObject.UsedCars;
import TestBase.BaseClass;

public class TC03_UsedCars extends BaseClass {

	UsedCars cars;

	@Test(priority = 4, groups = {"regression"})
	public void hoverOnUsedCars() throws IOException{
		cars = new UsedCars(driver);
		logger.info("*** Starting TC03_UsedCars TestCase ***");
		
		cars.scrollUp();
		logger.info("Again scroll to top of the page");
		
		boolean actual=cars.usedCarsAssert();
		sa.assertEquals(actual, true, "used car dropdown is not displayed");
		logger.info("Validating the used cars dropdown is displayed or not");
		
		//sleep(2000);
		cars.usedCars();
		logger.info("Hovering on the Used Cars dropdown");
	
		screenshot("HoverOnUsedCars");
		logger.info("Capture the screenshot when hovered on the used car dropdown");
	
	}

	@Test(priority = 5, dependsOnMethods = { "hoverOnUsedCars" }, groups = { "regression" })
	public void location() throws IOException {
		cars = new UsedCars(driver);
		
		cars.clickOnChennai();
		logger.info("Clicked on the Chennai location");

		screenshot("ChennaiLocation");
		logger.info("Capture the screenshot when clicked on the location");
		
	}

	@Test(priority = 6, dependsOnMethods = { "hoverOnUsedCars", "location" }, groups = { "regression" })
	public void brandAvailable() throws IOException, InterruptedException {
		cars = new UsedCars(driver);

		cars.brand();
		logger.info("Scrolldown to view brand list");

		String[] brands = cars.brandPopular();
		int i = 0;
		for (String brand : brands) {
			excelUtilities.setCellData("Data", "PopularBrands", i++, 0, brand);
		}
		logger.info("Collecting the list of popular brand available");

		screenshot("popularBrands");
		logger.info("Capture the screenshot of popular brands");

		cars.home();
		logger.info("Navigated to HomePage again");

		logger.info("*** Finishing TC03_UsedCars TestCase ***");

	}
}
