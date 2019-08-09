package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ivorytech.utility.Constant;
import com.ivorytech.utility.ExcelFileReader;
import com.ivorytech.utility.ExcelUtils;
import com.ivorytech.utility.WebElementAction;
import com.ivorytech.pageFactory.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.stat.inference.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class KeywordDrivenTest {
	
	static WebDriver driver;
	static String browser = "Chrome";
	static String url = "https://www.darty.com";

	//public static EventFiringWebDriver driver;
	public static ExcelFileReader SuiteData;
	public static ExcelFileReader TestStepData;

	public static String keyword;
	public static String webPage;
	public static String webElement;
	public static String TestData;
	public static String Param1;
	public static String Param2;
	public static String Description;
	public static File f ;
	public static FileInputStream FI;
	

		
	public static void main(String[] args) throws Exception{
		
		Initialize();
		setBrowserConfig();
		//runTest();
		
		//ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Feuil2");

		
		String sStepID = ExcelUtils.getCellData(1, 1);
		
		String sStepDescription = ExcelUtils.getCellData(1, 2);
		
		String sKeyword = ExcelUtils.getCellData(1, 3);
		
		String sObjectPage = ExcelUtils.getCellData(1, 4);

		String sObject = ExcelUtils.getCellData(1, 5);
		
		String sValue1 = ExcelUtils.getCellData(1, 6);
		
		String sValue2 = ExcelUtils.getCellData(1, 7);
		
		String sValue3 = ExcelUtils.getCellData(1, 8);
		
		
		
		
		
	
		
		String TCStatus="Pass"; // TestCase status
		
		int rows = ExcelFileReader.getRowCount("Feuil2");
		
		
		// loop through test data
		for(int TD=2;TD<=rows;TD++ ) {
		
			// loop through the test steps
			//System.out.println("SuiteData.getRowCount(TestCaseID)"+SuiteData.getRowCount(Feuil2));
			
			for(int TestStep=1;TestStep<=ExcelFileReader.getRowCount("Feuil2");TestStep++) {
				
				keyword = ExcelFileReader.getCellData("Feuil2", "Keyword", TestStep);
				webPage = ExcelFileReader.getCellData("Feuil2", "WebElementPage", TestStep);
				webElement = ExcelFileReader.getCellData("Feuil2", "WebElementName", TestStep);
				Param1 = ExcelFileReader.getCellData("Feuil2", "Parameter1", TestStep);
				Param2 = ExcelFileReader.getCellData("Feuil2", "Parameter2", TestStep);
				

				
				
				//Method method = WebElementAction.class.getMethod(keyword);
				
				if(keyword.trim().equalsIgnoreCase("navigate")) { //keyword.trim().toLowerCase()=="navigate"
					
					LoginPage lp = new LoginPage(driver);
					
					//lp.Saisir(driver, lp.webPage, a);
					System.out.println("Hello");
					ExcelFileReader.Navigate(Param1);
				}
				
				//TSStatus = (String) method.invoke(method);    // TestSuite status


			}
		}

				closeTest();


	}


	public static void Initialize() throws IOException {		
		TestStepData = new ExcelFileReader(Constant.Path_TestData + Constant.File_TestData);
		SuiteData = new ExcelFileReader(Constant.Path_TestData + Constant.File_TestData);
	
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
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		driver.get(url);
		driver.getTitle();
	}

	 static void closeTest(){
		driver.quit();		
	}


}
