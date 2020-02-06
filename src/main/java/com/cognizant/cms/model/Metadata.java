package com.cognizant.cms.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {


	private String title_id;

	@XmlElement(name = "AMS")
	private AMS ams;

	@XmlElement(name = "App_Data")
	private List<App_Data> app_Data;

	private List<CustomField> customFields;

	private String status;

	private String reason;

	private Content contentType;

	public Metadata() {
		super();
	}

	public Metadata(String title_id, AMS ams, List<App_Data> app_Data, List<CustomField> customFields, String status,
			String reason, Content contentType) {
		super();
		this.title_id = title_id;
		this.ams = ams;
		this.app_Data = app_Data;
		this.customFields = customFields;
		this.status = status;
		this.reason = reason;
		this.contentType = contentType;
	}

	public String getTitle_id() {
		return title_id;
	}

	public void setTitle_id(String title_id) {
		this.title_id = title_id;
	}

	public List<CustomField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}

	public List<App_Data> getApp_Data() {
		return app_Data;
	}

	public void setApp_Data(List<App_Data> app_Data) {
		this.app_Data = app_Data;
	}

	public AMS getAms() {
		return ams;
	}

	public void setAms(AMS ams) {
		this.ams = ams;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Content getContentType() {
		return contentType;
	}

	public void setContentType(Content content) {
		this.contentType = content;
	}

	@Override
	public String toString() {
		return "Metadata [getTitle_id()=" + getTitle_id() + ", getCustomFields()=" + getCustomFields()
				+ ", getApp_Data()=" + getApp_Data() + ", getAms()=" + getAms() + ", getReason()=" + getReason()
				+ ", getStatus()=" + getStatus() + ", getContentType()=" + getContentType() + "]";
	}

}
