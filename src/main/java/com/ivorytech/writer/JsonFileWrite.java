package com.ivorytech.writer;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ivorytech.utility.ResourceHelper;


public class JsonFileWrite implements FileWrite {

	@Override
	public boolean writeToFile(String fileName, List<String> dData) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		try(PrintWriter out = new PrintWriter(new File(ResourceHelper.getResourcePath("json/") + fileName + ".json"))) {
			String str = gson.toJson(dData,ArrayList.class).replace("\\u0027", "'").replace("\\u003d", "=");
			out.write(str);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
