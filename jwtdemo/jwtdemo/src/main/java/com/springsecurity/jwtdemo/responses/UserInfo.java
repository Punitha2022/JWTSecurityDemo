package com.springsecurity.jwtdemo.responses;

public class UserInfo {
	private String firstName;
	private String lastName;
	private String email;
	
	private Object roles;

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

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object getRoles() {
		return roles;
	}

	public void setRoles(Object roles) {
		this.roles = roles;
	}
}
