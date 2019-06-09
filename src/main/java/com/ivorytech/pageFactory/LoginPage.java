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

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[3]/form/div[3]/button")	
	@CacheLookup
	WebElement btn_Connexion;



	// Methods

	public void setUsername(String a){
		System.out.println("Fill --- textBox -- txt_username");
		performingAction(SendKeysOnWebElement(driver, txt_username, a));		
	}

	public void setPassword(String a){
		System.out.println("Fill --- textBox -- txt_password");
		performingAction(SendKeysOnWebElement(driver, txt_password, a));		
	}

	public void clickOnConnexion(){
		System.out.println("Click --- button -- btn_Connexion");
		performingAction(ClickOnWebElement(driver, btn_Connexion));		
	}



	// Vérifier que l'élément est cliquable => page is loaded !
				public boolean isAt() {    
			    	
			        boolean isClickable = WaitForWebElement(driver, btn_Connexion);
			        int i = 1;
			        while (isClickable == false && i < 4) {
			        	isClickable = WaitForWebElement(driver, btn_Connexion);
			            System.out.println("page is loading ...");
			            i++;
			            
			            if(isClickable == false && i==4) {
			            	System.out.println("LoginPage was not successfully loaded");
				        }
			        }        
			        
			        return isClickable; 
				}



	    



}
