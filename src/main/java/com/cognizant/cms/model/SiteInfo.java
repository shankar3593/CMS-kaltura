package com.cognizant.cms.model;

public class SiteInfo {
	
	private String siteId;
	private String name;
	private String siteType;
	private String externalId;
	private String description;
	private String metadataFormat;
	private String distributionOption;
	
	public SiteInfo() {
		super();
	}

	public SiteInfo(String siteId, String name, String siteType, String externalId, String description, String metadataFormat,
			String distributionOption) {
		super();
		this.siteId = siteId;
		this.name = name;
		this.siteType = siteType;
		this.externalId = externalId;
		this.description = description;
		this.metadataFormat = metadataFormat;
		this.distributionOption = distributionOption;
	}
	
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMetadataFormat() {
		return metadataFormat;
	}

	public void setMetadataFormat(String metadataFormat) {
		this.metadataFormat = metadataFormat;
	}

	public String getDistributionOption() {
		return distributionOption;
	}

	public void setDistributionOption(String distributionOption) {
		this.distributionOption = distributionOption;
	}

	@Override
	public String toString() {
		return "SiteInfo [getSiteId()=" + getSiteId() + ", getName()=" + getName() + ", getSiteType()=" + getSiteType()
				+ ", getExternalId()=" + getExternalId() + ", getDescription()=" + getDescription()
				+ ", getMetadataFormat()=" + getMetadataFormat() + ", getDistributionOption()="
				+ getDistributionOption() + "]";
	}

	

	
	
	
	
	
	

}
