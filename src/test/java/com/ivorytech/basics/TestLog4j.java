package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;

import com.ivorytech.driverFactory.DriverManager;
import com.ivorytech.pageFactory.*;


public class TestLog4j {
	
	DriverManager driverManager;
    WebDriver driver;
    String browser = "Chrome";
    String url = "https://www.darty.com";
    String search = "lenovo";
    
    @BeforeTest
    public void setBrowserConfig(){
		
		if(browser.contains("Firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if(browser.contains("Chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\SeleniumGecko\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

    @BeforeMethod
    public void runTest(){
		//maximize the browser 
		driver.manage().window().maximize();  
		//if can't find the element with in 10 sec, throw exception  
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		driver.get(url);
		driver.getTitle();
	}

	@AfterMethod
	public void closeTest(){
		//driver.quit();		
	}




    @Test
    public void launchTest() {
    	//driver.get(url);
    	
    	 	
    	HomePage hp = new HomePage(driver);
    	Assert.assertTrue(hp.isAt());
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle());    	    	    	
    	Logger log = Logger.getLogger("HomePage was successfully loaded :"+driver.getTitle());
    	hp.goToLoginPage();
    	
    	LoginPage lp = new LoginPage(driver);
    	Assert.assertTrue(lp.isAt());
    	System.out.println("LoginPage was successfully loaded :"+driver.getTitle());
    	lp.Saisir(driver, lp.txt_username, "willdjako05@gmail.com");
    	lp.Saisir(driver, lp.txt_password, "Alexandrine1");
    	lp.Cliquer(driver, lp.btn_Connexion);

    	    	
    	    	
    }
 
    

    
    
    
    
    

	

    
    
    
    
    
}





