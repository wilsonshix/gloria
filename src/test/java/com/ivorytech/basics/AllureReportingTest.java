package com.ivorytech.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ivorytech.baseTests.SeleniumBaseTests;
import com.ivorytech.pageFactory.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;



@Listeners({ com.ivorytech.utilities.listeners.TestListener.class })
@Epic("Regression Tests")
@Feature("Login Tests")
public class AllureReportingTest extends SeleniumBaseTests{
	
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
    	homePage.GoToURL(driver, baseURL);
    	homePage.Saisir(driver, homePage.txt_searchBar,"hello");
    	homePage.Cliquer(driver, homePage.btn_search);        
    }
    
    
    
    

    @Test (priority = 0, description="Valid Login Scenario with good username and password.")
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
	
	
	

}
