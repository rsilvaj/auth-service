package com.auth.validation.impl;

import org.springframework.stereotype.Component;

import com.auth.dto.UserDTO;
import com.auth.validation.Validation;

@Component
public class UserCreationValidationImpl implements Validation<UserDTO> {

	@Override
	public boolean isValid(UserDTO userDTO) {
		if(null == userDTO) {
			return false;
		}
		return !userDTO.getName().isBlank() && !userDTO.getEmail().isBlank() && !userDTO.getPassword().isBlank();
	}
	
}