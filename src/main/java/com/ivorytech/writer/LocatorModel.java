package com.ivorytech.writer;

public class LocatorModel {

	private String locatorType;
	public String getLocatorType() {
		return locatorType;
	}

	private String locatorValue;
	public String getLocatorValue() {
		return locatorValue;
	}
	
	private String locatorName;
	public String getLocatorName() {
		return locatorName;
	}

	private String objectName;
	public String objectName() {
		return objectName;
	}
	
	private String pageObjectName;
	public String pageObjectName() {
		return pageObjectName;
	}
	
	public LocatorModel(String objectName,String locatorType,String locatorName, String locatorValue, String pageObjectName) {
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
		this.locatorName = locatorName;
		this.pageObjectName = pageObjectName;
		this.objectName = objectName;
	}
	
	// A modifier c'est sûr !
	@Override
	public String toString() {
		return locatorType + ":" + locatorValue + ":" + locatorName;
	}
}
