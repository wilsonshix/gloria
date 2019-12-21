package com.ivorytech.baseTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.ivorytech.pageFactory.webBrowserApplication.*;

import io.appium.java_client.AppiumDriver;

import org.testng.annotations.*;


public class SeleniumBaseTests {
	
	public WebDriver driver;
	
    public HomePage homePage;
    public LoginPage loginPage;

    public AppiumDriver getDriver() {
        return (AppiumDriver) driver;
    }

    @BeforeClass
    public void classLevelSetup() {
    	
    	System.setProperty("webdriver.chrome.driver","C:\\SeleniumGecko\\chromedriver78.exe");
		driver = new ChromeDriver();        
		
    	
    			
    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
