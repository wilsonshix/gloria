package com.ivorytech.ReadWrite;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class ApplicationTest {
	
	
	
	public static void main(String[] args) throws IOException {

		
		String respath = "/com.ivorytech.pageFactory/LoginPage.java";
		InputStream in = ApplicationTest.class.getResourceAsStream(respath);
		if ( in == null ) {
			try {
				throw new Exception("resource not found: " + respath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}