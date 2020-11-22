package com.auth.validation.impl;

import org.springframework.stereotype.Component;

import com.auth.dto.UserDTO;
import com.auth.validation.Validation;

@Component
public class UserUpdationValidationImpl implements Validation<UserDTO> {

	@Override
	public boolean isValid(UserDTO userDTO) {
		return null != userDTO.getId();
	}

}
