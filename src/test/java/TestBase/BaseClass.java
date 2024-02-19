package TestBase;
 
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;
 
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
 
import Utilities.ExcelUtilities;
 
public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	public static ResourceBundle rb = ResourceBundle.getBundle("config");
	public static String fileName = "Data";
	public static ExcelUtilities excelUtilities = new ExcelUtilities();
	public static SoftAssert sa=new SoftAssert();

	@BeforeTest(groups = { "sanity" })
	@Parameters({ "os","browser"})
	public void setUpEnvi(String os,String br) throws IOException {
 
		logger = LogManager.getLogger(this.getClass());
		if(rb.getString("execution_env").equalsIgnoreCase("remote"))
	 	{	
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WINDOWS);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching os..");
			return;
		}

		//browser
		switch(br.toLowerCase())
		{
		case "chrome" : capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser.."); return;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	    }
		else if(rb.getString("execution_env").equalsIgnoreCase("local")) {
		switch (br.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOpt = new ChromeOptions();
			chromeOpt.addArguments("disable-notifications");
			driver = new ChromeDriver(chromeOpt);
			break;
		case "edge":
			EdgeOptions edgeOpt = new EdgeOptions();
			edgeOpt.addArguments("disable-notifications");
			driver = new EdgeDriver(edgeOpt);
			break;
		default:
			System.out.println("No browser is matching");
			return;
		}
	}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(getURL());
		driver.manage().window().maximize();
 
	}
 
	public String getURL() {
		return rb.getString("url");
	}
 
	public void screenshot(String fileName) throws IOException {
		TakesScreenshot srcshot = (TakesScreenshot) driver;
		File src = srcshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(".\\screenshots\\" + fileName + ".png"));
	}
 
	public String captureScreen(String file) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHss").format(new Date());
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
 
		String targetFilePath = System.getProperty("user.dir") + "\\ExtentReportScreenshots\\" + file + "_" + timeStamp
				+ ".png";
		File targetFile = new File(targetFilePath);
		srcFile.renameTo(targetFile);
		return targetFilePath;
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
	@AfterTest(groups = { "regression" })
	public void tearDown() {
		driver.quit();
	}
 
}