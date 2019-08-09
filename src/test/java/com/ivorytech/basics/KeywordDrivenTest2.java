package com.ivorytech.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ivorytech.utility.Constant;
import com.ivorytech.utility.ExcelUtils;
import com.ivorytech.pageFactory.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class KeywordDrivenTest2 {
	
	static WebDriver driver;
	static String browser = "Chrome";
	static String url = "https://www.darty.com";


	public static void main(String[] args) throws Exception{
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Feuil2");

		
		String sStepID = ExcelUtils.getCellData(1, 1);
		
		String sStepDescription = ExcelUtils.getCellData(1, 2);
		
		String sKeyword = ExcelUtils.getCellData(1, 3);
		
		String sObjectPage = ExcelUtils.getCellData(1, 4);

		String sObject = ExcelUtils.getCellData(1, 5);
		
		String sValue1 = ExcelUtils.getCellData(1, 6);
		
		String sValue2 = ExcelUtils.getCellData(1, 7);
		
		String sValue3 = ExcelUtils.getCellData(1, 8);
		
		//System.out.println(sUsername);
		//System.out.println(sPassword);
		ExcelUtils.reader(Constant.Path_TestData + Constant.File_TestData,1); //0
		
		LoginPage lp = new LoginPage(driver);
		
		
		
		
		
		//setBrowserConfig();	
		//runTest();
		
		
		/*		HomePage hp = new HomePage(driver);
    	Assert.assertTrue(hp.isAt());
    	//Assert.assertTrue(hp.isAt(driver,hp.lnk_Seconnecter,"HomePage"));
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle());    	    	    	
    	hp.goToLoginPage();
    	
    	LoginPage lp = new LoginPage(driver); 
    	Assert.assertTrue(lp.isAt());
    	System.out.println("LoginPage was successfully loaded :"+driver.getTitle());
    	
    	
    	
    	
        //lp.Saisir(driver, lp.txt_username, sValue1);
    	
    	lp.Saisir(driver, lp.txt_username, "willdjako05@gmail.com");
    	lp.Saisir(driver, lp.txt_password, "Alexandrine1");
    	lp.Cliquer(driver, lp.btn_Connexion);
    	lp.verifierConnexion(driver, lp.lnk_connexion, lp.txt_connecte, lp.lnk_deconnexion);

    	UserDetailsPage up = new UserDetailsPage(driver);
    	up.ChargerPage(driver, up.lnk_Modifier_Userdetails,"Compte client");
    	
		System.out.println("");     */

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
		//driver.manage().window().maximize();  
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
