package com.amazon.test;

import static com.amazon.common.Constants.APP_PROPERTIES_FILE_PATH;
import static com.amazon.common.Constants.PROPERTY_FILE_PATH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.common.PropertyUtility;
import com.amazon.pages.BaseClass;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
 * Actual class where product checkout test case handled
 * 
 * @author Karthika
 * 
 *         History : 2020-Apr-17 Karthika : Added simple test case to check
 *         positive flow of selecting the product and navigating to checkout
 *         screen 2020-Apr-18 Karthika : Handled Swipe event and Re-factored the
 *         testcase
 * 
 */

public class ProductCheckout extends BaseClass {
	PropertyUtility putility = new PropertyUtility(PROPERTY_FILE_PATH);
	private static final Logger LOGGER = Logger.getLogger(ProductCheckout.class.getName());
	By SiginButton = putility.getObject("SiginButton");
	By emailormobile = putility.getObject("emailormobile");
	By logincontinue = putility.getObject("logincontinue");
	By password = putility.getObject("password");
	By login = putility.getObject("login");
	By languageSelection = putility.getObject("languageSelection");
	By saveChanges = putility.getObject("saveChanges");
	By search = putility.getObject("search");
	By ratings = putility.getObject("ratings");
	By checkoutProductName = putility.getObject("checkoutProductName");
	By scrollTopEle = putility.getObject("scrollTopEle");

	PropertyUtility commonProp = new PropertyUtility(APP_PROPERTIES_FILE_PATH);

	@Test
	public void searchTest() {
		LoginToAmazon();
		// to select the language
		waitForElementPresence(languageSelection);
		clickElement(languageSelection);
		LOGGER.info("languageSelection radio button" + languageSelection + "  clicked");

		waitForElementPresence(saveChanges);
		clickElement(saveChanges);
		LOGGER.info("saveChanges button" + saveChanges + "  clicked");

		waitForElementPresence(search);
		setValue(search, "65 inch TV");
		((RemoteWebDriver) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
		LOGGER.info("Performed search");
		waitForElementPresence(ratings);
		rotateScreen();
		// Identify Elememt using Text usimg scroll gesture
		MobileElement element = scrollToTheProduct();
		boolean response = element.isDisplayed();
		assertTrue(response);
		LOGGER.info("Scrolled to particular product");

		String searchProductName = getText(element);
		element.click();
		boolean language = isElementDisplayed(languageSelection);
		if (language) {
			clickElement(languageSelection);
			LOGGER.info("languageSelection radio button" + languageSelection + "  clicked");
			waitForElementPresence(saveChanges);
			clickElement(saveChanges);
		}
		waitForElementPresence(checkoutProductName);
		MobileElement checkoutProductNameWE = (MobileElement) driver.findElement(checkoutProductName);
		String checkoutProductName = getText(checkoutProductNameWE);
		assertEquals(searchProductName, checkoutProductName);
		if (searchProductName.equalsIgnoreCase(checkoutProductName)) {
			LOGGER.info("Test case ran successfully");
		}
	}

	private void LoginToAmazon() {
		waitForElementPresence(SiginButton);
		clickElement(SiginButton);

		waitForElementPresence(emailormobile);
		Assert.assertTrue(isElementDisplayed(emailormobile));
		setValue(emailormobile, commonProp.getProperty("username"));
		LOGGER.info("username is set");

		waitForElementPresence(logincontinue);
		clickElement(logincontinue);

		waitForElementPresence(password);
		setValue(password, commonProp.getProperty("password"));

		waitForElementPresence(login);
		clickElement(login);
		LOGGER.info("Logged in successfully");
	}

	/* Method to scroll to the particular element */
	private MobileElement scrollToTheProduct() {
		MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.amazon.mShop.android.shopping:id/rs_search_results_root\")).scrollIntoView("
						+ "new UiSelector().text(\"Panasonic 164 cm (65 Inches) 4K Ultra HD Smart LED TV TH-65GX750D (Black) (2019 Model)\"))"));
		return element;
	}

}