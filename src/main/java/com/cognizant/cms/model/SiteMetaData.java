package com.cognizant.cms.model;

import java.util.List;

public class SiteMetaData {
	
	private String siteId;
	private List<Metadata> metadata;
	//private Metadata metadata;
	
	public String getSiteId() {
		return siteId;
	}
	
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	public List<Metadata> getMetadata() {
		return metadata;
	}
	
	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}
	
	public SiteMetaData(String siteId, List<Metadata> metadata) {
		super();
		this.siteId = siteId;
		this.metadata = metadata;
	}

	public SiteMetaData() {
		super();
	}

	@Override
	public String toString() {
		return "SiteMetaData [getSiteId()=" + getSiteId() + ", getMetadata()=" + getMetadata() + "]";
	}
	
	
	
	
		
	
	
	

}
