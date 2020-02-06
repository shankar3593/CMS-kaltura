package com.cognizant.cms.validator;

import java.util.List;

import com.cognizant.cms.model.ADI;
import com.cognizant.cms.model.Asset;

public class ValidateMajorWithinADI {
	
	public static String validateMajorWithinADI(ADI adi) throws Exception {

		String major1 = adi.getMetadata().getAms().getVersion_Major();
		List<Asset> assets = adi.getAsset();
		for (Asset asset : assets) {
			String major2 = asset.getMetadata().getAms().getVersion_Major();
			if (!major2.equals(major1)) {

				throw new Exception("invalid ADI due to mismatch in major version");

			}

			List<Asset> nestedassets = asset.getAsset();
			for (Asset asset2 : nestedassets) {
				String major3 = asset2.getMetadata().getAms().getVersion_Major();
				if (!major3.equals(major1)) {

					throw new Exception("invalid ADI due to mismatch in major version");

				}

			}

		}

		return "Major_Version has been validated successfully";
	}

}
