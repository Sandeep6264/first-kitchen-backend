package com.nt.requestDTO;

import java.time.LocalDateTime;


public class JwtErrorResponseDTO {
	private int responseCode;
    private String responseContent;
    private String responseMessage;
    private String responseStatus;
    private LocalDateTime timestamp;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "JwtErrorResponseDTO [responseCode=" + responseCode + ", responseContent=" + responseContent
				+ ", responseMessage=" + responseMessage + ", responseStatus=" + responseStatus + ", timestamp="
				+ timestamp + "]";
	}
	public JwtErrorResponseDTO(int responseCode, String responseContent, String responseMessage, String responseStatus,
			LocalDateTime timestamp) {
		super();
		this.responseCode = responseCode;
		this.responseContent = responseContent;
		this.responseMessage = responseMessage;
		this.responseStatus = responseStatus;
		this.timestamp = timestamp;
	}
	public JwtErrorResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
