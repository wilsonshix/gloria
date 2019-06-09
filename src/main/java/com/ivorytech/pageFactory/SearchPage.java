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

	//	Aller � la page suivante  OK  + penser � am�liorer avec la v�rification de ne pas �tre � la fin
	public void goToNexLink(){		
		performingAction(ClickOnWebElement(driver, pageLink));		
	}


	public String getSearchTitle(WebElement txt_searchTitle){		
		return txt_searchTitle.getText();
	}

	//V�rifier que le titre de la recherche est correcte
	public void isCorrectResultTitle(String search){		
		String title= "R�sultats pour �"+search+"�";	
		Assert.assertEquals(txt_searchTitle.getText().toString().toLowerCase(), title.toLowerCase());
	}

	//V�rifier si la recherche retourne des r�sultats  KO
	public boolean areArticlesPresent(){	
		boolean display = driver.findElement(By.id("request_title")).isDisplayed();
		return 	display;
	}




	//S�lectionner un article : al�atoirement
	public void clickOnRandomArticle(){ 
		List<WebElement> items = articles_Nom; 		
		int size = getArticleSize();
		Random rand = new Random();
		System.out.println("**********************************");

		int number = rand.nextInt(size);  //0 � 31
		System.out.println("radom number :"+number);

		int i = 0;
		for(WebElement e : items) {		

			if(!(e.getText().isEmpty())){	
				System.out.println("i: "+i+"  "+e.getText());
				if(i==number){
					System.out.println("l'heureux �lu est : "+i+" :"+e.getText());
					performingAction(ClickOnWebElement(driver, e));	
					break;
				}
				i++;
			}				
		}
	}



	//S�lectionner un article en cliquant sur son image
	public void clickOnImageArticle(){
		performingAction(ClickOnWebElement(driver, article_img));
	}


	// Connaitre l'�tat de recherche d'un article bien d�fini : par son nom	                       
	//Si trouv� => cliquer  Sinon goToNext
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






	// Retourner le nombre d'articles 
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


	// Afficher tous les articles retourn�s par la recherche
	public void displayAllArticles(){
		
		boolean bool = ElementIsPresent(driver, article_Nom); // <> articles_Nom     Demo pour �viter les objets non atteignables
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



	//S�lectionner une des options de tri, en fonction de sa valeur 		
	public void selectCBoxValue(String value){ 		
		performingAction(selectValueOnCBox(driver, CBox_sorting, value));
	}

	//S�lectionner une des options de tri, de mani�re al�atoire	
	public void selectRandomCBoxValue(){
		performingAction(selectRandomValueOnCBox(driver, CBox_values)); 
	}

	//R�cuperer toutes les options de tri, dans une liste	
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


	// V�rifier que l'�l�ment est cliquable => page is loaded !
	public boolean isAt() {    

		boolean isClickable = WaitForWebElement(driver, article_Nom);
		int i = 1;
		while (isClickable == false && i < 4) {
			isClickable = WaitForWebElement(driver, article_Nom);
			System.out.println("page is loading ...");
			i++;

			if(isClickable == false && i==4) {
				System.out.println("SearchPage was not successfully loaded");
			}
		}        
		return isClickable;
	}









}

