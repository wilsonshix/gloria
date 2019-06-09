package com.ivorytech.pageFactory;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ivorytech.utility.WebElementAction;

public class ProductPage extends CommonOnPage implements WebElementAction{

	WebDriver driver;

	//Constructor
	public ProductPage(WebDriver driver){
		this.driver =driver;
		PageFactory.initElements(driver, this);		
	}
	

	
	
	/*
	 * @Ent�tes
	 */




	/*
	 * @Corps de page
	 */


	//Btn_Ajouter au panier
	@FindBy(xpath="//div/form/button[@class='ajout_panier_bouton btn-reset']")
	WebElement btn_addToCard;

	//Message Produit bien ajout�
	@FindBy(xpath="//*[@id='main']/div[1]/div[1]/p")
	WebElement msg_productAdded;
	

	//Bouton acc�der au panier
	@FindBy(xpath="//*[@id='main']/div[1]/div[1]/a")
	WebElement btn_AccessToCart;
	
	//Offre de garanti => Affichage de PopIn
	@FindBy(xpath="//*[@className='service-title font-2-b']")
	WebElement txt_guarantee;
	
	

	//btn_closeAlert
	@FindBy(xpath="//*[@id='kClose']")
	WebElement btn_CloseAlert;

	//POP IN qui apparait lorsqu'on clique sur "Acc�der au panier"
	@FindBy(xpath="//*[@id='kPopin']")
	WebElement pop_up;




	/*
	 * @M�thodes
	 */
	
	//Savoir si la garantie est propos�e
	public void isGuaranteePresent(){		
		//boolean rep = Assert.assertTrue(txt_guarantee.isDisplayed());
	}
	

	
	
	

	//V�rifier la pr�sence du bouton Ajouter ***Autrement il n'est pas disponible
	public boolean addToCardIsDisplayed(){
		boolean rep = true;
		if(btn_addToCard.isDisplayed()){
			rep = true;
		}else{
			rep = false;
		}
		return rep;		
	}
	
	public boolean isBtnAddToCardPresent(){
	    try{
	        driver.findElement(By.xpath("//div/form/button[@class='ajout_panier_bouton btn-reset']"));
	        return true;
	    }
	    catch(NoSuchElementException e){
	        return false;
	    }
	}
	
	
	
	/*public boolean isElementPresent(String xpathOfElement){
	    try{
	        driver.findElement(By.xpath(xpathOfElement));
	        return true;
	    }
	    catch(NoSuchElementException e){
	        return false;
	    }
	}*/
	
	
	

	//Ajouter au panier
	public void clickOnAddToCart(){
		performingAction(ClickOnWebElement(driver, btn_addToCard));

		//Exp�rimentation de action tracing
		System.out.println("Click btn Ajout au panier - OK");
	}


	//V�rifier que le produit a �t� bien ajout� au panier
	public void isCorrectResultTitle(){		
		String title= "Votre produit a bien �t� ajout� au panier";	
		Assert.assertEquals(msg_productAdded.getText().toString().toLowerCase(), title.toLowerCase());
	}

	//Acc�der au panier
	public void clickOnAccessToCart(){
		performingAction(ClickOnWebElement(driver, btn_AccessToCart));
		
		//Exp�rimentation de action tracing
		System.out.println("Click btn Acc�der au panier - OK");
		
		}

	//Acc�der au panier avec gestion des alertes
	public void clickOnAccessToCart2(){

		try {
			// Get the number of windows open before clicking
			int numberOfWindowsBefore = driver.getWindowHandles().size();
			String title = driver.getTitle();
			btn_AccessToCart.click();
			// Check how many windows are open after clicking 
			int numberOfWindowsAfter = driver.getWindowHandles().size();

			// Now compare the number of windows before and after clicking			
			if (numberOfWindowsBefore < numberOfWindowsAfter) {
				// If there is a new window available, switch to it.
				//driver.switchTo().window(title);
				driver.findElement(By.xpath("//*[@id='kClose']")).click();

				System.out.println("le w� est visible tch� !");
			}

		} catch (TimeoutException ex) {
			System.out.println("le w� n'est pas visible tch� !");
		}

	}


	//Fermer la pop up qui s'affiche apr�s avoir cliqu� sur btn clickOnAccessToCart
	//lorsque le pop up n'est pas pr�sent = erreur
	public void clickOnCloseAlert(){
		btn_CloseAlert.click();
	}






	// V�rifier que l'�l�ment est cliquable => page is loaded !
			public boolean isAt() {    
		    	
		        boolean isClickable = WaitForWebElement(driver, btn_addToCard);
		        int i = 1;
		        while (isClickable == false && i < 4) {
		        	isClickable = WaitForWebElement(driver, btn_addToCard);
		            System.out.println("page is loading ...");
		            i++;
		            
		            if(isClickable == false && i==4) {
		            	System.out.println("ProductPage was not successfully loaded");
			        }
		        }        
		        
		        return isClickable;
			}


	
	/*
	 * 
Following are ways to check if an Web-Element isPresent or not :
I have used XPath as an Element Identifier/Locator, but you can use other locators as well.

Solution I :

public boolean isElementPresent(String xpathOfElement){
    try{
        driver.findElement(By.xpath(xpathOfElement));
        return true;
    }
    catch(NoSuchElementException e){
        return false;
    }
}

Solution II :

public boolean isElementPresent(String xpathOfElement){
    boolean isPresent = false;
    if(!driver.findElements(By.xpath(xpathOfElement)).isEmpty()){
        isPresent=true;
    }
    return isPresent;
}
Solution III :

public boolean isElementPresent(String xpathOfElement){
    return driver.findElements(By.xpath(xpathOfElement)).size() > 0;
}
	
*/



}



