package com.auth.builder;

import com.auth.dto.UserDTO;

public class UserDTOBuilder extends Builder {

	private UserDTO userDTO;
	
	public UserDTOBuilder() {
		userDTO = new UserDTO();
	}

	public static UserDTOBuilder builder() {
		return new UserDTOBuilder();
	}
	
	public UserDTOBuilder filledFields() {
		userDTO.setId(1L);
		userDTO.setName("User One");
		userDTO.setEmail("email@email.com");
		userDTO.setPassword("1234");
		userDTO.setCreatedAt(this.getFirstJan());
		userDTO.setUpdatedAt(this.getFirstJan());
		return this;
	}
	
	public UserDTOBuilder withName(String name) {
		userDTO.setName(name);
		return this;
	}
	
	public UserDTOBuilder withEmail(String email) {
		userDTO.setEmail(email);
		return this;
	}
	
	public UserDTOBuilder withPassword(String password) {
		userDTO.setPassword(password);
		return this;
	}
	
	public UserDTO build() {
		return userDTO;		
	}
	
}