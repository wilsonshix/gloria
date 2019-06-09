package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ivorytech.utility.Constant;
import com.ivorytech.utility.ExcelUtils;
import com.ivorytech.pageFactory.*;


public class dartyTestExcel {
	
	static WebDriver driver;
	static String browser = "Chrome";
	static String url = "https://www.darty.com";


	public static void main(String[] args) throws Exception{
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Objects");

		String sUsername = ExcelUtils.getCellData(1, 1);

		String sPassword = ExcelUtils.getCellData(1, 2);
		
		
		
		
		setBrowserConfig();	
		runTest();
		
		HomePageExcel hp = new HomePageExcel(driver);		
		hp.seConnecter();

		

		closeTest();

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
		//open the url
		driver.get(url);
		driver.getTitle();
		//driver.quit();
	}

	 static void closeTest(){
		driver.quit();		
	}


}
