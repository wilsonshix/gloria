package com.ivorytech.ReadWrite;

import com.ivorytech.utility.Constant;
import com.ivorytech.utility.ExcelUtils;
import com.ivorytech.utility.ReadWriteOnFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.testng.annotations.Test;

import com.ivorytech.pageFactory.*;

public class JavaFile_to_XcelFile {
	
	
	
		
	public static void main(String[] args) throws Exception{
		
		
		ExcelUtils.setExcelFile(Constant.Path_TestOR + Constant.File_TestOR ,"Objects");

		String sKeyword = ExcelUtils.getCellData(1, 1);

		String sObject = ExcelUtils.getCellData(1, 2);
		
		String sValue1 = ExcelUtils.getCellData(1, 3);
		
		String sValue2 = ExcelUtils.getCellData(1, 4);
		
		System.out.println(sKeyword);
		System.out.println(sObject);		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++");		
		
		ExcelUtils.reader(Constant.Path_TestOR  + Constant.File_TestOR ,0);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		
		ExcelUtils.reader2(Constant.Path_TestOR  + Constant.File_TestOR ,0);
		
		
		
		System.out.println("+++++++++++++++++++ FOLDER FILES LISTING ++++++++++++++++++");
				
						
		File folder = new File(Constant.Path_PageObject);        // ReadWriteOnFile.listFilesForFolder();
		
		String path = Constant.Path_PageObject;					 // ReadWriteOnFile.WriteReadFiles();
		
		//ReadWriteOnFile.listFilesForFolder(folder);
		
	//	System.out.println("Après"); System.out.println(ReadWriteOnFile.liste);
		
		
		System.out.println("+++++++++++++++++++ FILE READING ++++++++++++++++++");
		
		//String fileName = Constant.Path_PageObject  + Constant.File_PageObject;		
		//ReadWriteOnFile.ReadFile(Constant.Path_PageObject  + Constant.File_PageObject);
		
		
		System.out.println("+++++++++++++++++++ WRITE READ FILE ++++++++++++++++++");
		
		
		
		//ReadWriteOnFile.WriteReadFiles(folder, Constant.Path_PageObject, "Csv");
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get("/Users/willd/Downloads/CartPage.java"));
			System.out.println(allLines.size());
			for (String line : allLines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("+++++++++++++++++++ XXXXXXXXX ++++++++++++++++++");
		
		try (Stream<String> stream = Files.lines(Paths.get("/Users/willd/Downloads/CartPage.java"))) {
		    stream
		        .filter(s -> s.startsWith("@FindBy"))
		        
		        .sorted()
		        .map(String::toUpperCase)
		        .forEach(System.out::println);
		}
		
		
		
	}
	        
		

	

}
