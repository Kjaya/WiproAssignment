package com.amazon.test;

import static com.amazon.common.Constants.PROPERTY_FILE_PATH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;

import com.amazon.common.PropertyUtility;
import com.amazon.pages.BaseClass;
import com.amazon.pages.LoginPage;
import com.amazon.pages.ProductDetailPage;
import com.amazon.pages.ProductSearchPage;

import io.appium.java_client.MobileElement;

/**
 * Actual class where product checkout test case handled
 * 
 * @author Karthika
 * 
 *         History : 
 *         2020-Apr-17 Karthika : Added simple test case to check
 *         positive flow of selecting the product and navigating to checkout
 *         screen 
 *         2020-Apr-18 Karthika : Handled Swipe event and Re-factored the
 *         testcase
 *         2020-Apr-29 Karthika : Review comments incorporated
 * 
 */

public class ProductCheckout extends BaseClass {
	PropertyUtility putility = new PropertyUtility(PROPERTY_FILE_PATH);
	private static final Logger LOGGER = Logger.getLogger(ProductCheckout.class.getName());
	By languageSelection = putility.getObject("languageSelection");
	By saveChanges = putility.getObject("saveChanges");
	By search = putility.getObject("search");
	By ratings = putility.getObject("ratings");
	By checkoutProductName = putility.getObject("checkoutProductName");

	/*
	 * Testcase to compare the details of product search page with product checkout page
	 */
	@Test
	public void testProductCheckout() {
		LoginPage loginPage = new LoginPage();
		loginPage.loginToAmazon();
		// to select the language
		waitForElementPresence(languageSelection);
		clickElement(languageSelection);
		LOGGER.info("languageSelection radio button" + languageSelection + "  clicked");

		waitForElementPresence(saveChanges);
		clickElement(saveChanges);
		LOGGER.info("saveChanges button" + saveChanges + "  clicked");

		waitForElementPresence(search);
		ProductSearchPage searchPage = new ProductSearchPage();
		searchPage.searchTV();
		waitForElementPresence(ratings);
		searchPage.validateFilterOptions();

		// Rotate the screen from default portrait to landscape
		rotateScreen(ScreenOrientation.LANDSCAPE);

		// Identify Elememt using Text usimg scroll gesture
		ProductSearchPage productSearchPage = new ProductSearchPage();

		MobileElement element = productSearchPage.scrollToTheProduct();
		boolean response = element.isDisplayed();
		assertTrue(response);
		LOGGER.info("Scrolled to particular product");

		String searchProductName = productSearchPage.getProductText();
		element.click();

		if (isElementDisplayed(languageSelection)) {
			clickElement(languageSelection);
			LOGGER.info("languageSelection radio button" + languageSelection + "  clicked");

			waitForElementPresence(saveChanges);
			clickElement(saveChanges);
			LOGGER.info("saveChanges button" + saveChanges + "  clicked");
		}

		waitForElementPresence(checkoutProductName);
		ProductDetailPage productDetailPage = new ProductDetailPage();
		String checkoutProductName = productDetailPage.getProductTitle();
		assertEquals(searchProductName, checkoutProductName);
		if (searchProductName.equalsIgnoreCase(checkoutProductName)) {
			LOGGER.info("Test case ran successfully");
		}
	}

}