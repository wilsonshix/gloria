package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
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
import com.ivorytech.pageFactory.ConnexionPage;
import com.ivorytech.pageFactory.HomePage;
import com.ivorytech.pageFactory.ProductPage;
import com.ivorytech.pageFactory.SearchPage;


import com.ivorytech.utility.WebElementActionStatic;

public class ConnexionPageTest {

	DriverManager driverManager;
	static WebDriver driver;
	String browser = "Chrome";
    static String url = "https://www.darty.com/espace_client/connexion?storeId=10001&espaceclient=0&org=head";
    String product = "LENOVO LEGION Y520-15IKBM 80YY003RFR";
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
		driver.manage().window().maximize();  
		//if can't find the element with in 10 sec, throw exception  
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		//driver.get(url);
		//driver.getTitle();
	}

	@AfterMethod
	public void closeTest(){
		//driver.quit();		
	}
	
	

	@Test
	public static void launchTest() {
		//driver.get(url);

		ConnexionPage p = new ConnexionPage(driver);
		
		WebElementActionStatic.Naviguer(driver, url);
		
		WebElementActionStatic.Saisir(driver, ConnexionPage.txt_username,"willdjako05@gmail.com");
		WebElementActionStatic.Saisir(driver, ConnexionPage.txt_password,"Alexandrine1");
		WebElementActionStatic.Cliquer(driver, ConnexionPage.btn_Connexion);
		
	
		


	}
}
