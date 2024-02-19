package TestCase;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import PageObject.UpcomingBikes;
import TestBase.BaseClass;

public class TC02_UpcomingBikes extends BaseClass {
	UpcomingBikes bikes;

	@Test(priority = 2, groups = { "regression" })
	public void ManufacturerDrpDown() throws IOException {
		bikes = new UpcomingBikes(driver);
		logger.info("*** Starting TC02_UpcomingBikes TestCase ***");

		screenshot("ManufacturerDropdown");
		logger.info("capture the screenshot, when manufacturer selected as HONDA");
		
		boolean actual=bikes.manufacturerAssert();
		sa.assertEquals(actual, true,"manufacturer is not displayed");
		logger.info("Validating the manufacturer dropdown is displayed or not");
		
		bikes.selectManufacturer(rb.getString("bikeManufacturer"));
		logger.info("Select Manufacturer as Honda");
		
		sa.assertAll();
	}

	@Test(priority = 3, dependsOnMethods = { "ManufacturerDrpDown" }, groups = { "regression" })
	public void bikeDetails() throws IOException, InterruptedException{
		bikes = new UpcomingBikes(driver);
		bikes.viewMore();
		logger.info("Click on View More");
		
		sleep(2000);
		List<String[]> bikeDetails = bikes.getDetailsOfBikes();
		logger.info("getting details of Honda bikes in the webPage");

		String sheetName = "BikeDetails";
		
		excelUtilities.deleteExcel(fileName);

		int i = 0, j = 0;
		for (String[] bike : bikeDetails) {
			j = 0;
			for (String data : bike) {
				excelUtilities.setCellData(fileName, sheetName, i, j++, data);
			}
			i++;
		}

		logger.info("*** Finishing TC02_UpcomingBikes TestCase ***");

	}

}
