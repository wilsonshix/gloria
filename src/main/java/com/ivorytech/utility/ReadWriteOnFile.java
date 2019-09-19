package com.ivorytech.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.ivorytech.writer.ObjectFactory;
import com.ivorytech.writer.FileWrite;



public class ReadWriteOnFile {
	
	// liste contenant les pageObjects
	public static String liste = "";
	
	public static List<String> lstfolder = new ArrayList<>();
	
	public static List<String> ObjectValuedata;
	
	public static List<String> ObjectNamedata ;
	
	public static List<String> WebElement ;
	
	public static List<String> lstrepo ;
	
	
	
	public static List<String> listFilesForFolder(File folder) throws IOException {
						
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	//liste = liste + fileEntry.getName().trim()+",";
	        	lstfolder.add(fileEntry.getName().trim());
	            System.out.println(fileEntry.getName());
	         /*  if ((fileEntry.getName().substring(fileEntry.getName().lastIndexOf('.') + 1, fileEntry.getName().length()).toLowerCase()).equals("java")) {
	        	   System.out.println("File= " + folder.getCanonicalPath()+ "\\" + fileEntry.getName());
	           }*/
	           
	        }
	    } //System.out.println("liste"+liste);  //
		return lstfolder;
	}
	
	
	
	

	
	
	
	
	//@SuppressWarnings("resource")
	public static List<String> ReadFileObject(String path, String fileName) throws Exception{  //Constant.Path_PageObject  + Constant.File_PageObject;
		
		
		if (fileName != null) {		
		
        // This will reference one line at a time
        String line = null;     
        String locatorValue = null;
        String locatorStrategy = null;
        String WebElementName = null;
        
        ObjectValuedata = new ArrayList<>();
        ObjectNamedata = new ArrayList<>();
        lstrepo = new ArrayList<String>();

        try {
        	        	
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(path+fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {  
            	
            	//if(line.trim().startsWith("//")) {continue;}//System.out.println("oui");
                            	     	
                if(line.trim().startsWith("@FindBy")) {  
                	
                   System.out.println(line.trim().toString());  									// @FindBy(xpath="//*[@id='password']")     
                	 
                   locatorStrategy = getLocatorType((String)line.trim());	               		   // xpath=
	                		
	               String values[]  = line.trim().split(locatorStrategy);  						// @FindBy(  et //*[@id='password']")
	             	 
	   		       locatorValue = values[1].substring(2,values[1].length()-2);      //  //*[@id='password']
		           	                	 
	   		    ObjectValuedata.add(locatorStrategy+ ":" + locatorValue + ":" +fileName);		                           	 
                }
                
                
                if(line.trim().startsWith("WebElement")) {  
                	
                    System.out.println(line.trim().toString());  									// WebElement msg_MyBasket;     
                 	 
                   // WebElementName	               		   
                   String name[] = null;	
 	               String values[]  = line.trim().split("WebElement");  						// msg_MyBasket;
 	             	 
 	              for (String val : values) {
	    				if (!val.isEmpty()) {
	    					System.out.println(val);
	    					name = val.split(";");	    					
	    				}
	    			}
 		           	                	 
 	             ObjectNamedata.add(name[0]);		                           	 
                 }
                
                
                if(line.trim().startsWith("public WebElement")) {  
                	
                    System.out.println(line.trim().toString());  									// WebElement msg_MyBasket;     
                 	 
                   // WebElementName	               		   
                   String name[] = null;	
 	               String values[]  = line.trim().split("public WebElement");  						// msg_MyBasket;
 	             	 
 	              for (String val : values) {
	    				if (!val.isEmpty()) {
	    					System.out.println(val);
	    					name = val.split(";");	    					
	    				}
	    			}
 		           	                	 
 	             ObjectNamedata.add(name[0]);		                           	 
                 }
                
           }
            
            // Always close files.
            bufferedReader.close(); 
            
            if(ObjectValuedata.size() == ObjectNamedata.size()) {
            	
            	for(int i = 0; i<ObjectValuedata.size() ; i++) {            		
            		lstrepo.add(ObjectNamedata.get(i)+ ":" + ObjectValuedata.get(i));            		
            	}
            }else if (ObjectValuedata.isEmpty() && ObjectNamedata.isEmpty()){
            	//throw new Exception("ObjectValuedata : "+ObjectValuedata.size()+" # ObjectNamedata : " + ObjectNamedata.size());
            	
            	
            }
            
            
            /*//
    			for (String data : ObjectNamedata) {
    				//String[] str = data.toString().split(":");
    				System.out.println(data); 
    			}*/
 
            
                    
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
		
		return lstrepo; //lstrepo    //ObjectValuedata
	
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
		
		List<String> listpageObject = listFilesForFolder(folder);
		WebElement = new ArrayList<String>();
				
		if(listpageObject!=null){
		
			listpageObject.forEach(lstitem->{
			try {
				System.out.println(lstitem);				
				WebElement = ReadFileObject(path,lstitem);
				RepoCreator(lstitem,WebElement, fileType);
				WebElement = null;
				System.out.println("*********************");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		}
		

			
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

