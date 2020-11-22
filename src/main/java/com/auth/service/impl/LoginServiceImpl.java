package com.auth.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth.exception.BusinessException;

@Service
public class LoginServiceImpl {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public UserDetails getLoggedUser() throws BusinessException {
		logger.info("UserDetails getLoggedUser()");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal() == null || "anonymousUser".equals(authentication.getPrincipal())) {
			throw new BusinessException("Have not logged user");
		}
		return (UserDetails) authentication.getPrincipal();
	}
}
