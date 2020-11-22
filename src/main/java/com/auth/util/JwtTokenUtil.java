package com.auth.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth.dto.TokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Value("${app.secret}")
	private String secret;
	
	@Value("${app.token.validity-in-minutes}")
	private Long validityInMinutes;
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return (Claims) Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parse(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public TokenDTO generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private TokenDTO doGenerateToken(Map<String, Object> claims, String subject) {
		LocalDateTime expiration = LocalDateTime.now(ZoneId.systemDefault()).plusMinutes(validityInMinutes);
		GregorianCalendar gcExpiration = GregorianCalendar.from(ZonedDateTime.of(expiration, ZoneId.systemDefault()));
	    String token = Jwts.builder().setClaims(claims).setSubject(subject).setExpiration(gcExpiration.getTime()).signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512).compact();
		return new TokenDTO(token, expiration);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
