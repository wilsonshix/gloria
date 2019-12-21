package com.ivorytech.pageFactory.webBrowserApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ivorytech.utilities.WebElementAction;

import io.appium.java_client.AppiumDriver;

public class GooglePage implements WebElementAction {

	WebDriver driver;

	/** Constructor */
	public GooglePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/** Variables */
	String baseURL = "https://www.darty.com/";

	/** Web Elements */
	
	
	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input") 
	@CacheLookup 
	public WebElement txt_searchBar; 
	
	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]") 
	@CacheLookup 
	public WebElement btn_search; 
	


}
