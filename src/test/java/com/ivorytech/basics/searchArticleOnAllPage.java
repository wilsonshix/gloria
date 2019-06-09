package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ivorytech.pageFactory.*;


public class searchArticleOnAllPage {

	static WebDriver driver;
	static String browser = "Chrome";
	static String url = "https://www.darty.com";
	static String produit = "Lenovo IDEAPAD 320S-14IKBR 81BN004LFR"; //"Lenovo IDEACENTRE 310S-08IAP 90GA000XFR";  //
	static boolean rep = false;
	static String sortingBy ="Prix d�croissant";

	
	public static void main(String[] args) throws Exception{


		setBrowserConfig();	
		runTest();

		HomePage hp = new HomePage(driver);				
		LoginPage lp = new LoginPage(driver);
		
		//lnk.typeSearch("lenovo");
		//lnk.clickOnSearch();

		SearchPage sp = new SearchPage(driver);

		//sp.displayAllSortingValue(); 
		//sp.selectRandomCBoxValue();
		//sp.selectCBoxValue(sortingBy);


		while(rep == false){
			boolean x = true;//hp.clickOnSpecificArticle(produit);
			System.out.println(x);
			if(x == true){
				System.out.println(x);
				break;
			}
			if(x==false){
				sp.goToNexLink();
			}
			rep=x;
		}
		



		//closeTest();

	}



	static void setBrowserConfig(){

		if(browser.contains("Firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		if(browser.contains("Chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\SeleniumGecko\\chromedriver.exe");
			driver = new ChromeDriver();
		}

	}

	static void runTest(){
		//maximize the browser 
		driver.manage().window().maximize();  
		//if can't find the element with in 10 sec, throw exception  
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		driver.get(url);
		driver.getTitle();
	}

	static void closeTest(){
		driver.quit();		
	}






}

