package com.cognizant.cms.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AMS")
@XmlAccessorType(XmlAccessType.FIELD)
public class AMS {

	@XmlAttribute(name = "Asset_ID")
	private String asset_ID;

	@XmlAttribute(name = "Asset_Name")
	private String asset_Name;

	@XmlAttribute(name = "Product")
	private String product;

	@XmlAttribute(name = "Provider_ID")
	private String provider_ID;

	@XmlAttribute(name = "Version_Minor")
	private String version_Minor;

	@XmlAttribute(name = "Version_Major")
	private String version_Major;

	@XmlAttribute(name = "Provider")
	private String provider;

	@XmlAttribute(name = "Description")
	private String description;

	@XmlAttribute(name = "Creation_Date")
	private String creation_Date;

	@XmlAttribute(name = "Asset_Class")
	private String asset_Class;



	public String getAsset_ID() {
		return asset_ID;
	}

	public void setAsset_ID(String asset_ID) {
		this.asset_ID = asset_ID;
	}

	public String getAsset_Name() {
		return asset_Name;
	}

	public void setAsset_Name(String asset_Name) {
		this.asset_Name = asset_Name;
	}

	public String getVersion_Minor() {
		return version_Minor;
	}

	public void setVersion_Minor(String version_Minor) {
		this.version_Minor = version_Minor;
	}

	public String getVersion_Major() {
		return version_Major;
	}

	public void setVersion_Major(String version_Major) {
		this.version_Major = version_Major;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreation_Date() {
		return creation_Date;
	}

	public void setCreation_Date(String creation_Date) {
		this.creation_Date = creation_Date;
	}

	public String getAsset_Class() {
		return asset_Class;
	}

	public void setAsset_Class(String asset_Class) {
		this.asset_Class = asset_Class;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getProvider_ID() {
		return provider_ID;
	}


	public void setProvider_ID(String provider_ID) {
		this.provider_ID = provider_ID;
	}

	@Override
	public String toString() {
		return "AMS [getAsset_ID()=" + getAsset_ID() + ", getAsset_Name()=" + getAsset_Name() + ", getVersion_Minor()="
				+ getVersion_Minor() + ", getVersion_Major()=" + getVersion_Major() + ", getProvider()=" + getProvider()
				+ ", getDescription()=" + getDescription() + ", getCreation_Date()=" + getCreation_Date()
				+ ", getAsset_Class()=" + getAsset_Class() + ", getProduct()=" + getProduct() + ", getProvider_ID()="
				+ getProvider_ID() + "]";
	}



}
