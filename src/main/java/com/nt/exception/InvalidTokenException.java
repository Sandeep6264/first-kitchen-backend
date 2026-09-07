package com.nt.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends RuntimeException {
	private final int status;
	private final String errorCode;

	public InvalidTokenException(String message) {
		super(message);
		this.status = HttpStatus.NOT_FOUND.value();
		this.errorCode = "USER_NOT_FOUND";
	}

	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
		this.status = HttpStatus.NOT_FOUND.value();
		this.errorCode = "USER_NOT_FOUND";
	}

	public InvalidTokenException(String message, HttpStatus status, String errorCode) {
		super(message);
		this.status = status.value();
		this.errorCode = errorCode;
	}

	public int getStatus() {
		return status;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
