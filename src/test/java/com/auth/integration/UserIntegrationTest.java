package com.auth.integration;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth.dto.UserDTO;
import com.auth.exception.BusinessException;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.service.impl.UserServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserIntegrationTest {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UserRepository userRepository;
	
	private void createUser()  {
		String email = "new_email@email.com";
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO.setName("NEW PERSON");
		userDTO.setPassword("12345678");
		try {
			UserDTO returnedUserDTO = userServiceImpl.create(userDTO);
			Assert.assertNotNull(returnedUserDTO.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
		}		
	}
	

	@Test
	public void testFindById() {
		createUser();
		try {
			Optional<User> optionalUser = userRepository.findById(1L);
			Assert.assertTrue(optionalUser.isPresent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInactivateAndActivateUser() {
		createUser();
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(1L);
			Assert.assertTrue(userServiceImpl.inactivate(userDTO));
			UserDTO userDTOChecking = userServiceImpl.findById(1L);
			Assert.assertFalse(userDTOChecking.isActive());
			Assert.assertTrue(userServiceImpl.activate(userDTO));
			userDTOChecking = userServiceImpl.findById(1L);
			Assert.assertTrue(userDTOChecking.isActive());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListUsers() {
		createUser();
		try {
			Long count = userRepository.count();
			List<UserDTO> usersActived = userServiceImpl.findAllActived();
			List<UserDTO> usersInactived = userServiceImpl.findAllInactived();
			
			Assert.assertNotNull(usersActived);
			Assert.assertNotNull(usersInactived);
			Assert.assertEquals(count.intValue(), usersActived.size() + usersInactived.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
