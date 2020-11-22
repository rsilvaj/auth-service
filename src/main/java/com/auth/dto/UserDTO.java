package com.auth.dto;

import java.time.LocalDateTime;

public class UserDTO extends BaseDTO {

	private String name;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean active;
	
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return String.format(
				"UserDTO [getName()=%s, getEmail()=%s, getPassword()=%s, getCreatedAt()=%s, getUpdatedAt()=%s, isActive()=%s, getId()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				getName(), getEmail(), getPassword(), getCreatedAt(), getUpdatedAt(), isActive(), getId(), getClass(),
				hashCode(), super.toString());
	}

	

}