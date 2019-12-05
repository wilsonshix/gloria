package TestNGAllureReport;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class BaseTest {
    //public WebDriver driver;
    public HomePage homePage;
    
    public AppiumDriver<MobileElement> driver;
    
  
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {
        //driver = new ChromeDriver();
    	
    	//Set the Desired Capabilities
  		DesiredCapabilities caps = new DesiredCapabilities();
  		caps.setCapability("deviceName", "My Phone");
  		caps.setCapability("udid", "8f81d4e2"); //Give Device ID of your mobile phone
  		caps.setCapability("platformName", "Android");
  		caps.setCapability("platformVersion", "9"); // The version of Android on your device
  		caps.setCapability("browserName", "Chrome");
  		caps.setCapability("noReset", true);
  		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);

    	try {
			driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
