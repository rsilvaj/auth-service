package com.auth.converter.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth.builder.UserDTOBuilder;
import com.auth.dto.UserDTO;
import com.auth.model.User;

@RunWith(SpringRunner.class)
public class UserDTOToUserConverterImplTest {

	@InjectMocks
	UserDTOToUserConverterImpl userDTOtoUserConverterImpl;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testConversionNull() {
		User user = userDTOtoUserConverterImpl.convertTo(null);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testConversion() {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().build();
		String passwordHash = "${BCRYPT}";
		Mockito.when(passwordEncoder.encode(userDTO.getPassword())).thenReturn(passwordHash);
		User user = userDTOtoUserConverterImpl.convertTo(userDTO);
		Assert.assertEquals(userDTO.getId(), user.getId());
		Assert.assertEquals(userDTO.getName(), user.getName());
		Assert.assertEquals(userDTO.getEmail(), user.getEmail());
		Assert.assertEquals(passwordHash, user.getPassword());
		Assert.assertEquals(userDTO.getCreatedAt(), user.getCreatedAt());
		Assert.assertEquals(userDTO.getUpdatedAt(), user.getUpdatedAt());
	}

}
