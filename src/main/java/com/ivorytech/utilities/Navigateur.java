package com.ivorytech.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Navigateur {

	public static class Navi{
		//static String browser;
		//static WebDriver driver;

		/*public String setBrowser(String nav){
			browser = nav;
			return browser;
		}*/

		public static void setBrowserConfig(WebDriver driver, String browser ){

			if(browser.contains("Firefox")){

				System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
				driver = new FirefoxDriver();
			}

			if(browser.contains("Chrome")){

				System.setProperty("webdriver.chrome.driver","C:\\SeleniumGecko\\chromedriver.exe");
				driver = new ChromeDriver();

			}

		}


		public static void runTest(WebDriver driver,String url){

			//maximize the browser 
			driver.manage().window().maximize();  
			//if can't find the element with in 10 sec, throw exception  
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			//open the url
			driver.get(url);
			driver.getTitle();
			//driver.quit();
		}

		public static void closeTest(WebDriver driver){
			driver.quit();		
		}



	}
}
