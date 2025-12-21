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
}
