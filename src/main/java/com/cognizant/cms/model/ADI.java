package com.cognizant.cms.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ADI")
@XmlAccessorType(XmlAccessType.FIELD)
public class ADI {

	@XmlElement(name = "Metadata")
	private Metadata metadata;

	@XmlElement(name = "Asset")
	List<Asset> asset;


	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}


	public List<Asset> getAsset() {
		return asset;
	}


	public void setAsset(List<Asset> asset) {
		this.asset = asset;
	}

	@Override
	public String toString() {
		return "ADI [getMetadata()=" + getMetadata() + ", getAsset()=" + getAsset() + "]\n";
	}

}