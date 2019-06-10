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



	//	Attendre le chargement complet d'un élément sur une page et Vérifier que cet élément soit localisé et cliquable
	public default boolean WaitForWebElement(WebDriver driver, WebElement e) {
		WebElement myDynamicElement = null;
		try {
			myDynamicElement = (new WebDriverWait(driver, 10))                    
					.until(ExpectedConditions.elementToBeClickable(e)); // located and clickable (visible and enabled).
			return true;
		} catch (TimeoutException ex) {        	
			return false;
		} 
	}
	
	
	//	Attendre le chargement complet d'une page et verifier qu'un élément soit présent
/*	public default boolean isWebElementPresent(WebDriver driver, WebElement e) {
		WebElement myDynamicElement = null;
		try {
			myDynamicElement = (new WebDriverWait(driver, 10))                    
					.until(ExpectedConditions.visibilityOfElementLocated((e)); // located and clickable (visible and enabled).
			return true;
		} catch (TimeoutException ex) {        	
			return false;
		} 
	}
	*/
	
	
//Attendre le chargement complet d'une page
    public default void  WaitForWebPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);        
    }

    
    
	// Vérifier qu'une page soit complètement chargée
	public default boolean isPageLoaded(WebDriver driver, WebElement element, String page) {    
		WaitForWebPageLoaded(driver);
		boolean isClickable = WaitForWebElement(driver, element);
		int i = 0;
		while (isClickable == false && i < 3) {
			isClickable = WaitForWebElement(driver, element);
			System.out.println(page +" is loading ...");
			i++;

			if(isClickable == false && i==3) {
				System.out.println(page +" was not successfully loaded");
			}
		}        
		//System.out.println(isClickable);  
		return isClickable;
	}
    
    
    

	//Permet de repeter une action sur un WebElement
	public default void performingAction(boolean b) {    

		boolean bool = false;
		int i = 0;
		while (bool == false && i < 2) { //repeter 2 fois
			bool = b;
			i++;
		}        

	}




	// Vérifier qu'un Element est (cliquable) présent => pour les getElement        // tous les objets cliquales
	public default boolean isElementPresent(WebDriver driver, WebElement e) {    

		boolean isClickable = WaitForWebElement(driver, e);
		int i = 1;
		while (isClickable == false && i < 4) {
			isClickable = WaitForWebElement(driver, e);
			System.out.println("Tentative n°"+i+" de recherche de l'objet "+e.getText());
			i++;

			if(isClickable == false && i==4) {
				System.out.println("L'objet n'a pas été retrouvé : "+e.getText());
			}
		}        

		return isClickable; 
	}


	
	// Vérifier qu'un Element est présent (objet non cliquable)       // tous les objets types : texte, image,... sans liens
	
	/*public default boolean ElementIsPresent(WebDriver driver, WebElement e) {    

		boolean isClickable = WaitForWebElement(driver, e);
		int i = 1;
		while (isClickable == false && i < 4) {
			isClickable = WaitForWebElement(driver, e);
			System.out.println("Tentative n°"+i+" de recherche de l'objet "+e.getText());
			i++;

			if(isClickable == false && i==4) {
				System.out.println("L'objet n'a pas été retrouvé : "+e.getText());
			}
		}        

		return isClickable; 
	}*/
	
	
	
	




	//Permet de cliquer-sélectionner un WebElement
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



	//Permet de renseigner une valeur dans un WebElement : TextBox, ...
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



//Permet de sélectionner une valeur précise dans un ménu déroulant : ComboBox
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



//Permet de sélectionner une valeur aléatoire dans un ménu déroulant
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




// Vérifier qu'un élément à été trouvé lors d'une recherche parmis plusieurs autres éléments
	public default boolean ArticleFound(WebDriver driver, WebElement e, String p) {

		if(!(e.getText().isEmpty())){	
			System.out.println("i:"+e.getText());
			if(e.getText().equalsIgnoreCase(p)){
				System.out.print("l'heureux �lu est :"+e.getText());
				System.out.println(" VS   produit recherch� : "+p);				
				performingAction(ClickOnWebElement(driver, e));	
				return true;				
			}
		}
		return false;
	}


}

