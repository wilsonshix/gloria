package com.ivorytech.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path,String SheetName) throws Exception {  //int Numero_de_Feuille_Excel

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName); //getSheetAt(0)

		} catch (Exception e){

			throw (e);

		}

	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception{

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		}catch (Exception e){

			return"";

		}

	}

	//This method is to write in the Excel cell, Row num and Col num are the parameters

	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

			ExcelWBook.write(fileOut);

			fileOut.flush();

			fileOut.close();

		}catch(Exception e){

			throw (e);

		}

	}



	public static void readExcel(String pageName) throws Exception{

		//pageName.equalsIgnoreCase(getCellData(int RowNum, int ColNum));

		//Find number of rows in excel file

		int rowCount = ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();

		//Create a loop over all the rows of excel file to read it

		for (int i = 0; i < rowCount+1; i++) {

			if(pageName.equalsIgnoreCase(getCellData(i,3))){

				Row row = ExcelWSheet.getRow(i);

				//Create a loop to print cell values in a row

				for (int j = 0; j < row.getLastCellNum(); j++) {


					//Print Excel data in console
					System.out.print(row.getCell(j).getStringCellValue()+"|| ");


				}
			}

			System.out.println();

		}



	}
}
