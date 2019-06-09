package com.ivorytech.pageFactory;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ivorytech.utility.WebElementAction;

public class CartPage extends CommonOnPage implements WebElementAction{
	
	WebDriver driver;

	//Constructor
	public CartPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	

	
	
	/*
	 * @Entêtes
	 */
	
	
	/*
	 * @Corps
	 */
	
	//Message Mon panier affiché
	@FindBy(xpath="//*[@id='basket_title']")
	WebElement msg_MyBasket;

    //Bouton Commander
	@FindBy(xpath="//*[@id='basket_content_inner_page']")
	
	//@FindBy(xpath="//form/input[@class='submit_button']")
	//*[@id="basket_content_inner_page"]/div[3]/div[2]/form/button
	//<button type="submit" class="btn btn-secondary btn-large btn-basket-submit">Commander</button>
	WebElement btn_Command;

	// Vérifier que l'élément est cliquable => page is loaded !
		public boolean isAt() {    
	    	
	        boolean isClickable = WaitForWebElement(driver, btn_Command);
	        int i = 1;
	        while (isClickable == false && i < 4) {
	        	isClickable = WaitForWebElement(driver, btn_Command);
	            System.out.println("page is loading ...");
	            i++;
	            
	            if(isClickable == false && i==4) {
	            	System.out.println("CartPage was not successfully loaded");
		        }
	        }        
	        return isClickable;
		}


	
	
	//<table id="HD_table" class="products_table" label="HD" style="display:;">

	//tr 	class = "product_line dispo pse" 
	

}

