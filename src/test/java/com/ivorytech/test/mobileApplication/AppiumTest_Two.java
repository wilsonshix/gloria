package com.ivorytech.test.mobileApplication;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ivorytech.baseTests.AppiumBaseTests;


@Listeners({ com.ivorytech.utilities.listeners.AppiumTestListener.class })
@Epic("Regression Tests")
@Feature("Accessing Tests // Renseigner l'US")
public class AppiumTest_Two extends AppiumBaseTests{
	
		
    @Test (priority = 3, description="Valid access to the HomePage")
    @Severity(SeverityLevel.NORMAL)
    @Description("Access to the HomePage")
    @Story("HomePage") 
    public void goToHomePage() {    					
    
		Assert.assertTrue(homePage.isAt());    	
    	System.out.println("HomePage was successfully loaded :"+driver.getTitle()); 
    	//((AndroidDriver<MobileElement>)driver).findElementByAndroidUIAutomator("new UiSelector().text(\"Mon compte\")").click();
    	
    	//driver.findElement(By.xpath("//*[@id = 'btn-search']")).sendKeys(search);
    	
   
	}
}
