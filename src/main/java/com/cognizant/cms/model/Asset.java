package com.cognizant.cms.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Asset")
@XmlAccessorType(XmlAccessType.FIELD)
public class Asset {

	@XmlElement(name = "Metadata")
	private Metadata metadata;

	@XmlElement(name = "Asset")
	private List<Asset> asset;
	
	@XmlElement(name = "Content")
	private Content content;


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
	
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Asset [getMetadata()=" + getMetadata() + ", getAsset()=" + getAsset() + ", getContent()=" + getContent()
				+ "]";
	}

	


}
