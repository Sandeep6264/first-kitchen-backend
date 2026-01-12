package com.nt.responseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentOrderResponse {
	private boolean success;
    private String message;
    private String orderId;
    private String razorpayOrderId;
    private String currency;
    private String amount;
    private String razorpayKeyId;
    private String customerName;
    private String customerEmail;
    private String timestamp;
    
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRazorpayKeyId() {
		return razorpayKeyId;
	}
	public void setRazorpayKeyId(String razorpayKeyId) {
		this.razorpayKeyId = razorpayKeyId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "PaymentOrderResponse [success=" + success + ", message=" + message + ", orderId=" + orderId
				+ ", razorpayOrderId=" + razorpayOrderId + ", currency=" + currency + ", amount=" + amount
				+ ", razorpayKeyId=" + razorpayKeyId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", timestamp=" + timestamp + "]";
	}
	public PaymentOrderResponse(boolean success, String message, String orderId, String razorpayOrderId,
			String currency, String amount, String razorpayKeyId, String customerName, String customerEmail,
			String timestamp) {
		super();
		this.success = success;
		this.message = message;
		this.orderId = orderId;
		this.razorpayOrderId = razorpayOrderId;
		this.currency = currency;
		this.amount = amount;
		this.razorpayKeyId = razorpayKeyId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.timestamp = timestamp;
	}
	public PaymentOrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

}
