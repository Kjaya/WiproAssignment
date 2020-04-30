package com.amazon.pages;

import static com.amazon.common.Constants.PROPERTY_FILE_PATH;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.amazon.common.PropertyUtility;
import com.amazon.test.ProductCheckout;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;


/**
 * Actual class where product search functionality handled
 * 
 * @author Karthika
 * 
 *         History : 
 *         2020-Apr-29 Karthika : Added Search method to perform search functions
 *         2020-Apr-30 Karthika : Handled scroll event 
 * 
 */

public class ProductSearchPage extends BaseClass{
	PropertyUtility putility = new PropertyUtility(PROPERTY_FILE_PATH);
	private static final Logger LOGGER = Logger.getLogger(ProductCheckout.class.getName());
	By search = putility.getObject("search");
	By filter = putility.getObject("filter");
	By opt_Filter = putility.getObject("opt_Filter");
	By opt_Catogries = putility.getObject("opt_Catogries");
	By opt_Sort = putility.getObject("opt_Sort");
	By opt_Television = putility.getObject("opt_Television");
	By opt_TelevisionSize = putility.getObject("opt_TelevisionSize");
	
	MobileElement element;
	
	/* Method to perform Search operation */
	public void searchTV() {
		setValue(search, "65 inch TV");
		((RemoteWebDriver) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
		LOGGER.info("Performed search");
	}
	
	/* Method to scroll to the particular element */
	public MobileElement scrollToTheProduct() {
		element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.amazon.mShop.android.shopping:id/rs_search_results_root\")).scrollIntoView("
						+ "new UiSelector().text(\"Panasonic 164 cm (65 Inches) 4K Ultra HD Smart LED TV TH-65GX750D (Black) (2019 Model)\"))"));
		return element;
	}
	
	/* Method to get the product title */
	public String getProductText() {
		return getText(element);
	}
	
	/* Method to validate filter options */
	public void validateFilterOptions() {
		clickElement(filter);
		waitForElementPresence(opt_Filter);
		Assert.assertTrue(isElementDisplayed(opt_Catogries));
		Assert.assertTrue(isElementDisplayed(opt_Sort));
		Assert.assertTrue(isElementDisplayed(opt_Television));
		Assert.assertTrue(isElementDisplayed(opt_TelevisionSize));
		clickElement(filter);
	}
}
