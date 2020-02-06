package com.cognizant.cms.validator;

public class ValidateContent {
	
	public static String validateContent(String value) {
		if (value.endsWith(".tar") == true) {
			return "pass";
		}
		// System.out.println("path must contain .tar or .mpeg extention");
		return "fail";
	}

}
