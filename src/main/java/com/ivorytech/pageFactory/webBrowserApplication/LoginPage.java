package com.ivorytech.pageFactory.webBrowserApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ivorytech.utilities.WebElementAction;

import io.qameta.allure.Step;

public class LoginPage extends CommonOnPage implements WebElementAction{

	WebDriver driver;

	/** Constructor */
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	/** Variables */

	/** Web Elements */
	
	@FindBy(xpath="//*[@id='login']") 
	@CacheLookup 
	public WebElement txt_username;  // public afin d'être vu dans la classe de test

	@FindBy(xpath="//*[@id='password']") 
	@CacheLookup	
	public WebElement txt_password;

	@FindBy(how = How.XPATH, using = "//*[@id=\"form-identification\"]/div[3]/button")	
	@CacheLookup 
	public WebElement btn_Connexion;

	

    // Se connecter
    @Step("Login Step with username: {0}, password: {1}, for method: {method} ...")
    public LoginPage connexion(String login, String pwd) {
    	Saisir(driver, txt_username, login);
    	Saisir(driver, txt_password, pwd);
    	performingClickOnWebElement(driver, btn_Connexion);
		return this;    	
    }
	

	// Créer un macther pour tous les noms des pages
	// Vérifier qu'on est bien sur la page
	@Step("Check login page is opened ...")
	public boolean isAt() {    		 
		return isPageLoaded(driver, txt_username, "LoginPage");
	}

	    



}
