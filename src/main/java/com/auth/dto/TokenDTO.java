package com.auth.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;
	
	private LocalDateTime expiration;
	
	
	public TokenDTO(String token, LocalDateTime expiration) {
		super();
		this.token = token;
		this.expiration = expiration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return String.format("TokenDTO [token=%s, expiration=%s]", token, expiration);
	}

}