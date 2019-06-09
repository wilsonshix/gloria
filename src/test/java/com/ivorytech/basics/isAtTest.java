package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ivorytech.pageFactory.HomePage;

public class isAtTest {
	
	WebDriver driver;
	HomePage homePage;
	
	@Test
	public void launch() {
	   // homePage = HomePage.init(driver);	    					
	                                    
	    //validate if the page is loaded
	    Assert.assertTrue(homePage.isAt()); 
	    
	    //Assert.assertTrue(homePage.isAt(10, TimeUnit.SECONDS)); 
	    
	}

}
