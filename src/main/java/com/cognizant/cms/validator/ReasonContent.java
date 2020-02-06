package com.cognizant.cms.validator;

public class ReasonContent {

	public static String reasonContent(String value )
	{
		if (value.endsWith(".tar") != true) {

			return "path must contain .tar extention";	
			}
		else
		{
			return "no error";
		}
	}
}