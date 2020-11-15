package com.backend.todolist.auth.controller;

public class UserSigninRequest {
    private String username;
    
    private String password;
    
    protected UserSigninRequest() {
    	
    }

	public UserSigninRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
