package TestCase;

import java.io.IOException;
import org.testng.annotations.Test;

import PageObject.LoginPage;
import TestBase.BaseClass;

public class TC04_LoginPage extends BaseClass {

	LoginPage lp;

	@Test(priority = 8, groups = { "sanity" })
	public void LoginPageInHome() throws IOException, InterruptedException {
		lp = new LoginPage(driver);
		logger.info("*** Starting TC04_LoginPage TestCase ***");

		lp.loginPage();
		logger.info("Clicked on the login link in homepage");

		screenshot("LoginPage");
		logger.info("Capture the screenshot of the login page");

		lp.clickOnGoogle();
		screenshot("ClickOnGoogle");
		logger.info("Clicked on the Google to login");

		lp.navigationToLoginPage();
		logger.info("Switched to new child window to login");
	}

	@Test(priority = 9, dependsOnMethods = { "LoginPageInHome" }, groups = { "regression" })
	public void errorMessage() throws IOException {
		lp = new LoginPage(driver);
		
		String errorMsg = lp.emailVerification(rb.getString("email"));
		System.out.println("Error Message: " + errorMsg);
		logger.info("Provide the invalid email inorder to get Error message");
		
		sa.assertEquals(errorMsg, "Couldn’t find your Google Account");
		sa.assertAll();
		logger.info("Validating the error message displayed");

		screenshot("ErrorMessageDisplayed");
		logger.info("Capture the screenshot after error message displayed on page");

		logger.info("*** Starting TC04_LoginPage TestCase ***");

	}
}
