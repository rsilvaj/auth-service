package com.auth.converter.impl;

import org.springframework.stereotype.Component;

import com.auth.converter.Converter;
import com.auth.dto.UserDTO;
import com.auth.model.User;

@Component
public class UserToUserDTOConverterImpl implements Converter<User, UserDTO> {

	@Override
	public UserDTO convertTo(User user) {
		UserDTO userDTO = new UserDTO();
		if(null != user) {
			userDTO.setEmail(user.getEmail());
			userDTO.setName(user.getName());
			userDTO.setId(user.getId());
			userDTO.setCreatedAt(user.getCreatedAt());
			userDTO.setUpdatedAt(user.getUpdatedAt());
			userDTO.setActive(user.isActive());
		}
		return userDTO;
	}

}
