package com.cognizant.cms.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Role {

	@JsonIgnore
	@Id
	public ObjectId _id;

	@NotBlank
	private String roleName;
	
	@NotBlank
	private String description;
	
	private List<Module> modules;
	

	public Role(String roleName, String description, List<Module> modules) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.modules = modules;
	}

	public String get_id() {
		return _id.toHexString();
	}

	public ObjectId gett_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Role() {
		super();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "\n Role [getModules()=" + getModules() + ", getRoleName()=" + getRoleName() + ", getDescription()="
				+ getDescription() + "]";
	}

	
}
