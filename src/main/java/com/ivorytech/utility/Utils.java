package com.ivorytech.utility;

import java.util.Random;

public class Utils {
	
	
	//Permet de repeter une action sur un WebElement
    public void performingAction(boolean actionOnWebElement) {    

		boolean bool = false;
		int i = 0;
		while (bool == false && i < 2) { //repète 2 fois
			bool = actionOnWebElement;
			i++;
			System.out.println(i);
			if(bool == false && i==3) {
				System.out.println("On n'a pas pu relancer la machine à click :)");
			}
		}        
		
	}
	
	
	
	
	
	
	
	public int randomizePairNumber(int size){
		Random rand = new Random();
		int  number = rand.nextInt((size/2)) + 0;	// 1 < number < items.size() 		div par 2 pour obtenir le bon nombre
		if (number % 2 != 1) {
			System.out.println(" number :"+ number);
		}
		
		return number;
	}

}
