package com.amazon.pages;

import static com.amazon.common.Constants.APP_PROPERTIES_FILE_PATH;
import static com.amazon.common.Constants.PROPERTY_FILE_PATH;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.amazon.common.PropertyUtility;
import com.amazon.test.ProductCheckout;


/**
 * Actual class where product search functionality handled
 * 
 * @author Karthika
 * 
 *  History : 
 *  2020-Apr-29 Karthika : Initial Version with login method
 *  2020-Apr-30 Karthika : Handled few validations
 */


public class LoginPage extends BaseClass{
	PropertyUtility putility = new PropertyUtility(PROPERTY_FILE_PATH);
	private static final Logger LOGGER = Logger.getLogger(ProductCheckout.class.getName());
	By SiginButton = putility.getObject("SiginButton");
	By emailormobile = putility.getObject("emailormobile");
	By logincontinue = putility.getObject("logincontinue");
	By password = putility.getObject("password");
	By login = putility.getObject("login");
	PropertyUtility commonProp = new PropertyUtility(APP_PROPERTIES_FILE_PATH);

	/* Method to perform login to amazon app */
	public void loginToAmazon() {
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
}
