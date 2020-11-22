package com.auth.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dto.AuthDTO;
import com.auth.dto.TokenDTO;
import com.auth.exception.BusinessException;
import com.auth.util.JwtTokenUtil;

@Service
public class AuthServiceImpl implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${app.messages.auth-failed}")
	private String authFailed;

	@Value("${app.messages.auth-bad-credentials}")
	private String authBadCredentials;

	public TokenDTO auth(AuthDTO authDTO) throws BusinessException {
		logger.info(String.format("TokenDTO auth( %s)", authDTO));
		if (passwordEncoder.matches(authDTO.getPassword(),
				userServiceImpl.findPasswordByEmail(authDTO.getUsername()))) {
			this.authenticate(authDTO.getUsername(), authDTO.getPassword());
			UserDetails userDetails = this.loadUserByUsername(authDTO.getUsername());
			return jwtTokenUtil.generateToken(userDetails);
		}
		return new TokenDTO(authFailed, LocalDateTime.now());
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		logger.info(String.format("UserDetails loadUserByUsername( %s)", username));
		return buildUserDetails(username, userServiceImpl.findPasswordByEmail(username));
	}

	private UserDetails buildUserDetails(String username, String password) {
		return new User(username, password, Collections.emptyList());
	}

	private void authenticate(String username, String password) throws BusinessException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException | BadCredentialsException e) {
			e.printStackTrace();
			throw new BusinessException(authBadCredentials);
		}
	}

}
