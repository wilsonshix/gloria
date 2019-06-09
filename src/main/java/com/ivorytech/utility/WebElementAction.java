package com.ivorytech.utility;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface WebElementAction {



	//	Attendre le chargement complet d'une page et verifier qu'un élément soit cliquable
	public default boolean WaitForWebElement(WebDriver driver, WebElement e) {
		WebElement myDynamicElement = null;
		try {
			myDynamicElement = (new WebDriverWait(driver, 20))                    
					.until(ExpectedConditions.elementToBeClickable(e)); // located and clickable (visible and enabled).
			return true;
		} catch (TimeoutException ex) {        	
			return false;
		} 
	}




	//Permet de repeter une action sur un WebElement
	public default void performingAction(boolean b) {    

		boolean bool = false;
		int i = 0;
		while (bool == false && i < 2) { //repète 2 fois
			bool = b;
			i++;
		}        

	}




	// Vérifier que l'élément est cliquable / présent => pour les getElement
	public default boolean ElementIsPresent(WebDriver driver, WebElement e) {    

		boolean isClickable = WaitForWebElement(driver, e);
		int i = 1;
		while (isClickable == false && i < 4) {
			isClickable = WaitForWebElement(driver, e);
			System.out.println("Element is loading ... : "+e.getText());
			i++;

			if(isClickable == false && i==4) {
				System.out.println("Element was not successfully found : "+e.getText());
			}
		}        

		return isClickable; 
	}






	//Cliquer ou Sélectionner un WebElement
	public default boolean ClickOnWebElement(WebDriver driver, WebElement e) {
		try {
			e.click();
			System.out.println("On click");	//A commenter
			return true;

		} catch (Exception ex){
			if(ex.getMessage().contains("no such element")){

				Actions actions = new Actions(driver);
				actions.moveToElement(e).click().build().perform();	
				System.out.println("On a relancé la machine à click :) NoSuchElementException");	//A commenter
				return true;

			} else if(ex.getMessage().contains("stale element reference")){	//appears also when element is already click before

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(e));
				e.click();
				System.out.println("On a relancé la machine à click :) StaleElementReferenceException");	//A commenter
				return true;

			} else if(ex.getMessage().contains("is not clickable at point")){
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", e);
				System.out.println("On a relancé la machine à click :) NotClickableException");	//A commenter
				return true;

			} else if(!(ex.getMessage()==null)){

				System.out.println(ex.getMessage());
				return false;
			}
		}		

		return false;
	}



	//Renseigner un WebElement
	public default boolean SendKeysOnWebElement(WebDriver driver, WebElement e, String v) {
		try {
			e.clear();
			e.sendKeys(v);
			System.out.println("On click");	//A commenter
			return true;

		} catch (Exception ex){
			if(ex.getMessage().contains("no such element")){

				Actions actions = new Actions(driver);
				actions.moveToElement(e).sendKeys(v).build().perform();	
				System.out.println(" NoSuchElementException");	//A commenter
				return true;

			} else if(ex.getMessage().contains("stale element reference")){	//appears also when element is already click before

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(e));
				e.sendKeys(v);
				System.out.println(" StaleElementReferenceException");	//A commenter
				return true;

			} else if(!(ex.getMessage()==null)){  // Add lines to avoid  NotClickableException

				System.out.println(ex.getMessage());
				return false;
			}
		}		

		return false;
	}




	public default boolean selectValueOnCBox(WebDriver driver, WebElement e, String v){  

		try{

			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver driver)  
				{
					Select select = new Select(e);
					return select.getOptions().size()>1;
				}
			});

			Select select = new Select(e);
			select.selectByVisibleText(v);

			return true;

		}catch(Throwable ex){
			System.out.println("Error found: "+ex.getMessage());
		}
		return false;
	}




	public default boolean selectRandomValueOnCBox(WebDriver driver, List<WebElement> list){  

		try{

			List<WebElement> items = list; 					
			Random rand = new Random();
			int number = rand.nextInt(items.size());  
			System.out.println("**********************************");
			System.out.println("random CBoxIndex :"+number);	
			System.out.println("random CBoxValue :"+items.get(number).getText());	
			items.get(number).click();
			return true;

		}catch(Throwable ex){
			System.out.println("Error found: "+ex.getMessage());
		}
		return false;
	}






	public default boolean ArticleFound(WebDriver driver, WebElement e, String p) {

		if(!(e.getText().isEmpty())){	
			System.out.println("i:"+e.getText());
			if(e.getText().equalsIgnoreCase(p)){
				System.out.print("l'heureux élu est :"+e.getText());
				System.out.println(" VS   produit recherché : "+p);				
				performingAction(ClickOnWebElement(driver, e));	
				return true;				
			}
		}
		return false;
	}


}

