package com.auth.service.impl;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth.builder.UserBuilder;
import com.auth.builder.UserDTOBuilder;
import com.auth.converter.impl.UserDTOToUserConverterImpl;
import com.auth.converter.impl.UserToUserDTOConverterImpl;
import com.auth.dto.UserDTO;
import com.auth.exception.BusinessException;
import com.auth.exception.ConverterException;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.util.StampUtil;
import com.auth.validation.impl.UserCreationValidationImpl;
import com.auth.validation.impl.UserUpdationValidationImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserCreationValidationImpl userCreationValidationImpl;
	
	@Mock
	UserUpdationValidationImpl userUpdationValidationImpl;
	
	@Mock
	UserDTOToUserConverterImpl userDTOtoUserConverterImpl;
	
	@Mock
	UserToUserDTOConverterImpl userToUserDTOConverterImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	StampUtil stampUtil;
	
	@Before
	public void before() {
		Mockito.when(stampUtil.now()).thenReturn(LocalDateTime.of(2020, 1, 1, 12, 0));
	}
	
	
	@Test
	public void testCreateUserNull() throws BusinessException {
		Mockito.when(userCreationValidationImpl.isValid(null)).thenReturn(false);
		Assert.assertThrows(BusinessException.class, () -> userServiceImpl.create(null));
	}
	
	@Test
	public void testCreateUserInvalid() throws BusinessException {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().build();
		Mockito.when(userCreationValidationImpl.isValid(userDTO)).thenReturn(false);
		Assert.assertThrows(BusinessException.class, () -> userServiceImpl.create(userDTO));
	}
	
	@Test
	public void testCreateUserValidNotSavedDataIntegrityViolationException() throws BusinessException {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().build();
		User user = UserBuilder.builder().build();
		
		Mockito.when(userCreationValidationImpl.isValid(userDTO)).thenReturn(true);
		Mockito.when(userDTOtoUserConverterImpl.convertTo(userDTO)).thenReturn(user);
		Mockito.when(userRepository.save(user)).thenThrow(new DataIntegrityViolationException("fake error"));
		Assert.assertThrows(BusinessException.class, () -> userServiceImpl.create(userDTO));
	}
	
	@Test
	public void testCreateUserSuccess() throws BusinessException, ConverterException {
		UserDTO userDTO = UserDTOBuilder.builder().filledFields().build();
		User user = UserBuilder.builder().filledFields().build();
		Mockito.when(userCreationValidationImpl.isValid(userDTO)).thenReturn(true);
		Mockito.when(userDTOtoUserConverterImpl.convertTo(userDTO)).thenReturn(user);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userToUserDTOConverterImpl.convertTo(user)).thenReturn(userDTO);
		Assert.assertNotNull(userServiceImpl.create(userDTO).getId());
	}

}
