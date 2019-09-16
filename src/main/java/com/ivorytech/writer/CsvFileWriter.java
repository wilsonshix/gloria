package com.ivorytech.writer;

import java.io.FileWriter;
import java.util.List;

import com.ivorytech.utility.ResourceHelper;
import com.opencsv.CSVWriter;

public class CsvFileWriter implements FileWrite {
	@Override
	public boolean writeToFile(String fileName, List<String> dData) {
		try (CSVWriter writer = new CSVWriter(new FileWriter(ResourceHelper.getResourcePath("locator/") + fileName + ".csv",false),',')){
			for (String data : dData) {
				String[] str = data.toString().split(":");
				writer.writeNext(str, false);
			}
		return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	}

}
