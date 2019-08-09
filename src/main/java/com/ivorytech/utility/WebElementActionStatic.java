package com.ivorytech.utility;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WebElementActionStatic {

/*
	1- Chargement de la page
	2- Vérification de la présence d'objet
*/
	
	 //static WebDriver driver;

	
/***************************************************************************************************************************************************
 * METHODES PUBLIQUES A DESTINATION DES UTILISATEURS
 **************************************************************************************************************************************************/

	
	public static String PreviousPageTitle = "PreviousPageTitle"; 
	public static String ActualPageTitle ="ActualPageTitle"; 
	
	

	// #1  Renseigner une valeur dans un objet
	public static void Saisir(WebDriver driver, WebElement e, String a){
		System.out.println("Renseigner -- "+ e.getTagName()+" avec la valeur -- "+a.toString());
		performingSendKeysOnWebElement(driver, e, a.trim());		
	}


	// #2  Cliquer sur un objet
	public static void Cliquer(WebDriver driver, WebElement e){
		System.out.println("Cliquer sur -- "+ e.getTagName());
		performingClickOnWebElement(driver, e);		
	}
	
	
	// #3 Sélectionner un item dans un ComboBox
	public static void Selectionner(WebDriver driver, WebElement cbox, List<WebElement> list, String value){
		
		if(value.isEmpty()) {
			System.out.println("Cliquer sur -- "+ ((WebElement) list).getTagName());
			performingSelectRandomValueOnCBox(driver, list);
		}else {
			System.out.println("Cliquer sur -- "+ cbox.getTagName());
			performingSelectValueOnCBox(driver, cbox, value);
		}
		
		
		/*		Si 1 parametre => 	performingSelectRandomValueOnCBox(driver, cbxItems);
				Si 2 parametres => 	performingSelectValueOnCBox(driver, cbox, value);
				
				Le faire dans le code, depuis le lecteur des actions
		  			
		 */		
		
	}


	
	// #4 Atteindre une page
	public static void Naviguer(WebDriver driver, String url){ 
		System.out.println("Aller sur la page : ");
		driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS); 
		driver.get(url);
		driver.getTitle();
		WaitForWebPageLoaded(driver);		
	}
	
		
	
	
     // To improve
	//  Vérifier le statut de connexion de l'utilisateur
	public static void verifierConnexion(WebDriver driver, WebElement seConnecter, WebElement etreConnecte, WebElement seDeconnecter){	

		boolean Connexion_isClickable = WaitForWebElement(driver, seConnecter);
		boolean Connecte_isVisible = WaitForWebElement(driver, etreConnecte);  
		boolean Deconnexion_isClickable = WaitForWebElement(driver, seDeconnecter);


		if(Connexion_isClickable == true) {
			if(Deconnexion_isClickable == false && Connecte_isVisible == false) {
				System.out.println("Vous êtes deconnecté(e) !");
			}
		}else {
			if(Deconnexion_isClickable == true && Connecte_isVisible == true){
			System.out.println("Vous êtes connecté(e) !");
			}
			}						
	}


	
