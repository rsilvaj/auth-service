package com.auth.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class StampUtil {
	
	public LocalDateTime now() {
		return LocalDateTime.now();
	}

}
