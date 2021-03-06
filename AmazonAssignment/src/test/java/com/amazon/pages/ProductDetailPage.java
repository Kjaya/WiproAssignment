package com.amazon.pages;

import static com.amazon.common.Constants.PROPERTY_FILE_PATH;

import java.util.logging.Logger;

import org.openqa.selenium.By;

import com.amazon.common.PropertyUtility;
import com.amazon.test.ProductCheckout;

import io.appium.java_client.MobileElement;

/**
 * Actual class where product search functionality handled
 * 
 * @author Karthika
 * 
 *  History : 
 *  2020-Apr-29 Karthika : Initial Version 
 *  2020-Apr-30 Karthika : Handled validations
 */

public class ProductDetailPage extends BaseClass{
	PropertyUtility putility = new PropertyUtility(PROPERTY_FILE_PATH);
	private static final Logger LOGGER = Logger.getLogger(ProductCheckout.class.getName());
	By checkoutProductName = putility.getObject("checkoutProductName");
	
	/* Method returns product title displayed in details page */
	public String getProductTitle() {
		MobileElement checkoutProductNameWE = (MobileElement) driver.findElement(checkoutProductName);
		LOGGER.info("Product title is found"+checkoutProductNameWE);
		String checkoutProductName = getText(checkoutProductNameWE);
		return checkoutProductName;
	}

}
