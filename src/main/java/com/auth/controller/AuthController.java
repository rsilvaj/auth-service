package com.auth.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.AuthDTO;
import com.auth.dto.TokenDTO;
import com.auth.exception.BusinessException;
import com.auth.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	AuthServiceImpl authServiceImpl;

	@PostMapping
	public TokenDTO auth(@RequestBody AuthDTO authDTO) throws BusinessException {
		logger.info(String.format("String auth(%s)", authDTO));
		return authServiceImpl.auth(authDTO);
	}
}
