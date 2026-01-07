package com.nt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.util.ResponseUtil;

@RestControllerAdvice
public class GlobleException {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentException(IllegalArgumentException ie){
		return ResponseUtil.error(404, ie.getMessage());
	}
	@ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(org.springframework.security.authentication.BadCredentialsException bce){
		return ResponseUtil.error(401, "Invalid username/password");
	}
}
