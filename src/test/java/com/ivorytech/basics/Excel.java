package com.ivorytech.basics;

import com.ivorytech.utility.Constant;
import com.ivorytech.utility.ExcelUtils;

public class Excel {

	public static void main(String[] args) throws Exception{
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Objects");

		ExcelUtils.readExcel("homepage");

	}
}

