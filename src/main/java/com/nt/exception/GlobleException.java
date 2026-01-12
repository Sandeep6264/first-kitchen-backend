package com.nt.exception;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobleException {
	 private static final Logger logger=LoggerFactory.getLogger(GlobleException.class);
	 
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentException(IllegalArgumentException ie){
		return ResponseUtil.error(404, ie.getMessage());
	}
	
	@ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(org.springframework.security.authentication.BadCredentialsException bce){
		return ResponseUtil.error(401, "Invalid username/password");
	}
	
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException(
            PaymentException ex, HttpServletRequest request) {
        logger.error("Payment exception occurred: {}", ex.getMessage(), ex);
        return ResponseUtil.error(ex.getStatus(), ex.getMessage(),Map.of("error",ex.getErrorCode(),"path",String.valueOf(request.getRequestURI())));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(),"Validation failed",Map.of("error",errors.toString()));

    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGenericException Exception ex, HttpServletRequest request) ;
//        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
//        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"An unexpected error occurred",Map.of("error",ex.getMessage()));
//        
//    }

}
