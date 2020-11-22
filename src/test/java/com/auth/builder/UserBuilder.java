package com.auth.builder;

import com.auth.model.User;

public class UserBuilder extends Builder {
	
	private User user;
	
	public UserBuilder() {
		user = new User();
	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public UserBuilder filledFields() {
		user.setId(1L);
		user.setName("User One");
		user.setEmail("email@email.com");
		user.setPassword("1234");
		user.setCreatedAt(this.getFirstJan());
		user.setUpdatedAt(this.getFirstJan());
		return this;
	}
	
	public User build() {
		return user;		
	}

}
