package com.cognizant.cms.model;


public class Module {

	private String moduleName;
	private Permission permissions;

	public Module() {

	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Permission getPermissions() {
		return permissions;
	}

	public void setPermissions(Permission permissions) {
		this.permissions = permissions;
	}

	public Module(String moduleName, Permission permissions) {
		super();
		setModuleName(moduleName);
		setPermissions(permissions);
	}

	@Override
	public String toString() {
		return "\n Module [getModuleName()=" + getModuleName() + ", getPermissions()=" + getPermissions() + "]";
	}
	
	
	

}
