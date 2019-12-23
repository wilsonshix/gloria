package com.ivorytech.baseTests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.ivorytech.pageFactory.webBrowserApplication.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


public class AppiumBaseTests {
	
	public AppiumDriver driver; //or AndroidDriver
	DesiredCapabilities caps;
    public GooglePage googlePage;
    public HomePage homePage;
    
    String appium_node = "http://0.0.0.0:4723/wd/hub";
	

    public AppiumDriver getDriver() { // or AndroidDriver
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {      
		
    	
    	// appium
    	//Set the Desired Capabilities
		 		caps = new DesiredCapabilities();
    			caps.setCapability("deviceName", "My Phone");
    			caps.setCapability("udid", "8f81d4e2"); //Give Device ID of your mobile phone
    			caps.setCapability("platformName", "Android");
    			caps.setCapability("platformVersion", "9"); // The version of Android on your device
    			caps.setCapability("browserName", "Chrome");
    			caps.setCapability("noReset", true);
    			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    			caps.setCapability("chromedriverExecutable", "C:\\SeleniumGecko\\chromedriver78.exe"); // Set ChromeDriver location
    			

    			//Instantiate Appium Driver or AndroidDriver
    			try {
    				driver = new AppiumDriver<>(new URL(appium_node), caps);
    				
    			} catch (MalformedURLException e) {
    				System.out.println(e.getMessage());
    			}
    					

		
    }

    @BeforeMethod
    public void methodLevelSetup() {
        googlePage = new GooglePage(driver);
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void teardown() {
       // driver.quit();
    }

}
