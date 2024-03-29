package com.ivorytech.test.webBrowserApplication;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ivorytech.pageFactory.webBrowserApplication.CommonOnPage;
import com.ivorytech.pageFactory.webBrowserApplication.HomePage;
import com.ivorytech.pageFactory.webBrowserApplication.LoginPage;
import com.ivorytech.pageFactory.webBrowserApplication.ProductPage;
import com.ivorytech.pageFactory.webBrowserApplication.SearchPage;

public class cartWithManyArticles {

	static WebDriver driver;
	static String browser = "Chrome";
	static String url = "https://www.darty.com";
	static String produit = "Lenovo IDEAPAD 320S-14IKBR 81BN004LFR"; //"Lenovo IDEACENTRE 310S-08IAP 90GA000XFR";  //
	static boolean rep = false;
	static String sortingBy ="Prix d�croissant";
	static String search ="Lenovo";

	public static void main(String[] args) throws Exception{

		setBrowserConfig();	
		runTest();



		SearchPage sd = new SearchPage(driver);
		ProductPage pp = new ProductPage(driver);

		int i=0;

		do{



	    	sd.Saisir(driver, sd.txt_searchBar,search);
	    	sd.Cliquer(driver, sd.btn_search);


			//sd.isCorrectResultTitle(search);

	    	sd.displayAllArticles(driver, sd.lst_Noms_Articles, sd.txt_Nom_Article);

			sd.displayAllSortingValue(driver, sd.lst_Noms_Articles); 
			sd.selectRandomCBoxValue(driver, sd.Lst_CBoxValues);  

			sd.clickOnRandomArticle(driver, sd.lst_Noms_Articles);


			pp.Cliquer(driver, pp.btn_addToCard);		
			//pp.isCorrectResultTitle();

			i++;

		}while (i<3);


		pp.clickOnAccessToCart(driver, pp.btn_AccessToCart);



		System.out.println("toto le chef de gang");

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
