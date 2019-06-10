package com.ivorytech.pageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ivorytech.utility.WebElementAction;

public class UserDetailsPage extends CommonOnPage implements WebElementAction {

	WebDriver driver;

	//Constructor
	public UserDetailsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}



	//Objects

	@FindBy(xpath="//*[@id='main']/div[1]/div[1]/h1/u")
	WebElement espace_client;


	
	
	
	//Methods
	
	
	
	
	
	
	
	
	// Vérifier que l'élément est cliquable => page is loaded !
	public boolean isAt() {    		 
		return isPageLoaded(driver, espace_client, "UserDetailsPage");
	}
	


}

