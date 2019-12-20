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

import com.ivorytech.utilities.WebElementAction;

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
	@FindBy(className="btn-buy btn-add-basket")
	public WebElement btn_addToCard;

	//Message Produit bien ajout�
	@FindBy(xpath="//*[@id='main']/div[1]/div[1]/p")
	public WebElement msg_productAdded;
	

	//Bouton acc�der au panier
	@FindBy(xpath="//*[@id='main']/div[1]/div[1]/a")
	public WebElement btn_AccessToCart;
	
	//Offre de garanti => Affichage de PopIn
	@FindBy(xpath="//*[@className='service-title font-2-b']")
	public WebElement txt_guarantee;
	
	

	//btn_closeAlert
	@FindBy(xpath="//*[@id='kClose']")
	public WebElement btn_CloseAlert;

	//POP IN qui apparait lorsqu'on clique sur "Accéder au panier"
	@FindBy(xpath="//*[@id='kPopin']")
	public WebElement pop_up;



	/*
	 * @Méthodes
	 */
	
	
	// Vérifier que l'élément est cliquable => page is loaded !
	public boolean isAt() {    		 
		return isPageLoaded(driver, btn_addToCard, "ProductPage");
	}



}



