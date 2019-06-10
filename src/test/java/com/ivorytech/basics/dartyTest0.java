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

import com.ivorytech.driverFactory.DriverManager;
import com.ivorytech.pageFactory.CartPage;
import com.ivorytech.pageFactory.CommonOnPage;
import com.ivorytech.pageFactory.HomePage;
import com.ivorytech.pageFactory.LoginPage;
import com.ivorytech.pageFactory.ProductPage;
import com.ivorytech.pageFactory.SearchPage;
import com.ivorytech.pageFactory.UserDetailsPage;


public class dartyTest0 {
	
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
		//driver.manage().window().maximize();  
		//if can't find the element with in 10 sec, throw exception  
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); 
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
    	hp.goToLoginPage();
    	
    	LoginPage lp = new LoginPage(driver); 
    	Assert.assertTrue(lp.isAt());
    	System.out.println("LoginPage was successfully loaded :"+driver.getTitle());
    	lp.setUsername("willdjako05@gmail.com");
    	lp.setPassword("Alexandrine1");
    	lp.clickOnConnexion();
    	
    	UserDetailsPage up = new UserDetailsPage(driver);
    	Assert.assertTrue(up.isAt());
    	System.out.println("UserDetailsPage was successfully loaded :"+driver.getTitle());
    	
    	//go to HomePage
    	up.clickOnLogo();
    	
    	//Assert.assertTrue(hp.isAt());
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle());
    	
    	//faire une recherche d'article
    	hp.typeSearch(search);
    	hp.clickOnSearch();
    	
    	SearchPage sp = new SearchPage(driver);
    	Assert.assertTrue(sp.isAt());
    	System.out.println("SearchPage was successfully loaded :"+driver.getTitle());
    	
    	sp.selectRandomCBoxValue();
    	sp.goToNexLink();
    	sp.clickOnRandomArticle();
    	
    	ProductPage pp = new ProductPage(driver);
    	Assert.assertTrue(pp.isAt());
    	System.out.println("ProductPage was successfully loaded :"+driver.getTitle());
    	pp.clickOnAddToCart();
    	pp.clickOnAccessToCart();   //have to fix the pop-in
    	
    	CartPage cp = new CartPage(driver);
    	Assert.assertTrue(cp.isAt());
    	System.out.println("CartPage was successfully loaded :"+driver.getTitle());
    	
    	    	
    	    	
    }
 
    

    
    
    
    
    

	

    
    
    
    
    
}





