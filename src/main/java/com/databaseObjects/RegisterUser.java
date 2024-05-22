package com.databaseObjects;

public class RegisterUser {
	private String name;
	private String email;
	private String password;
	private String confPassword;
	private String role;
	
	public RegisterUser(String name, String email, String password, String confPassword, String role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confPassword = confPassword;
		this.role = role;
	}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
