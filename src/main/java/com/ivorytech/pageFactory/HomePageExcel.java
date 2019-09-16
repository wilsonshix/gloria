package com.ivorytech.pageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ivorytech.utility.WebElementAction;


public class HomePageExcel extends CommonOnPage implements WebElementAction{

	WebDriver driver;

	//Constructor
	public HomePageExcel(WebDriver driver){
		this.driver =driver;
		PageFactory.initElements(driver, this);		
	}
	
		
	//Objects
	
	
	@FindBy(xpath="//*[@id='h_xxl_login_lien']")	
	@CacheLookup
	static WebElement lnk_Seconnecter;

	
	@FindBy(xpath="//*[@id='login']")		
	@CacheLookup
	WebElement txt_username;

	@FindBy(xpath="//*[@id='password']")	
	@CacheLookup
	WebElement txt_password;
	
	@FindBy(xpath="//*[@id=\"form-identification\"]/div[3]/button")	
	@CacheLookup
	WebElement btn_Connexion;
	
	// Methods
	
	public void seConnecter(){
		lnk_Seconnecter.click();
	}
	
	// Methods


	

}

