package com.ivorytech.pageFactory.webBrowserApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ivorytech.utilities.WebElementAction;

import io.qameta.allure.Step;


public class HomePage extends CommonOnPage implements WebElementAction {

	WebDriver driver;

	/** Constructor */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/** Variables */
	String baseURL = "https://www.darty.com/";

	/** Web Elements */
	
	
	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input") 
	@CacheLookup 
	public WebElement txt_searchBar; 
	
	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]") 
	@CacheLookup 
	public WebElement btn_search; 
	

	/** Page Methods */

	// Methods à généraliser
	
    //Go to Homepage
    @Step("Open Darty.com Step...")
    public HomePage goToDarty() {
        driver.get(baseURL);
        return this;
    }

	//Atteindre la page de connexion
	@Step("Open login page ...")
	public void goToLoginPage() {
		System.out.println("Click on button Se connecter");
		performingClickOnWebElement(driver, lnk_PageConnexion);
	}
	
	
	//Go to LoginPage
    @Step("Go to Login Page Step...")
    public LoginPage goToLoginPage2() {
    	performingClickOnWebElement(driver, lnk_PageConnexion);
        return new LoginPage(driver);
    }
    
    

	// Vérifier qu'on est bien sur la page
	@Step("Check Homepage is opened ...")
	public boolean isAt() {
		return isPageLoaded(driver, lnk_PageConnexion, "HomePage");
	}

}
