package com.ivorytech.pageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;


public class HomePageExcel {

	WebDriver driver;

	//Constructor
	public HomePageExcel(WebDriver driver){
		this.driver =driver;
		PageFactory.initElements(driver, this);		
	}
	
		
	//Objects
	
	//@FindBy(how = How.XPATH, using = "//*[@id='h_xxl_login_lien']")	
	@FindBy(xpath= "//*[@id='h_xxl_login_lien']")	
	@CacheLookup
	static WebElement lnk_Seconnecter;


	
	// Methods
	
	public void seConnecter(){
		lnk_Seconnecter.click();
	}
	

}

