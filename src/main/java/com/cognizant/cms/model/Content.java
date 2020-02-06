package com.cognizant.cms.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Content")
@XmlAccessorType(XmlAccessType.FIELD)
public class Content {

	@XmlAttribute(name = "Value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setStatus(String validateProviedr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Content [value=" + value + "]";
	}
	
}

	