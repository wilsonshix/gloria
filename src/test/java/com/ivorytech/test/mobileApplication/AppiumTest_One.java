package com.ivorytech.test.mobileApplication;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ivorytech.baseTests.AppiumBaseTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ com.ivorytech.utilities.listeners.AppiumTestListener.class })
@Epic("Regression Tests")
@Feature("Accessing Tests // Renseigner l'US")
public class AppiumTest_One extends AppiumBaseTests{

	@Test(priority = 3, description = "Valid access to the HomePage")
	@Severity(SeverityLevel.NORMAL)
	@Description("Access to the HomePage")
	@Story("HomePage")
	public void main() {	
		// Open URL in Chrome Browser
		driver.get("https://www.google.com/");

	}
}
