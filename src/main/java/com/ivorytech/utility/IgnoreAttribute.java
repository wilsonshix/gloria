package com.ivorytech.utility;

import java.util.Arrays;
import java.util.List;

public class IgnoreAttribute {
	public static List<String> ignoreAttribute = Arrays.asList(new String[]{
			"@CacheLookup",
			"href",
			"src","width",
			"height",
			"alt",
			"style",
			"disabled",
			"target",
			"rel"
		});

}
