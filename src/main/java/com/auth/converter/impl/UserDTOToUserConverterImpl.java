package com.auth.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth.converter.Converter;
import com.auth.dto.UserDTO;
import com.auth.model.User;

@Component
public class UserDTOToUserConverterImpl implements Converter<UserDTO, User> {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User convertTo(UserDTO userDTO) {
		User user = new User();
		if(null != userDTO) {
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setCreatedAt(userDTO.getCreatedAt());
			user.setUpdatedAt(userDTO.getUpdatedAt());
		}
		return user;
	}

}