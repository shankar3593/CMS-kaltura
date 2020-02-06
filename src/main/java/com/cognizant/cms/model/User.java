package com.cognizant.cms.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	public ObjectId _id;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]*", message = "length must be 6")
	private String userName;

	@NotBlank
	@Size(min =8 ,max = 12, message = "field should have atleast 8 characters, maximum length is 12 character")
	private String password;

	private List<Role> roles;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z]*" , message = "should have alphabets only")
	@Size(min =2 ,max = 12, message = "field should have atleast 2 characters, maximum length is 12 character")
	private String firstName;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]*", message = "should have alphabets only")
	@Size(min= 2, max = 12, message = "field should have atleast 2 characters, maximum length is 12 character")
	private String lastName;

	public String get_id() {
		return _id.toHexString();
	}

	public ObjectId gett_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public User(ObjectId _id, String userName, String password, List<Role> roles, String firstName, String lastName) {
		set_id(_id);
		setUserName(userName);
		setPassword(password);
		setRoles(roles);
		setFirstName(firstName);
		setLastName(lastName);
	}

	public User(String userName, String password, List<Role> roles, String firstName, String lastName) {
	
		setUserName(userName);
		setPassword(password);
		setRoles(roles);
		setFirstName(firstName);
		setLastName(lastName);
	}
	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;

	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	@Override
	public String toString() {
		return "User [getUserName()=" + getUserName() + ", getPassword()=" + getPassword() + ", \n getRoles()="
				+ getRoles() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + "]";
	}

}
