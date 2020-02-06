package com.cognizant.cms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permission {

	@JsonProperty("isView")
	private boolean isView;
	
	@JsonProperty("isCreate")
	private boolean isCreate;
	
	@JsonProperty("isModify")
	private boolean isModify;
	
	@JsonProperty("isDelete")
	private boolean isDelete;

	public Permission(boolean isView, boolean isCreate, boolean isModify, boolean isDelete) {
		this.isView  = isView;
		this.isCreate  = isCreate;
		this.isModify  = isModify;
		this.isDelete  = isDelete;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public boolean isModify() {
		return isModify;
	}

	public void setModify(boolean isModify) {
		this.isModify = isModify;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "\n Permission [isView()=" + isView() + ", isCreate()=" + isCreate() + ", isModify()=" + isModify()
				+ ", isDelete()=" + isDelete() + "]";
	}

	public Permission() {
		super();
	}
	

}
