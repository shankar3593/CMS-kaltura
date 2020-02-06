package com.cognizant.cms.model;

public class TargetedTitles {
	
	private String titleId;
	private String status;
	private String thumbnail;
	private String statusTime;
	private String title;
	private String assetId;
	private String majorVersion;
	private String licenseStart;
	private String licenseEnd;
	
	public TargetedTitles() {
		super();
	}

	public TargetedTitles(String titleId, String status, String thumbnail, String statusTime, String title,
			String assetId, String majorVersion, String licenseStart, String licenseEnd) {
		super();
		this.titleId = titleId;
		this.status = status;
		this.thumbnail = thumbnail;
		this.statusTime = statusTime;
		this.title = title;
		this.assetId = assetId;
		this.majorVersion = majorVersion;
		this.licenseStart = licenseStart;
		this.licenseEnd = licenseEnd;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public String getLicenseStart() {
		return licenseStart;
	}

	public void setLicenseStart(String licenseStart) {
		this.licenseStart = licenseStart;
	}

	public String getLicenseEnd() {
		return licenseEnd;
	}

	public void setLicenseEnd(String licenseEnd) {
		this.licenseEnd = licenseEnd;
	}

	@Override
	public String toString() {
		return "TargetedTitles [getTitleId()=" + getTitleId() + ", getStatus()=" + getStatus() + ", getThumbnail()="
				+ getThumbnail() + ", getStatusTime()=" + getStatusTime() + ", getTitle()=" + getTitle()
				+ ", getAssetId()=" + getAssetId() + ", getMajorVersion()=" + getMajorVersion() + ", getLicenseStart()="
				+ getLicenseStart() + ", getLicenseEnd()=" + getLicenseEnd() + "]";
	}
	
	
	
	
	

}
