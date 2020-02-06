package com.cognizant.cms.validator;

public class ValidateProvider {

	public static String validateProviedr(String provider_ID )
	{
		if (provider_ID.endsWith(".com") == true) {
			return "pass";
		}
		//System.out.println("Provider name must contain .com extention");
		return "fail";
		
	}
}
 