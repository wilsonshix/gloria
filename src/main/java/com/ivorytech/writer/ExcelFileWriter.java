package com.ivorytech.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ivorytech.utility.ResourceHelper;


public class ExcelFileWriter implements FileWrite {
	
	private static final String SHEET_NAME = "Locator";
	
	private XSSFWorkbook getWorkBook() throws InvalidFormatException, IOException{
		return new XSSFWorkbook();
	}
	
	@Override
	public boolean writeToFile(String fileName, List<String> dData) {
		int i = 0;
		try(XSSFWorkbook book = getWorkBook()) {
			XSSFSheet sheet = book.createSheet(fileName); //SHEET_NAME
			for (String data : dData) {
				String[] str = data.toString().split(":");
				
				XSSFRow row = sheet.createRow(i++);
				row.createCell(0).setCellValue(str[0]);
				row.createCell(1).setCellValue(str[1]);
				row.createCell(2).setCellValue(str[2]);
				row.createCell(3).setCellValue(str[3]);
			}
			String filename[] = fileName.split(".java");
			book.write(new FileOutputStream(new File(ResourceHelper.getResourcePath("excel/") + filename[0] + ".xlsx")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
