package com.auth.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.exception.BusinessException;
import com.auth.service.impl.LoginServiceImpl;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@PostMapping
	public UserDetails login() throws BusinessException {
		logger.info("UserDetails login()");
		return loginServiceImpl.getLoggedUser();
	}
	
}
