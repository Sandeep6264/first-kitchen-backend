package com.nt.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nt.common.ResponseModel;

public class ResponseUtil {
	public static <T> ResponseEntity<ResponseModel<T>> success(T content, String message) {
		ResponseModel<T> responseModel = new ResponseModel<T>(200, message, content, LocalDateTime.now(), "S");
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}

	public static <T> ResponseEntity<ResponseModel<T>> error(int code, String message) {
		ResponseModel<T> body = new ResponseModel<>(code, message, null, LocalDateTime.now(), "F");
		return new ResponseEntity<>(body, HttpStatus.valueOf(code));
	}

	public static <T> ResponseEntity<ResponseModel<T>> validationError(String message) {
		ResponseModel<T> body = new ResponseModel<>(400, message, null, LocalDateTime.now(), "F");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	public static <T> ResponseEntity<ResponseModel<T>> unauthorized(String message) {
		ResponseModel<T> body = new ResponseModel<>(401, message, null, LocalDateTime.now(), "F");
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

}
