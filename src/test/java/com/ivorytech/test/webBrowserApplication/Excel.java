package com.ivorytech.test.webBrowserApplication;

import com.ivorytech.utilities.Constant;
import com.ivorytech.utilities.ExcelUtils;

public class Excel {

	public static void main(String[] args) throws Exception{
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Objects");

		ExcelUtils.readExcel("homepage");

	}
}

