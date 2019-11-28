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


import com.ivorytech.driverFactory.*;



@SuppressWarnings("unused")
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
			System.setProperty("webdriver.chrome.driver","C:\\SeleniumGecko\\chromedriver78.exe");
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
    	driver.get(url);
    	
    	 	
 /*   	HomePage hp = new HomePage(driver);
    	Assert.assertTrue(hp.isAt());
    	//Assert.assertTrue(hp.isAt(driver,hp.lnk_Seconnecter,"HomePage"));
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle());    	    	    	
    	hp.goToLoginPage();
    	
    	LoginPage lp = new LoginPage(driver); 
    	Assert.assertTrue(lp.isAt());
    	System.out.println("LoginPage was successfully loaded :"+driver.getTitle());
    	lp.Saisir(driver, lp.txt_username, "willdjako05@gmail.com");
    	lp.Saisir(driver, lp.txt_password, "Alexandrine1");
    	lp.Cliquer(driver, lp.btn_Connexion);
    	
    	UserDetailsPage up = new UserDetailsPage(driver);
    	Assert.assertTrue(up.isAt());
    	System.out.println("UserDetailsPage was successfully loaded :"+driver.getTitle());
    	
    	//go to HomePage
    	up.Naviguer(driver,"https:www.darty.com"); //
    	
    	//Assert.assertTrue(hp.isAt());
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle());
    	
    	//faire une recherche d'article
    	hp.Saisir(driver, hp.txt_searchBar,search);
    	hp.Cliquer(driver, hp.btn_search);
    	
    	SearchPage sp = new SearchPage(driver);
    	Assert.assertTrue(sp.isAt());
    	System.out.println("SearchPage was successfully loaded :"+driver.getTitle());
    	
    	sp.selectRandomCBoxValue(driver, sp.Lst_CBoxValues );
    	sp.Cliquer(driver, sp.lnk_PageSuivante);
    	sp.clickOnRandomArticle(driver, sp.lst_Noms_Articles);
    	
    	ProductPage pp = new ProductPage(driver);
    	Assert.assertTrue(pp.isAt());
    	System.out.println("ProductPage was successfully loaded :"+driver.getTitle());
    	pp.Cliquer(driver, pp.btn_addToCard);
    	pp.clickOnAccessToCart(driver, pp.btn_AccessToCart);   //have to fix the pop-in
    	
    	CartPage cp = new CartPage(driver);
    	Assert.assertTrue(cp.isAt());
    	System.out.println("CartPage was successfully loaded :"+driver.getTitle());
    	
    	    */	
    	    	
    }
 
    

    
    
    
    
    

	

    
    
    
    
    
}





