package com.ivorytech.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.ivorytech.utility.WebElementAction;

public class LoginPage extends CommonOnPage implements WebElementAction{

	WebDriver driver;

	//Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	

	//Objects

	
	@FindBy(xpath="//*[@id='login']") 
	@CacheLookup 
	public WebElement txt_username;  // public afin d'être vu dans la classe de test

	@FindBy(xpath="//*[@id='password']") 
	@CacheLookup	
	public WebElement txt_password;

	@FindBy(how = How.XPATH, using = "//*[@id=\"form-identification\"]/div[3]/button")	
	@CacheLookup 
	public WebElement btn_Connexion;

	

	

	// Créer un macther pour tous les noms des pages
	// Vérifier qu'on est bien sur la page
	public boolean isAt() {    		 
		return isPageLoaded(driver, txt_username, "LoginPage");
	}

	    



}
