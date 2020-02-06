package com.cognizant.cms.parser;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import com.cognizant.cms.model.ADI;
import com.cognizant.cms.model.Metadata;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlParsing {

	public ADI getData() {
		ADI adi = null;
		try {
			File inputFile = new File("C:/Users/728929/Desktop/ADI.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			JAXBContext jaxbContext = JAXBContext.newInstance(ADI.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			adi = (ADI) jaxbUnmarshaller.unmarshal(inputFile);

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		} catch (Exception e) {
			System.out.println("the xml file is corrupt" + e);
		}
		return adi;
	}

	public Metadata getMetaData() {
		ADI adi = new XmlParsing().getData();
		return adi.getMetadata();

	}
	
	public String generateTitleId()
	{
		int leftLimit = 48; // letter 'a'
		int rightLimit = 57; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();

		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		
		return buffer.toString();

	}
}
