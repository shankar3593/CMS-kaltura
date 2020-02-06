//package com.cognizant.cms.validator;
//
//import java.util.List;
//
//import javax.swing.text.Document;
//import javax.xml.XMLConstants;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.Source;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamSource;
//import javax.xml.validation.Schema;
//import javax.xml.validation.SchemaFactory;
//import javax.xml.validation.Validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.cognizant.cms.model.ADI;
////import com.amazonaws.util.StringInputStream;
//import com.cognizant.cms.model.AMS;
//import com.cognizant.cms.model.Asset;
//import com.cognizant.cms.parser.XmlParsing;
//import com.cognizant.cms.repository.MetadataRepo;
//
//public class ADI_Validator {
//
//	@Autowired
//	MetadataRepo repo;
//
//	public static String validateMajorWithinADI(ADI adi) throws Exception {
//
//		String major1 = adi.getMetadata().getAms().getVersion_Major();
//		List<Asset> assets = adi.getAsset();
//		for (Asset asset : assets) {
//			String major2 = asset.getMetadata().getAms().getVersion_Major();
//			if (!major2.equals(major1)) {
//
//				throw new Exception("invalid ADI due to mismatch in major version");
//
//			}
//
//			List<Asset> nestedassets = asset.getAsset();
//			for (Asset asset2 : nestedassets) {
//				String major3 = asset2.getMetadata().getAms().getVersion_Major();
//				if (!major3.equals(major1)) {
//
//					throw new Exception("invalid ADI due to mismatch in major version");
//
//				}
//
//			}
//
//		}
//
//		return "Major_Version has been validated successfully";
//	}
//
//	public static String validateProvider(String provider_ID) {
//		
//		
//		
//		
//		if (provider_ID.endsWith(".com") == true) {
//			return "pass";
//		}
//		// System.out.println("Provider name must contain .com extention");
//		return "fail";
//
//	}
//
//	public static String reasonProvider(String provider_ID) {
//		if (provider_ID.endsWith(".com") != true) {
//
//			return "Provider name must contain .com extention";
//		} else {
//			return "no error";
//		}
//	}
//
//	public static String validateContent(String value) {
//		if (value.endsWith(".tar") == true) {
//			return "pass";
//		}
//		// System.out.println("path must contain .tar or .mpeg extention");
//		return "fail";
//	}
//
//	public static String reasonContent(String value) {
//		if (value.endsWith(".tar") != true) {
//
//			return "path must contain .tar extention";
//		} else {
//			return "no error";
//		}
//	}
//
////	public static String validateMajorVersion(String Version_Major) {
//////		AMS ams=new AMS();
//////		ams.getVersion_Major().equalsIgnoreCase("1");
////
////		Query query = new Query();
////		query.addCriteria(Criteria.where("ams.version_Major").is("1"));
////
////		if (Version_Major == "1") {
////			return "pass";
////		} else {
////			return "fail";
////		}
////
////	}
//
//}
