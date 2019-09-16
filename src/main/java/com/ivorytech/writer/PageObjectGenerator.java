package com.ivorytech.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.How;

import com.ivorytech.utility.ResourceHelper;


public class PageObjectGenerator implements FileWrite {
	
	private static int count = 1;
	protected String formatWebElement(String how,String using) {
		StringBuilder build = new StringBuilder();
		
		build.append("\n")
			.append("\t")
			.append("\t")
			.append("@FindBy("+ how +"="+ using +"\")")  
			.append("\n")
			.append("\t")
			.append("\t");
		
	//	@FindBy(how=How.XPATH,using="//span[normalize-space(text())='Check out']")
	//	public WebElement xpath_91;
		
		// Xpath,//a[@title='Contact Us']
		
		if(!"How.XPATH".equalsIgnoreCase(how)){
			build.append("public WebElement " + using.replaceAll("[^\\w\\s]", "") +";")
			.append("\n");
			return new String(build);
		}
		build.append("public WebElement xpath_" + count++ +";")
		.append("\n");
		return new String(build);
	}
	
	
	private String getWebElementString(String how,String using) {
		
		if(!"class".equalsIgnoreCase(how))
			switch (How.valueOf(how.toUpperCase())) {
			case ID:
				return formatWebElement("id", using); //How.ID
			case LINK_TEXT:
				return formatWebElement("linkText", using);
			case XPATH:
				return formatWebElement("xpath", using);
			case CSS:
				return formatWebElement("css", using);
			case NAME:
				return formatWebElement("name", using);
			case PARTIAL_LINK_TEXT:
				return formatWebElement("partialLinkText", using);
			default:
				break;
			}
		
		return formatWebElement("className",using);  //"How.CLASS_NAME"
	}

	@Override
	public boolean writeToFile(String fileName, List<String> dData) {
		
		count = 1;
		File file = new File(ResourceHelper.getResourcePath("pageobject/") + fileName + ".java");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		try(FileWriter fWrite = new FileWriter(file,false)) {
			
			fWrite.append("\n")
				.append("public class " + fileName + " {");
			for (String model : dData) {
				//fWrite.append(getWebElementString(model.getLocatorType(), model.getLocatorValue()));
			}
			fWrite.append("\n")
				.append("}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
