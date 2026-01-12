package com.nt.exception;

//PaymentException.java

import org.springframework.http.HttpStatus;

public class PaymentException extends RuntimeException {
 private final int status;
 private final String errorCode;
 
 public PaymentException(String message) {
     super(message);
     this.status = HttpStatus.BAD_REQUEST.value();
     this.errorCode = "PAYMENT_ERROR";
 }
 
 public PaymentException(String message, Throwable cause) {
     super(message, cause);
     this.status = HttpStatus.BAD_REQUEST.value();
     this.errorCode = "PAYMENT_ERROR";
 }
 
 public PaymentException(String message, HttpStatus status, String errorCode) {
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

 @Override
 public String toString() {
	return "PaymentException [status=" + status + ", errorCode=" + errorCode + "]";
 }

 public PaymentException(int status, String errorCode) {
	super();
	this.status = status;
	this.errorCode = errorCode;
 }



 
 
}