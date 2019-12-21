package com.ivorytech.test.mobileApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ivorytech.baseTests.AppiumBaseTests;
import com.ivorytech.baseTests.SeleniumBaseTests;
import com.ivorytech.pageFactory.webBrowserApplication.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;



@Listeners({ com.ivorytech.utilities.listeners.AppiumTestListener.class })
@Epic("Regression Tests")
@Feature("Login Tests // Renseigner l'US")
public class AllureReportingTest extends AppiumBaseTests{
	
	//Test Data
    String wrongUsername = "willdjako@gmail.com";
    String wrongPassword = "Alexandrine";
    
    String validUsername = "willdjako05@gmail.com";
    String validPassword = "Alexandrine1";
    
	String baseURL = "https://www.google.com/";
    
    @Test (priority = 0, description="Valid Login Scenario with good username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with good username and good password.")
    @Story("Valid username and password login test")    
    public void FirstSearchOnGoogle() {
    	googlePage.GoToURL(driver, baseURL);
    	googlePage.Saisir(driver, googlePage.txt_searchBar,"hello");
    	googlePage.Cliquer(driver, googlePage.btn_search);        
    }
    
    
    
    

  /*  @Test (priority = 0, description="Valid Login Scenario with good username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with good username and good password.")
    @Story("Valid username and password login test")
    public void ValidLoginTest_ValidUserNameValidPassword () {
        homePage
            .goToDarty()
             .goToLoginPage();
        
        loginPage
        .connexion(validUsername, validPassword);
        
    }
    
    
    @Test (priority = 0, description="InValid Login Scenario with wrong username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with wrong username and wrong password.")
    @Story("Invalid username and password login test")
    public void invalidLoginTest_InvalidUserNameInvalidPassword () {
        homePage
            .goToDarty()
             .goToLoginPage();
        
        loginPage
        .connexion(wrongUsername, wrongPassword);
        
    }
	
	*/
	

}
