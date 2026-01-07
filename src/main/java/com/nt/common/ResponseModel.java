package com.nt.common;

import java.time.LocalDateTime;

 
public class ResponseModel<T> {
	  private int responseCode;
	  private String responseMessage;
	  private T responseContent;
	  private LocalDateTime timestamp;
	  private String responseStatus;
	  public int getResponseCode() {
		  return responseCode;
	  }
	  public void setResponseCode(int responseCode) {
		  this.responseCode = responseCode;
	  }
	  public String getResponseMessage() {
		  return responseMessage;
	  }
	  public void setResponseMessage(String responseMessage) {
		  this.responseMessage = responseMessage;
	  }
	  public T getResponseContent() {
		  return responseContent;
	  }
	  public void setResponseContent(T responseContent) {
		  this.responseContent = responseContent;
	  }
	  public LocalDateTime getTimestamp() {
		  return timestamp;
	  }
	  public void setTimestamp(LocalDateTime timestamp) {
		  this.timestamp = timestamp;
	  }
	  public String getResponseStatus() {
		  return responseStatus;
	  }
	  public void setResponseStatus(String responseStatus) {
		  this.responseStatus = responseStatus;
	  }
	  @Override
	  public String toString() {
		return "ResponseModel [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseContent=" + responseContent + ", timestamp=" + timestamp + ", responseStatus="
				+ responseStatus + "]";
	  }
	  public ResponseModel(int responseCode, String responseMessage, T responseContent, LocalDateTime timestamp,
			String responseStatus) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseContent = responseContent;
		this.timestamp = timestamp;
		this.responseStatus = responseStatus;
	  }
	  public ResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	  }
	  
}


