package com.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

	private String name;

	@Column(unique = true)
	private String email;
    
	private String password;
	
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

	@Override
	public String toString() {
		return String.format(
				"User [getName()=%s, getEmail()=%s, getPassword()=%s, getId()=%s, isActive()=%s, isInactive()=%s, getCreatedAt()=%s, getUpdatedAt()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				getName(), getEmail(), "", getId(), isActive(), isInactive(), getCreatedAt(), getUpdatedAt(),
				getClass(), hashCode(), super.toString());
	}


}