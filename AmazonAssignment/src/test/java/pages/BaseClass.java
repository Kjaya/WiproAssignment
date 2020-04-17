package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	public WebDriver driver;
	protected static BaseClass instance;

	@BeforeSuite
	@Parameters("Browser")
	public void setup(String Browser) throws InterruptedException {
		if (Browser.equals("Android")) {
			try {
				//File apkDir = new File("C:\\Users\\dk\\Downloads");
				//File apk = new File(apkDir, "Amazon_shopping.apk");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("udid", "A2QDU17111006740");
				capabilities.setCapability("deviceName", "A2QDU17111006740");
				capabilities.setCapability("platformVersion", "7.0");
				//capabilities.setCapability("app", apk.getAbsolutePath());
				capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
				capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
				capabilities.setCapability("noRest", true);
				capabilities.setCapability("autoGrantPermissions", true);
				Thread.sleep(50000);

				driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
				NetworkConnection mobileDriver = (NetworkConnection) driver;
				if (mobileDriver.getNetworkConnection() != ConnectionType.DATA) {
					// enabling Airplane mode
					System.out.println("Data is OFF");
					mobileDriver.setNetworkConnection(ConnectionType.DATA);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@AfterSuite
	public void teardown() {
		// driver.quit();
	}

	public static Properties prop;

	public void loadProperties() {
		try {
			prop = new Properties();
			FileInputStream locatorsConfig = new FileInputStream("src\\main\\resources\\configs\\locators.properties");
			prop.load(locatorsConfig);

		} catch (IOException e) {
			throw new RuntimeException("Couldn't load Properties", e);
		}
	}

	protected void waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	protected void isElementDisplayed(By locator) {

		driver.findElement(locator).isDisplayed();
	}

	protected void waitForElementPresence(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	

}