/***************************************************************************************************************************************************
* METHODES PRIVEES A DESTINATION DES DEVELOPPEURS
**************************************************************************************************************************************************/

	
	
	// Vérifier qu'un Element soit présent (cliquable) => pour les getElement        // tous les objets cliquales
	private static void isElementPresent(WebDriver driver, WebElement e) {    

		boolean isClickable = WaitForWebElement(driver, e);
		int i = 1;
		while (isClickable == false && i < 4) {
			isClickable = WaitForWebElement(driver, e);
			System.out.println("Tentative n°"+i+" de recherche de l'objet "+e.getText());
			i++;

			if(isClickable == false && i==4) {
				System.out.println("L'objet n'a pas été retrouvé : "+e.getText());   // Veuillez recharger la page ou vérifier la connexion
			}
		}  
	}	
	
	
	

	// Pas trop utile puisque WaitForWebPageLoaded est intégré dans Naviguer
	// Vérifier qu'une page est entierement Chargee avant toute opération dessus
	public static void ChargerPage(WebDriver driver, WebElement referenceElement, String page) {
		boolean isLoaded = isPageLoaded(driver, referenceElement, page);
		
		if(isLoaded == true) {
			System.out.println("La page "+ page +" a été chargée avec succès ! -- "+driver.getTitle());
		}else {
			System.out.println("La page "+ page +" n'a pas pu être chargée !");
		}
		
	}
	
	
	
	
	
	// A modifier puisque je vais inclure WaitForWebPageLoaded dans ChargerPage et Naviguer
	// Vérifier qu'une page soit complètement chargée + et qu'on soit sur la bonne page
	private static boolean isPageLoaded(WebDriver driver, WebElement element, String page) {    
		WaitForWebPageLoaded(driver);
		boolean isClickable = WaitForWebElement(driver, element);
		int i = 0;
		while (isClickable == false && i < 3) {
			isClickable = WaitForWebElement(driver, element);
			System.out.println("La page "+ page +" est en cours de chargement ...");
			i++;

		/*	if(isClickable == false && i==3) {
				System.out.println("La page "+ page +" n'a pas pu être chargée");
			}
		*/
		}        		
		return isClickable;
	}
	
	

	

	//Attendre le chargement complet d'une page
	private static void  WaitForWebPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 15); // Attendre 15 secondes à chaque fois
		wait.until(pageLoadCondition);        
	}
	
	
	

	//	Attendre et Vérifier qu'un élément soit localisé et cliquable
	private static boolean WaitForWebElement(WebDriver driver, WebElement e) {
		WebElement myDynamicElement = null;
		try {
			myDynamicElement = (new WebDriverWait(driver, 5))           // Attendre 5 secondes             
					.until(ExpectedConditions.elementToBeClickable(e)); // located and clickable (visible and enabled).
			return true;
		} catch (TimeoutException ex) {        	
			return false;
		} 
	}


	
	
	
	
	
	//Permet de repeter une action de click sur un WebElement
		private static void performingClickOnWebElement(WebDriver driver, WebElement e) {  
			
			boolean bool = ClickOnWebElement(driver, e);
			System.out.println("Entrée dans performingAction  - avant la boucle While - valeur initiale de b ="+bool);
			
			int i = 0;
			while (bool == false && i < 2) { //repeter 2 fois      
				
				bool = ClickOnWebElement(driver, e);
				System.out.println("performingAction n°"+i+" - dans la boucle While - valeur avant incrémentation de b ="+bool);
				i++;
				
				if(i==2 && bool==false) {					
					//Verifier que l'element est présent/visible dans la page
					isElementPresent(driver, e);
				}
			}   
		}


		
	//Permet de repeter une action d'écrire dans un WebElement
		private static void performingSendKeysOnWebElement(WebDriver driver, WebElement e, String v) {  
			
			boolean bool = SendKeysOnWebElement(driver, e, v);
			System.out.println("Entrée dans performingAction  - avant la boucle While - valeur initiale de b ="+bool);
			
			int i = 0;
			while (bool == false && i < 2) { //repeter 2 fois      
				
				bool = SendKeysOnWebElement(driver, e, v);
				System.out.println("performingAction n°"+i+" - dans la boucle While - valeur avant incrémentation de b ="+bool);
				i++;
				
				if(i==2 && bool==false) {					
					//Verifier que l'element est présent/visible dans la page
					isElementPresent(driver, e);
				}
			}   
		}
		
		
		
		






	//Permet de cliquer-sélectionner un WebElement
		private static boolean ClickOnWebElement(WebDriver driver, WebElement e) {
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
	private static boolean SendKeysOnWebElement(WebDriver driver, WebElement e, String v) {
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

			} else if(!(ex.getMessage() == null)){  // Add lines to avoid  NotClickableException

				System.out.println(ex.getMessage());
				return false;
			}
		}		

		return false;
	}
	
	
	
	
	//Permet de repeter l'action de sélectionner une valeur précise dans un ménu déroulant : ComboBox
	private static void performingSelectValueOnCBox(WebDriver driver, WebElement e, String v) {  
		
		boolean bool = SelectValueOnCBox(driver, e, v);
		System.out.println("Entrée dans performingAction  - avant la boucle While - valeur initiale de b ="+bool);
		
		int i = 0;
		while (bool == false && i < 2) { //repeter 2 fois      
			
			bool = SendKeysOnWebElement(driver, e, v);
			System.out.println("performingAction n°"+i+" - dans la boucle While - valeur avant incrémentation de b ="+bool);
			i++;
		}   
	}



	//Permet de sélectionner une valeur précise dans un ménu déroulant : ComboBox
	private static boolean SelectValueOnCBox(WebDriver driver, WebElement e, String v){  

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
			if(ex.getMessage().contains("no such element")){
				System.out.println(" NoSuchElementException - L'élément est introuvable !");
				return false;
			}
			else if(!(ex.getMessage()==null)) {
				System.out.println("Error found: "+ex.getMessage());
				return false;
			}
		}
		return false;
	}
	
	
	
	
	//Permet de repeter l'action de sélectionner une valeur aléatoire dans un ménu déroulant
	private static void performingSelectRandomValueOnCBox(WebDriver driver, List<WebElement> list) {  
		
		boolean bool = SelectRandomValueOnCBox(driver, list);
		System.out.println("Entrée dans performingAction  - avant la boucle While - valeur initiale de b ="+bool);
		
		int i = 0;
		while (bool == false && i < 2) { //repeter 2 fois      
			
			bool = SelectRandomValueOnCBox(driver, list);
			System.out.println("performingAction n°"+i+" - dans la boucle While - valeur avant incrémentation de b ="+bool);
			i++;
		}   
	}



	//Permet de sélectionner une valeur aléatoire dans un ménu déroulant
	private static boolean SelectRandomValueOnCBox(WebDriver driver, List<WebElement> list){  

		try{

			List<WebElement> items = list; 					
			Random rand = new Random();
			int number = rand.nextInt(items.size());  
			System.out.println("**********************************");
			System.out.println("random CBoxIndex :"+number);	
			System.out.println("random CBoxValue :"+items.get(number).getText());	
			items.get(number).click();
			//ClickOnWebElement(driver,items.get(number));		pour la prochaine évolution !!
			return true;

		}catch(Throwable ex){
			System.out.println("Error found: "+ex.getMessage());
			
			}
		return false;
	}




	// Vérifier qu'un élément à été trouvé lors d'une recherche parmis plusieurs autres éléments
	private static boolean ArticleFound(WebDriver driver, WebElement e, String p) {

		if(!(e.getText().isEmpty())){	
			System.out.println("i:"+e.getText());
			if(e.getText().equalsIgnoreCase(p)){
				System.out.print("l'heureux élu est :"+e.getText());
				System.out.println(" VS   produit recherché : "+p);				
				performingClickOnWebElement(driver, e);	
				return true;				
			}
		}
		return false;
	}
	
	
	
	
	/*///////////////////////////////////////////////////////// SearchPage //////////////////////////////////////////////////////////////////////*/
	
	
	/*public default void goToNexLink(Driver driver, WebElement e){		
		performingClickOnWebElement(driver, e);	 //pageLink	
	}*/

