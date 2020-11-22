package com.auth.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.service.impl.AuthServiceImpl;
import com.auth.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
	
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private AuthServiceImpl jwtUserDetailsServiceImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.debug("void doFilterInternal(request, response, filter)");
		String requestTokenHeader = request.getHeader("Authorization");
		if (null != requestTokenHeader && requestTokenHeader.startsWith("Bearer ")) {
			try {
				String token = requestTokenHeader.substring(7);
				String username = jwtTokenUtil.getUsernameFromToken(token);
				UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
				if (jwtTokenUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				} else {
					logger.debug(String.format("void doFilterInternal(request, response, filter): invalid token %s", token));
				}
			} catch (MalformedJwtException | SignatureException | IllegalArgumentException | ExpiredJwtException e ) {
				logger.error(String.format("void doFilterInternal(request, response, filter): error message get username %s", e.getMessage()));
				e.printStackTrace();
			}
			
		} else {
			logger.debug(String.format("void doFilterInternal(request, response, filter): Token bad format %s", requestTokenHeader));
		}
		chain.doFilter(request, response);
	}

}