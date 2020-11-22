package com.auth.dto;

import java.io.Serializable;

public class AuthDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public AuthDTO() {
	}

	public AuthDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("AuthDTO [username=%s, password=%s]", username, "");
	}
	
}