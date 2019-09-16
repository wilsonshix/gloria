package com.ivorytech.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class ConnexionPage extends CommonOnPage  {

	//Constructor
	public ConnexionPage(WebDriver driver){
		PageFactory.initElements(driver, this);		
	}


	//Objects

	
	@FindBy(xpath="//*[@id='login']") 
	@CacheLookup 
	public static WebElement txt_username;  // public afin d'être vu dans la classe de test

	@FindBy(xpath="//*[@id='password']") 
	@CacheLookup	
	public static WebElement txt_password;

	@FindBy(xpath="//*[@id=\"form-identification\"]/div[3]/button")	
	@CacheLookup 
	public static WebElement btn_Connexion;



	

	








	    



}
