package com.ivorytech.pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ivorytech.utility.WebElementAction;

public class HomePage extends CommonOnPage implements WebElementAction{

	WebDriver driver;

	//Constructor
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}


	//Objects




	// Methods
	
	
	
	
	
	
//Atteindre la page de connexion
	public void goToLoginPage() { 
		System.out.println("Click on button Se connecter");
		performingAction(ClickOnWebElement(driver, lnk_Seconnecter));
	}


	

	// Vérifier qu'on est bien sur la page 
	public boolean isAt() {    		 
		return isPageLoaded(driver, lnk_Seconnecter, "HomePage");
	}
	



}
