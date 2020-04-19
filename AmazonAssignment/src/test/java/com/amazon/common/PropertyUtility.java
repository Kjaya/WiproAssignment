package com.amazon.common;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.By;
/**
 * This class is used to load and read the elements from property file
 * @author Karthika
 * 
 * History :
 * 2020-Apr-18 Karthika : Property file containing methods to read values from property file
 *  
 */

public class PropertyUtility {
	
	Properties prop = new Properties();	
	public PropertyUtility(String fileName)
	{
		try{
			InputStream input =  null;
			input = new FileInputStream(fileName);
			prop.load(input);
		}
		catch (IOException e) {
			throw new RuntimeException("Couldn't load Properties", e);
		}
	}
	
	public String getProperty(String propertyKey){
		String propertyValue;
		propertyValue=prop.getProperty(propertyKey);
		return propertyValue;
	}
	
	public By getObject(String name)
	{
		By ret = null;
		String[] keyVal = getProperty(name).split("\\~");
		String key = keyVal[0].trim();
		String value = keyVal[1].trim();
		
		if(key.toLowerCase().equals("class")){
			ret = By.className(value);
		}else if(key.toLowerCase().equals("id")){
			ret = By.id(value);
		}else if(key.toLowerCase().equals("name")){
			ret = By.name(value);
		}else if(key.toLowerCase().equals("xpath")){
			ret = By.xpath(value);
		}
		return ret;
	}
}