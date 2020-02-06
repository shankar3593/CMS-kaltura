package com.cognizant.cms.model;

import org.springframework.data.annotation.Id;

public class CustomField {

	private String className;
	private String name;
	private String displayname;
	private String datatype;
	private String fieldtype;
	@Id
	private String id;
	//private String asset_ID;
	private String title_id;




	//	public String getAsset_ID() {
	//		return asset_ID;
	//	}
	//	public void setAsset_ID(String asset_ID) {
	//		this.asset_ID = asset_ID;
	//	}

	public String getTitle_id() {
		return title_id;
	}

	public void setTitle_id(String title_id) {
		this.title_id = title_id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getId() {
		return id;
	}
	public void setId(String idd) {
		this.id = idd;
	}

	@Override
	public String toString() {
		return "CustomField [getTitle_id()=" + getTitle_id() + ", getClassName()=" + getClassName() + ", getName()="
				+ getName() + ", getDisplayname()=" + getDisplayname() + ", getDatatype()=" + getDatatype()
				+ ", getFieldtype()=" + getFieldtype() + ", getId()=" + getId() + "]";
	}


	public CustomField(String className, String name, String displayname, String datatype, String fieldtype, String id,
			String asset_ID) {
		super();
		this.className = className;
		this.name = name;
		this.displayname = displayname;
		this.datatype = datatype;
		this.fieldtype = fieldtype;
		this.id = id;
		this.title_id = title_id;
	}

	public CustomField() {
		super();

	}

}
