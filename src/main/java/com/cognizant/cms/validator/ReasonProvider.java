package com.cognizant.cms.validator;

public class ReasonProvider {

	public static String reasonProviedr(String provider_ID )
	{
		if (provider_ID.endsWith(".com") != true) {

			return "Provider name must contain .com extention";			
			}
		else
		{
			return "no error";
		}
	}
}
