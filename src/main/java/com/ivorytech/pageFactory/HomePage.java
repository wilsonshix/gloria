package com.ivorytech.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
	
	
	
	
	

	public void goToLoginPage() { 
		System.out.println("Click on button Se connecter");
		performingAction(ClickOnWebElement(driver, lnk_Seconnecter));
	}


	

	// Vérifier que l'élément est cliquable => page is loaded !
	public boolean isAt() {    

		boolean isClickable = WaitForWebElement(driver, lnk_Seconnecter);
		int i = 0;
		while (isClickable == false && i < 3) {
			isClickable = WaitForWebElement(driver, lnk_Seconnecter);
			System.out.println("page is loading ...");
			i++;

			if(isClickable == false && i==3) {
				System.out.println("HomePage was not successfully loaded");
			}
		}        
		//System.out.println(isClickable);  
		return isClickable;
	}






}
