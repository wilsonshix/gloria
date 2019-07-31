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
	public	WebElement txt_searchTitle;

	//Bloc rectangulaire produit
	//<div class="product_detail next_prev_info"
	@FindBy(xpath="//div[@class='product_detail next_prev_info']")
	@CacheLookup
	public WebElement article_bloc;


	//Image du produit
	@FindBy(xpath="//a/img[@class='lazyload']")
	@CacheLookup
	public WebElement article_img;


	//Menu de tri pour l'affichage - ComboBox
	@FindBy(xpath="//*[@id='sort_by']")
	public WebElement CBox_sorting;
	
	//Toutes les options du menu de tri pour l'affichage - ComboBox
	@FindBy(xpath="//*[@id='sort_by']/option")
	public List<WebElement> Lst_CBoxValues;


	//Tous les Noms de produit
	@FindBy(className="next_prev")
	public List<WebElement> lst_Noms_Articles;

	//Un seul Nom de produit  (s)
	@FindBy(className="next_prev")
	public WebElement txt_Nom_Article;

	//PageLinks List
	@FindBy(partialLinkText="Page suivante")
	public WebElement lnk_PageSuivante;


	/*traiter le cas o� le produit s�lectionn� n'est pas disponible 
	 *
	 */


	// traiter le cas o� la recherche ne retourne rien __ d�ja commenc� *

	//	Aller à la page suivante  OK  + penser à améliorer avec la vérification de ne pas être à la fin
	
	
	
	
	
	
	
	
	
	
	
	// Vérifier que l'élément est cliquable => page is loaded !
	public boolean isAt() {    		 
		return isPageLoaded(driver, txt_Nom_Article, "SearchPage");
	}
	










}

