package com.ivorytech.writer;


public class ObjectFactory {
	
	public static FileWrite getObject(String type) {
		switch (type) {
		case "Excel":
			return new ExcelFileWriter();

		case "Csv":
			return new CsvFileWriter();
			
		case "Json":
			return new JsonFileWrite();
			
		case "POM":
			return new PageObjectGenerator();
		}
		throw new RuntimeException("Cannot create Object");
	}

}
