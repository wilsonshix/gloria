package com.ivorytech.pageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ivorytech.utility.WebElementAction;


//Liaisons entre les pages : Entêtes, Menus, Pieds de page

public class CommonOnPage implements WebElementAction{

	WebDriver driver;


	// Objects ............................................
	/*
	 * @Ent�tes
	 */

	
	

// MENU HORIZONTAL
	
 
 //SOLDES
	@FindBy(xpath = "//*[@id=\"main-tab-id-1\"]/a")	
	@CacheLookup
	public WebElement Menu_soldes;
	
 //BONS PLANS
	@FindBy(xpath = "//*[@id=\"main-tab-id-2\"]/a")	
	@CacheLookup
	public WebElement Menu_bons_plans;
	
 //CUISINES SUR MESURES
	@FindBy(xpath = "//*[@id=\"main-tab-id-3\"]/a")	
	@CacheLookup
	public WebElement Menu_cuisines_sur_mesures;
	
	

	
	
 //PAGE D'ACCUEIL
	@FindBy(xpath = "//*[@id='logo']")	
	@CacheLookup
	public WebElement img_LogoPageAccueil;

	//Barre de recherche
	@FindBy(xpath ="//*[@id='dartyCom_searchfield_xxl']")
	@CacheLookup
	public WebElement txt_searchBar;

	//bouton de recherche
	@FindBy(xpath ="//*[@id='dartyCom_searchform_submit']")
	@CacheLookup
	public WebElement btn_search;

 //PAGE DE CONNEXION
	@FindBy(xpath="//a[@id='h_xxl_login_lien']")
	@CacheLookup
	public WebElement lnk_PageConnexion;
	


	
	/*
	 * //Etat_connect�
	@FindBy(xpath="//a/span[@class='label']")
	WebElement txt_connect�;
	 */

	//<span class="label">CONNECT�(E)</span>

	//*[@id="h_xxl_login_lien"]/span




	// MENU CLIENT
	
	//CONNEXION
	@FindBy(xpath="//a[contains(text(), 'Se connecter']")
	public WebElement lnk_connexion;
	
	//ETRE CONNECTE(E)
	@FindBy(xpath="//span[contains(text(), 'CONNECTE(E)']")
	public WebElement txt_connecte;
	
	//DECONNEXION
	@FindBy(xpath="//a[@class='signout']")
	//*[@id="h_xxl_login_loggedin_popin"]/a[4]
	//*[@id="h_xxl_login_loggedin_popin"]/a[4]
	public WebElement lnk_deconnexion;
	
	//Mes_Listes
	@FindBy(xpath="//*[@id='h_xxl_wishlist_lien']")
	@CacheLookup
	public WebElement lnk_mylist; 

	//Mon_Panier
	@FindBy(xpath="//*[@id='h_xxl_panier_lien']")
	@CacheLookup
	public WebElement lnk_panier;

	
	
	
	

/*  Refactor OK
 * 
	//Aller sur la page d'accueil
	public void clickOnLogo(){
		System.out.println("Click --- Image -- logo");
		performingAction(ClickOnWebElement(driver, img_LogoPageAccueil));

	}



	//Permet de saisir la recherche
	public void typeSearch(String v){
		System.out.println("Fill --- textBox -- txt_searchBar");
		performingAction(SendKeysOnWebElement(driver, txt_searchBar, v));
	}

	//Permet de lancer une recherche
	public void clickOnSearch(){
		System.out.println("Click --- button -- btn_search");
		performingAction(ClickOnWebElement(driver, btn_search));
	}

	
	
	// Vérifier le statut de connexion
	public void isConnected(){		
		String statut= "CONNECTE(E)";	
		Assert.assertEquals(txt_connecte.getText().toString().toLowerCase(), statut.toLowerCase());
	}
	
	*/
	
/*	
	//Se connecter
	public void seConnecter(){
		Actions actions = new Actions(driver);
		actions.moveToElement(btn_search).click().build().perform();
		//lnk_Seconnecter.click();
	}

	//Acc�der au panier
	void clickOnPanier(){
		Actions actions = new Actions(driver);
		actions.moveToElement(lnk_panier).click().build().perform();
		//lnk_panier.click();
	}

	//Acc�der � sa liste de favoris
	public void clickOnMyList(){
		Actions actions = new Actions(driver);
		actions.moveToElement(lnk_mylist).click().build().perform();
		//lnk_mylist.click();
	}

	//Savoir si l'utilisateur est connect�
	public void isConnected(){
		Assert.assertTrue(txt_connect�.isDisplayed());
	}



	 */

	/*
	 * @Corps_de_page
	 */








	/*
	 * Ent�tes
................................................................

// MENU HORIZONTAL
 
 //SOLDES
 
 //BONS PLANS
 
 //CUISINES SUR MESURES











Corps de page
.............................................................................

Page de r�sultats



<div class="title"></div>




........................................................................
@FindBy(xpath = "/html/body/div[1]/div/div/header/div/h1")
   private WebElement heading;
+
//We will use this boolean for assertion. To check if page is opened
   public boolean isPageOpened(){
       return heading.getText().toString().contains("Developer portal");
   }
............................................................................

@FindBy(linkText = "APPLY AS A DEVELOPER")

............................................................................

<span>Upload you pic :</span>
  css= span:contains(�Upload you pic �)

css=<HTML tag><:><contains><(text)>

............................................................................

Decoupage

amazon.createUser(user, paymentInfo, shippingInfo)
amazon.authenticate(username, password)
amazon.SearchPage.searchFor(item)
amazon.SearchPage.selectItem(1)
amazon.ProductPage.AddToCart()
amazon.CartPage.ProceedToCheckout()
amazon.OrderPage.PlaceOrder()


	public void clickOnSpecificArticle(String keyword){
		List<WebElement> items = getAllArticles();
		Iterator<WebElement> itr = items.iterator();
		while(itr.hasNext()) {
			@SuppressWarnings("unchecked")
			String values = ((Iterator<WebElement>) items).next().getText();
		    if(values.equalsIgnoreCase(keyword)){
		    	itr.next().click();
		    }else{
		    	System.out.println("�l�ment non trouv� !!");		    	
		    }
		}
	}



	 */








}
