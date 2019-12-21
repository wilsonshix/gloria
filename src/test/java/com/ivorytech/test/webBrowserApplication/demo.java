package com.ivorytech.test.webBrowserApplication;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//--
	import org.openqa.selenium.By;
	//--
	import org.openqa.selenium.WebDriver;
	//--
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//--
	import org.openqa.selenium.firefox.FirefoxDriver;
	//--
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

	public class demo {

		WebDriver driver;
	    String browser = "Chrome";

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
		}

		@AfterMethod
		public void closeTest(){
			//driver.quit();		
		}

		@Test
	    public void verifySearchButton() {

	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        driver.get("http://www.google.com");
	        driver.getTitle();

	        String search_text = "Recherche Google";
	        WebElement search_button = driver.findElement(By.name("btnK"));

	        String text = search_button.getAttribute("value");

	        Assert.assertEquals(text, search_text, "Text not found!");
	    }

}


