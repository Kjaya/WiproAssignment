package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import pages.BaseClass;

public class ProductCheckout extends BaseClass{
	

	
	@Test
	public void searchTest(){
		super.loadProperties();
		waitForElementPresence(By.id(prop.getProperty("SiginButton")));
	    driver.findElement(By.id(prop.getProperty("SiginButton"))).click();
	    
	    waitForElementPresence(By.id(prop.getProperty("emailormobile")));
	    Assert.assertTrue(driver.findElement(By.id(prop.getProperty("emailormobile"))).isDisplayed());
	    driver.findElement(By.id(prop.getProperty("emailormobile"))).sendKeys("9688240881");
	    
	    waitForElementPresence(By.id(prop.getProperty("logincontinue")));
	    driver.findElement(By.id(prop.getProperty("logincontinue"))).click();
	    
	    waitForElementPresence(By.id(prop.getProperty("password")));
	    driver.findElement(By.id(prop.getProperty("password"))).sendKeys("Happy6565");
	    
	    waitForElementPresence(By.id(prop.getProperty("login")));
	    driver.findElement(By.id(prop.getProperty("login"))).click();
	    
	    waitForElementPresence(By.xpath(prop.getProperty("languageSelection")));
	  //  Assert.assertTrue(driver.findElement(By.xpath(prop.getProperty("languageSelection"))).isDisplayed());
	    driver.findElement(By.xpath(prop.getProperty("languageSelection"))).click();
	    
	    waitForElementPresence(By.xpath(prop.getProperty("saveChanges")));
	    driver.findElement(By.xpath(prop.getProperty("saveChanges"))).click();
	    
	    waitForElementPresence(By.id(prop.getProperty("search")));
	    WebElement search = driver.findElement(By.id(prop.getProperty("search")));
	    search.sendKeys("65 inch TV");
	   ((RemoteWebDriver) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
	   waitForElementPresence(By.xpath(prop.getProperty("ratings")));
	   WebElement searchProductNameWE = driver.findElement(By.xpath(prop.getProperty("searchProductName")));
	   String searchProductName = searchProductNameWE.getText();
	   searchProductNameWE.click();
	   waitForElementPresence(By.xpath(prop.getProperty("checkoutProductName")));
	   WebElement checkoutProductNameWE = driver.findElement(By.xpath(prop.getProperty("checkoutProductName")));
	   String checkoutProductName = checkoutProductNameWE.getText();
	   checkoutProductNameWE.click();
	   
	   assertEquals(searchProductName, checkoutProductName);
	   
	   
	    
	}  


}