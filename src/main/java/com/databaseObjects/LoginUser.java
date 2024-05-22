package com.databaseObjects;

public class LoginUser {
	private String name;
	private String email;
	private String role;
	private boolean login;
	
	
	
	public LoginUser(String name, String email, String role, boolean login) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.login = login;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	
}
