package com.cognizant.cms;

import java.util.List;

import com.cognizant.cms.model.ADI;
import com.cognizant.cms.model.Asset;
import com.cognizant.cms.parser.XmlParsing;



public class Main {

	public static void main(String[] args) {
		
		XmlParsing parsing = new XmlParsing();
		ADI adi = parsing.getData();
		List<Asset> assets=adi.getAsset();
		for (Asset asset : assets) {
			System.out.println(asset.getMetadata().getAms());
			List<Asset>nestedassets=asset.getAsset();
			for (Asset asset2 : nestedassets) {
				System.out.println(asset2.getMetadata().getAms());
			}
		}
	}
}
