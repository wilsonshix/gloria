package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ivorytech.pageFactory.SearchPage;
import com.ivorytech.pageFactory.CommonOnPage;
import com.ivorytech.pageFactory.ProductPage;

public class articleIsNotAvailable {	// A peaufiner encore

	static WebDriver driver;
	static String browser = "Chrome";
	//static String browser = "Firefox";
	static String url = "https://www.darty.com";
	static String search ="Lenovo"; //'
	static String produit ="Lenovo IDEACENTRE 310S-08IAP 90GA000XFR";
	static String sortingBy ="Prix croissant";
	//static boolean btn_addToCardIsDisplayed = true;

	public static void main(String[] args) {

		setBrowserConfig();	
		runTest();

		SearchPage sd = new SearchPage(driver);
		ProductPage pp = new ProductPage(driver);

		int numberOfWindowsBefore = driver.getWindowHandles().size();

		boolean btn_addToCardIsDisplayed = true;
		do{
			//Lancer la recherche
	    	sd.Saisir(driver, sd.txt_searchBar,search);
	    	sd.Cliquer(driver, sd.btn_search);

			sd.isCorrectResultTitle(search);			
			sd.displayAllArticles();

			sd.displayAllSortingValue(); 
			//sd.selectRandomCBoxValue();  
			//sd.selectCBoxValue(sortingBy); ok

			sd.clickOnRandomArticle();

			if(pp.isBtnAddToCardPresent()==false){
				btn_addToCardIsDisplayed = false;
				System.out.println("non dispo");
			}




		} while(btn_addToCardIsDisplayed==false);

		try{
			pp.clickOnAddToCart();
		}
		catch(org.openqa.selenium.StaleElementReferenceException e){
			pp.clickOnAddToCart();	
		}

		pp.isCorrectResultTitle();

		pp.clickOnAccessToCart();

		try{
			//*[@id='darty_push_accessoire']//iframe
			//if(driver.findElements(By.cssSelector("#darty_push_accessoire>iframe")).size() > 0){
				driver.switchTo().frame(1);
				driver.findElement(By.cssSelector("#kClose")).click();			

				System.out.println("le w� est visible tch� !");
			//}

		}catch(NoSuchElementException ex){
			System.out.println("le w� n'est pas visible tch� !");
		}


		/*	try {
			// Get the number of windows open before clicking
			//int numberOfWindowsBefore = driver.getWindowHandles().size();
			String title = driver.getTitle();

			pp.clickOnAccessToCart();

			// Check how many windows are open after clicking 
			int numberOfWindowsAfter = driver.getWindowHandles().size();

			// Now compare the number of windows before and after clicking			
			if (numberOfWindowsBefore < numberOfWindowsAfter) {
				// If there is a new window available, switch to it.
				//driver.switchTo().window(title);


				driver.switchTo().frame(driver.findElement(By.id("//*[@id='darty_push_accessoire']//iframe")));
				driver.findElement(By.xpath("//*[@id='kClose']")).click();




				System.out.println("le w� est visible tch� !");
			}

		} catch (TimeoutException ex) {
			System.out.println("le w� n'est pas visible tch� !");
		}

		 */



		System.out.println("toto le chef de gang");



		//sd.clickOnSpecificArticle(search); a refaire
		//driver.findElement(By.linkText("Lenovo IDEACENTRE 310S-08IAP 90GA000XFR")).click();
		//System.out.println(driver.findElement(By.linkText("Lenovo IDEACENTRE 310S-08IAP 90GA000XFR")).getText());




		//pp.clickOnCloseAlert();  //lorsque le pop up n'est pas pr�sent = erreur
		//pp.isBtnclickOnCloseAlertVisible();


		//System.out.println(sd.txt_searchTitle.getText()); public
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
