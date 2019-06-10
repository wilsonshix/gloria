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
	WebElement txt_username;

	@FindBy(xpath="//*[@id='password']")	
	@CacheLookup
	WebElement txt_password;

	@FindBy(how = How.XPATH, using = "//*[@id=\"form-identification\"]/div[3]/button")	
	@CacheLookup
	WebElement btn_Connexion;

	

	// Methods

	// Renseigner le nom d'utilisateur
	public void setUsername(String a){
		System.out.println("Fill --- textBox -- txt_username");
		performingAction(SendKeysOnWebElement(driver, txt_username, a));		
	}

	// Renseigner le mot de passe
	public void setPassword(String a){
		System.out.println("Fill --- textBox -- txt_password");
		performingAction(SendKeysOnWebElement(driver, txt_password, a));		
	}

// Cliquer sur le bouton de connexion	
	public void clickOnConnexion(){
		System.out.println("Click --- button -- btn_Connexion");
		performingAction(ClickOnWebElement(driver, btn_Connexion));		
	}



	// Vérifier qu'on est bien sur la page
				public boolean isAt() {    		 
					return isPageLoaded(driver, txt_username, "LoginPage");
				}

	    



}
