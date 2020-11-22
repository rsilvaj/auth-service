package com.auth.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.UserDTO;
import com.auth.exception.BusinessException;
import com.auth.exception.ConverterException;
import com.auth.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping
	public List<UserDTO> listAll() throws ConverterException{
		logger.info("List<UserDTO> listAll()");
		return userServiceImpl.findAllActived();
	}
	
	@PostMapping
	public UserDTO create(@RequestBody UserDTO userDTO) throws BusinessException{
		logger.info(String.format("UserDTO create( %s)", userDTO));
		return userServiceImpl.create(userDTO);
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO findById(@PathVariable("id") Long id) throws BusinessException {
		logger.info(String.format("UserDTO findById( %s)", id));
		return userServiceImpl.findById(id);
	}
	
	@PutMapping
	public UserDTO update(@RequestBody UserDTO userDTO) throws BusinessException {
		logger.info(String.format("UserDTO update( %s)", userDTO));
		return userServiceImpl.update(userDTO);
	}
	
	@PutMapping("/activate")
	public Boolean activate(@RequestBody UserDTO userDTO) throws BusinessException {
		logger.info(String.format("Boolean activate( %s)", userDTO));
		return userServiceImpl.activate(userDTO);
	}
	
	@PutMapping("/inactivate")
	public Boolean inactivate(@RequestBody UserDTO userDTO) throws BusinessException {
		logger.info(String.format("Boolean inactivate( %s)", userDTO));
		return userServiceImpl.inactivate(userDTO);
	}

}
