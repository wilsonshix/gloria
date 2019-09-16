package com.ivorytech.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ivorytech.writer.ObjectFactory;
import com.ivorytech.writer.CsvFileWriter;
import com.ivorytech.writer.ExcelFileWriter;
import com.ivorytech.writer.FileType;
import com.ivorytech.writer.FileWrite;
import com.ivorytech.writer.JsonFileWrite;
import com.ivorytech.writer.LocatorModel;
import com.ivorytech.writer.PageObjectGenerator;
import com.opencsv.CSVWriter;



public class ReadWriteOnFile {
	
	// liste contenant les pageObjects
	public static String liste = "";
	
	public static List<String> lstdemo = new ArrayList<>();
	
	public static List<String> lstrepo = new ArrayList<>();
	
	public static List<String> data = new ArrayList<>();
	
	
	
	
	public static List<String> listFilesForFolder(File folder) throws IOException {
						
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	//liste = liste + fileEntry.getName().trim()+",";
	        	lstdemo.add(fileEntry.getName().trim());
	            System.out.println(fileEntry.getName());
	         /*  if ((fileEntry.getName().substring(fileEntry.getName().lastIndexOf('.') + 1, fileEntry.getName().length()).toLowerCase()).equals("java")) {
	        	   System.out.println("File= " + folder.getCanonicalPath()+ "\\" + fileEntry.getName());
	           }*/
	           
	        }
	    } //System.out.println("liste"+liste);  //
		return lstdemo;
	}
	
	
	
	

	
	
	
	
	public static List<String> ReadFile(String fileName) throws IOException{  //Constant.Path_PageObject  + Constant.File_PageObject;
		
		
		if (fileName != null) {		
		
        // This will reference one line at a time
        String line = null;     
        String locatorValue = null;
        String locatorStrategy = null;
        String WebElementName = null;

        try {
        	        	
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {  
            	
            	//if(line.startsWith("/"+"/")) {System.out.println("oui");}
                            	     	
                if(line.contains("@FindBy")) {  
                	
                   System.out.println(line.trim().toString());  									// @FindBy(xpath="//*[@id='password']")     
                	 
                   locatorStrategy = getLocatorType((String)line.trim());	               		   // xpath=
	                		
	               String values[]  = line.trim().split(locatorStrategy);  						// @FindBy(  et //*[@id='password']")
	             	 
	   		       locatorValue = values[1].substring(2,values[1].length()-2);      //  //*[@id='password']
		          // System.out.println("locatorStrategy : "+locatorStrategy);
		           //System.out.println("locatorValue :"+locatorValue);
		           //System.out.println(values[0]);
		          // System.out.println(values[1]); 	                	 
		           lstrepo.add(locatorStrategy+ ":" + locatorValue + ":" +fileName);		                           	 
                }
           }               
            /*//
    			for (String data : lstrepo) {
    				String[] str = data.toString().split(":");
    				System.out.println(data); 
    			}*/
 
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
		return lstrepo;
	
	}
	
	
	
	
	public static String getLocatorType (String line ) {
		
		if(line.trim().contains("id=\"")) {
			return "id";
		}else if(line.trim().contains("linkText=\"")) {
			return "linkText";
		}else if(line.trim().contains("xpath=\"")) {
			return "xpath";
		}else if(line.trim().contains("css=\"")) {
			return "css";
		}else if(line.trim().contains("name=\"")) {
			return "name";
		}else if(line.trim().contains("partialLinkText=\"")) {
			return "partialLinkText";
		}else if(line.trim().contains("className=\"")) {
			return "className";
		}else {
			
			System.out.println("A corriger !!!"+line);			
			return "";
			//throw new RuntimeException("Aucun locator type présent dans cette ligne");
		}
	
	}
	
	
	

	
	
	// Lire pour chaque fichier
	
	public static void WriteReadFiles(File folder, String path, String fileType) throws IOException {
		
		List<String> listFolder = listFilesForFolder(folder);
				
		if(listFolder!=null){
		
			listFolder.forEach(lstitem->{
			try {
				System.out.println(lstitem);				
				data = ReadFile(path+lstitem);
				RepoCreator(lstitem,data, fileType);
				System.out.println("*********************");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		}
		
	/*	String list = listFilesForFolder(folder);
		
		String[] tab = list.split(",");
		
	    for (String tmp : tab){
	        System.out.println(tmp);
	        ReadFile(path+tmp);
	        System.out.println("*********************");
	      }*/
			
		}
	
	
	
		
		
	
	/**
	 * Generate the locator and write to the file
	 * @param type : Excel, POM, Csv, Json
	 */
		// Créer un fichier repo pour chaque fichier lu  
		
			public static void RepoCreator(String pageObject, List<String> dData, String fileType) throws IOException {					
					FileWrite writer = ObjectFactory.getObject(fileType);					
					writer.writeToFile(pageObject, dData);	
					
					}
		
					
	
			
			
			
			/*I had written this utility function which returns a string combination of locator strategy + locator value.

			private String getLocatorFromWebElement(WebElement element) {

			    return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
			}
		
	
	*/
	
	}

