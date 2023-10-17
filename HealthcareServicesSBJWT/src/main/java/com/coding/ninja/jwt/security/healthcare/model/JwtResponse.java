package com.coding.ninja.jwt.security.healthcare.model;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse {
	private String message;
	private String token;
	private String id;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
