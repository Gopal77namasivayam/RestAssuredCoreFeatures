package com.rest.api.POST;
// create   class private variable
// generate getter and setter for those variable
//generate constructor for  those variable

public class Users {
	
	// class variables
	private String name;
	private String email;
	private String gender;
	private String status;
	
	
	// getters and setters
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

		
	
	
	//Constructors
	public Users(String name, String email, String gender, String status) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
	//getters and setters
	

}