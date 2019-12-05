package com.ivorytech.basics;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ivorytech.pageFactory.HomePage;
import com.ivorytech.pageFactory.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThirdAppiumTest {
	
	String browser = "Chrome";
    String url = "https://www.darty.com";    
    String appium_node = ("http://0.0.0.0:4723/wd/hub");	
	AndroidDriver driver;
	DesiredCapabilities caps;
	String search = "lenovo";
	
	@BeforeTest
    public void setBrowserConfig(){		

	    //Set the Desired Capabilities
	    caps = new DesiredCapabilities();
	    caps.setCapability("deviceName", "My Phone");
	    caps.setCapability("udid", "8f81d4e2"); //Give Device ID of your mobile phone
	    caps.setCapability("platformName", "Android");
	    caps.setCapability("platformVersion", "9");
	    caps.setCapability("browserName", "Chrome");
	    caps.setCapability("noReset", true);
	    caps.setCapability("unicodekeyboard", true);
	    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);	
	    caps.setCapability("chromedriverExecutable","C:\\SeleniumGecko\\chromedriver78.exe");  //Set ChromeDriver location
	}
	
	
	
    @BeforeMethod
    public void runTest() throws MalformedURLException, InterruptedException{    	
		
	    try {
	    	 driver = new AndroidDriver < > (new URL(appium_node), caps);
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    }
    	
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); 
		driver.get(url);
		driver.getTitle();
	}
    
	
	@AfterMethod
	public void closeTest(){
		//driver.quit();		
	}	
	
	
	
	@Test
    public void main() {
    					
    
    	HomePage hp = new HomePage(driver);
    	//Assert.assertTrue(hp.isAt());
    	//Assert.assertTrue(hp.isAt(driver,hp.lnk_Seconnecter,"HomePage"));
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle()); 
    	//((AndroidDriver<MobileElement>)driver).findElementByAndroidUIAutomator("new UiSelector().text(\"Mon compte\")").click();
    	
    	driver.findElement(By.xpath("//*[@id = 'btn-search']")).sendKeys(search);
    	
   
	}
	
	}

