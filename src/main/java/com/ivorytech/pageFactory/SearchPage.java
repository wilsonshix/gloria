package com.ivorytech.pageFactory;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ivorytech.utility.WebElementAction;

public class SearchPage extends CommonOnPage implements WebElementAction{

	ArrayList<String> cBoxOptions;
	WebDriver driver;

	boolean rep = false;

	//Constructor
	public SearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}




	/*
	 * @Ent�tes
	 */


	/*
	 * @Corps de page
	 */

	//Lib�ll� de la recherche
	@FindBy(xpath="//*[@id='request_title']")
	@CacheLookup
	WebElement txt_searchTitle;

	//Bloc rectangulaire produit
	//<div class="product_detail next_prev_info"
	@FindBy(xpath="//div[@class='product_detail next_prev_info']")
	@CacheLookup
	WebElement article_bloc;


	//Image du produit
	@FindBy(xpath="//a/img[@class='lazyload']")
	@CacheLookup
	WebElement article_img;


	//Menu de tri pour l'affichage - ComboBox
	@FindBy(xpath="//*[@id='sort_by']")
	WebElement CBox_sorting;
	
	//Toutes les options du menu de tri pour l'affichage - ComboBox
	@FindBy(xpath="//*[@id='sort_by']/option")
	List<WebElement> CBox_values;


	//Tous les Noms de produit
	@FindBy(className="next_prev")
	List<WebElement> articles_Nom;

	//Un seul Nom de produit  (s)
	@FindBy(className="next_prev")
	WebElement article_Nom;

	//PageLinks List
	@FindBy(partialLinkText="Page suivante")
	WebElement pageLink;


	/*traiter le cas o� le produit s�lectionn� n'est pas disponible 
	 *
	 */


	// traiter le cas o� la recherche ne retourne rien __ d�ja commenc� *

	//	Aller à la page suivante  OK  + penser à améliorer avec la vérification de ne pas être à la fin
	public void goToNexLink(){		
		performingAction(ClickOnWebElement(driver, pageLink));		
	}

// Récupérer le titre de la recherche
	public String getSearchTitle(WebElement txt_searchTitle){		
		return txt_searchTitle.getText();
	}

	//Vérifier que le titre de la recherche est correcte
	public void isCorrectResultTitle(String search){		
		String title= "Résultats pour :"+search+"!";	
		Assert.assertEquals(txt_searchTitle.getText().toString().toLowerCase(), title.toLowerCase());
	}

	//Vérifier si la recherche retourne des résultats  KO
	public boolean areArticlesPresent(){	
		boolean display = driver.findElement(By.id("request_title")).isDisplayed();
		return 	display;
	}




	//Sélectionner un article : aléatoirement
	public void clickOnRandomArticle(){ 
		List<WebElement> items = articles_Nom; 		
		int size = getArticleSize();
		Random rand = new Random();
		System.out.println("**********************************");

		int number = rand.nextInt(size);  //0 + 31
		System.out.println("radom number :"+number);

		int i = 0;
		for(WebElement e : items) {		

			if(!(e.getText().isEmpty())){	
				System.out.println("i: "+i+"  "+e.getText());
				if(i==number){
					System.out.println("l'heureux élu est : "+i+" :"+e.getText());
					performingAction(ClickOnWebElement(driver, e));	
					break;
				}
				i++;
			}				
		}
	}



	//Sélectionner un article en cliquant sur son image
	public void clickOnImageArticle(){
		performingAction(ClickOnWebElement(driver, article_img));
	}


	// Connaitre l'état de recherche d'un article bien défini : par son nom	                       
	//Si trouvé => cliquer  Sinon goToNext
	public void clickOnSpecificArticle(String keyword){		
		List<WebElement> items = articles_Nom; 
		boolean rep = false;
		
		do{
			for (WebElement e : items) {	
				rep = ArticleFound(driver, e, keyword);
				if(rep == true){
					break;
				}
			}
			if(rep == false){
				goToNexLink();
			}
		}while(rep == false);
	}			






	// Permet d'obtenir le nombre d'articles 
	public int getArticleSize(){
		List<WebElement> myElements = articles_Nom; 	
		int i = 0;
		for(WebElement e : myElements) {			

			if(!(e.getText().isEmpty())){					
				i++;
			}						
		}		
		return i;
	}	


	// Permet d'afficher tous les articles trouvés par la recherche
	public void displayAllArticles(){
		
		boolean bool = isElementPresent(driver, article_Nom); // <> articles_Nom     Demo pour �viter les objets non atteignables
		if(bool==true){
			
			List<WebElement> myElements = articles_Nom; 
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
	public void selectCBoxValue(String value){ 		
		performingAction(selectValueOnCBox(driver, CBox_sorting, value));
	}

	//Sélectionner une des options de tri, de manière aléatoire	
	public void selectRandomCBoxValue(){
		performingAction(selectRandomValueOnCBox(driver, CBox_values)); 
	}

	//Récuperer toutes les options de tri, dans une liste	
	public List<WebElement> getAllSortingValue(){
		List<WebElement> myElements = driver.findElements(By.xpath("//*[@id='sort_by']/option"));
		return myElements;
	}	

	//Afficher toutes les options de tri, dans la console	
	public void displayAllSortingValue(){		
		List<WebElement> myElements = CBox_values; 				//driver.findElements(By.xpath("//*[@id='sort_by']/option"));
		for(WebElement e : myElements) {
			System.out.println(e.getText());			
		}
		System.out.println(myElements.size());
	}


	// Vérifier que l'élément est cliquable => page is loaded !
	public boolean isAt() {    		 
		return isPageLoaded(driver, article_Nom, "SearchPage");
	}
	










}

