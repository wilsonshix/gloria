package com.ivorytech.basics;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ivorytech.baseTests.SeleniumBaseTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Listeners({ com.ivorytech.utilities.listeners.TestListener.class })
@Epic("Regression Tests")
@Feature("Login Tests")
public class AllureReportingTest extends SeleniumBaseTests{
	
	//Test Data
    String wrongUsername = "willdjako05@gmail.com";
    String wrongPassword = "Alexandrine1";

    @Test (priority = 0, description="Valid Login Scenario with good username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with good username and wrong password.")
    @Story("Valid username and password login test")
    public void invalidLoginTest_InvalidUserNameInvalidPassword () {
        homePage
            .goToDarty();

    }
	
	
	

}
