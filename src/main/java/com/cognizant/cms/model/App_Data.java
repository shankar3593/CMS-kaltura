package com.cognizant.cms.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "App_Data")
@XmlAccessorType(XmlAccessType.FIELD)
public class App_Data {

	@XmlAttribute(name = "Value")
	private String value;
	@XmlAttribute(name = "Name")
	private String name;
	@XmlAttribute(name = "App")
	private String app;


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getApp() {
		return app;
	}


	public void setApp(String app) {
		this.app = app;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App_Data other = (App_Data) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[getValue()=" + getValue() + ", getName()=" + getName() + ", getApp()=" + getApp() + "]\n";
	}

}