// Récupérer le titre de la recherche du produit
	public static String getSearchTitle(WebElement e){		//WebElement txt_searchTitle
		return e.getText();
	}

	//Vérifier que le titre de la recherche est correcte
	public static void isCorrectResultTitle(WebElement e, String search){		
		String title= "Résultats pour :"+search+"!";	
		Assert.assertEquals(e.getText().toString().toLowerCase(), title.toLowerCase());
	}

	//Vérifier si la recherche retourne des résultats  KO
/*	public default boolean areArticlesPresent(){	
		boolean display = driver.findElement(By.id("request_title")).isDisplayed();
		return 	display;
	}*/




	//Sélectionner un article : aléatoirement
	public static void clickOnRandomArticle(WebDriver driver, List<WebElement> list){ 
		//List<WebElement> items = list; 	    // articles_Nom	
		int size = getArticleSize(list);
		Random rand = new Random();
		System.out.println("**********************************");

		int number = rand.nextInt(size);  //0 + 31
		System.out.println("radom number :"+number);

		int i = 0;
		for(WebElement item : list) {		

			if(!(item.getText().isEmpty())){	
				System.out.println("i: "+i+"  "+item.getText());
				if(i==number){
					System.out.println("l'heureux élu est : "+i+" :"+item.getText());
					performingClickOnWebElement(driver, item);	
					break;
				}
				i++;
			}				
		}
	}



	//Sélectionner un article en cliquant sur son image								A supprimer / Remplacer par Cliquer()
	public static void clickOnImageArticle(WebDriver driver, WebElement e){
		performingClickOnWebElement(driver, e);  // article_img
	}


	// Connaitre l'état de recherche d'un article bien défini : par son nom	                       
	//Si trouvé => cliquer  Sinon goToNext
	public static void clickOnSpecificArticle(WebDriver driver, List<WebElement> list, WebElement lnkNext, String keyword){		//WebElement e
		List<WebElement> items = list; 		//articles_Nom
		boolean rep = false;
		
		do{
			for (WebElement e : items) {	//e
				rep = ArticleFound(driver, e, keyword);
				if(rep == true){
					break;
				}
			}
			if(rep == false){
				Cliquer(driver, lnkNext);
			}
		}while(rep == false);
	}			






	// Permet d'obtenir le nombre d'articles 
	public static int getArticleSize(List<WebElement> list){
		List<WebElement> myElements = list; 	 //articles_Nom
		int i = 0;
		for(WebElement e : myElements) {			

			if(!(e.getText().isEmpty())){					
				i++;
			}						
		}		
		return i;
	}	


	// Permet d'afficher tous les articles trouvés par la recherche
	public static void displayAllArticles(WebDriver driver, List<WebElement> list, WebElement wb){
		
		boolean bool = WaitForWebElement(driver, wb); // <> article_Nom     Demo pour �viter les objets non atteignables
		if(bool==true){
			
			List<WebElement> myElements = list;   //articles_Nom
			int i = 0;
			for(WebElement e : myElements) {			

				if(!(e.getText().isEmpty())){				
					System.out.println(i +": "+e.getText());
					i++;
				}			
			}
			System.out.println("elements : "+i );
		}
	}



	//Sélectionner une des options de tri, en fonction de sa valeur 		
	public static void selectCBoxValue(WebDriver driver, WebElement cbox, String value){ 		
		performingSelectValueOnCBox(driver, cbox, value);  				
	}

	//Sélectionner une des options de tri, de manière aléatoire	
	public static void selectRandomCBoxValue(WebDriver driver, List<WebElement> cbxItems){
		performingSelectRandomValueOnCBox(driver, cbxItems);      			
	}

	//Récuperer toutes les options de tri, dans une liste	
	public static List<WebElement> getAllSortingValue(WebDriver driver, WebElement wb){
		List<WebElement> myElements = driver.findElements(By.xpath("//*[@id='sort_by']/option"));		
		return myElements;
	}	

	//Afficher toutes les options de tri, dans la console	
	public static void displayAllSortingValue(WebDriver driver, List<WebElement> list){		
		List<WebElement> myElements = list; 				//driver.findElements(By.xpath("//*[@id='sort_by']/option"));
		for(WebElement e : myElements) {
			System.out.println(e.getText());			
		}
		System.out.println(myElements.size());
	}

	
	

	
	public static int randomizePairNumber(int size){
		Random rand = new Random();
		int  number = rand.nextInt((size/2)) + 0;	// 1 < number < items.size() 		div par 2 pour obtenir le bon nombre
		if (number % 2 != 1) {
			System.out.println(" number :"+ number);
		}
		
		return number;
	}
	
	
	

	/*///////////////////////////////////////////////////////// ProductPage /////////////////////////////////////////////////////////////////////////////// */
	
	
	//Savoir si la garantie est proposée
		public static void isGuaranteePresent(){		
			//boolean rep = Assert.assertTrue(txt_guarantee.isDisplayed());
		}
		

		
		
		
		//Improve avec Replay et repeat after 
		//Vérifier la présence du bouton Ajouter ***Autrement il n'est pas disponible      btn_addToCard
		public static boolean addToCardIsDisplayed(WebElement e){
			boolean rep = true;
			if(e.isDisplayed()){
				rep = true;
			}else{
				rep = false;
			}
			return rep;		
		}
		
		
		//Improve avec Replay et repeat after 
		public static boolean isBtnAddToCardPresent(WebDriver driver){
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
		
		


		//Improve
		//Vérifier que le produit a été bien ajouté au panier
		public static void isCorrectResultTitle(WebElement wb){							// msg_productAdded
			String title= "Votre produit a bien �t� ajout� au panier";	
			Assert.assertEquals(wb.getText().toString().toLowerCase(), title.toLowerCase());
		}

		//Permet d'accéder au panier
		public static void clickOnAccessToCart(WebDriver driver, WebElement wb){			// btn_AccessToCart
			Cliquer(driver, wb);
			
			//Exp�rimentation de action tracing
			System.out.println("Click btn Acc�der au panier - OK");
			
			}

		//Permet d'accéder au panier avec gestion des alertes
		public static void clickOnAccessToCart2(WebDriver driver, WebElement wb){      // btn_AccessToCart

			try {
				// Get the number of windows open before clicking
				int numberOfWindowsBefore = driver.getWindowHandles().size();
				String title = driver.getTitle();
				wb.click();
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


		//Fermer la pop up qui s'affiche après avoir cliqué sur btn clickOnAccessToCart
		//lorsque le pop up n'est pas présent = erreur
		public static void clickOnCloseAlert(WebElement wb){    //btn_CloseAlert
			wb.click();
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
	
	
	
	
	
	
	
		/*/////////////////////////////////////////////////////////////// KEYWORDS INTERPRETOR //////////////////////////////////////////////////////////////////// */
	
	
	
		/**
		    * This Method will return web element.
		    * @param locator
		    * @return
		    * @throws Exception
		    */
	
		
		//@FindBy(xpath="//*[@id='login']") 
		//@CacheLookup 
		//public WebElement txt_username;
	
	/*	public  default WebElement getLocator(WebDriver driver, String locator) throws Exception {
	        String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];

			if (locatorType.toLowerCase().equals("id"))
				return driver.findElement(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElement(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))
					|| (locatorType.toLowerCase().equals("class")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElement(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElement(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		}
		
		public default List<WebElement> getLocators(WebDriver driver, String locator) throws Exception {
	        String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];

			if (locatorType.toLowerCase().equals("id"))
				return driver.findElements(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElements(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))
					|| (locatorType.toLowerCase().equals("class")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElements(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElements(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		}
		
		*/

		/*public default WebElement getWebElement(String locator) throws Exception{
			System.out.println("locator data:-"+locator+"is---"+Repository.getProperty(locator));
			return getLocator(Repository.getProperty(locator));
		}
		
		public default List<WebElement> getWebElements(String locator) throws Exception{
			return getLocators(Repository.getProperty(locator));
		}
		
		public default String expliciteWait(WebDriver driver, String webElement) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(getWebElement(webElement)));
			return "Pass";
		}*/
	
	
	
	
	
	
	
	
	
	
		/*/////////////////////////////////////////////////////////////// Others Things //////////////////////////////////////////////////////////////////// */
	
	
	


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


}

