package com.ivorytech.pageFactory;

import org.openqa.selenium.WebDriver;
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
