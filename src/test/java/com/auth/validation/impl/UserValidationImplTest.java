package com.auth.validation.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth.builder.UserDTOBuilder;
import com.auth.dto.UserDTO;

@RunWith(SpringRunner.class)
public class UserValidationImplTest {

	@InjectMocks
	UserCreationValidationImpl creationValidationImpl; 
	
	@Test
	public void testIsInvalidNull() {
		Assert.assertFalse(creationValidationImpl.isValid(null));
	}
	
	@Test
	public void testIsInvalidBlankName() {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().withName("").build();		
		Assert.assertFalse(creationValidationImpl.isValid(userDTO));
	}
	
	@Test
	public void testIsInvalidBlankEmail() {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().withEmail("").build();
		Assert.assertFalse(creationValidationImpl.isValid(userDTO));
	}
	
	@Test
	public void testIsInvalidBlankPassword() {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().withPassword("").build();
		Assert.assertFalse(creationValidationImpl.isValid(userDTO));
	}
	
	@Test
	public void testIsValid() {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().build();
		Assert.assertTrue(creationValidationImpl.isValid(userDTO));
	}

}
