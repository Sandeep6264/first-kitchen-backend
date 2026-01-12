package com.nt.responseDTO;

import java.time.LocalDateTime;

public class PaymentVerificationResponse {
	   private boolean success;
	    private String message;
	    private String paymentId;
	    private String orderId;
	    private String status;
	    private String amount;
	    private String currency;
	    private LocalDateTime paymentDate;
	    private String customerEmail;
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
		public String getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public LocalDateTime getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(LocalDateTime paymentDate) {
			this.paymentDate = paymentDate;
		}
		public String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}
		@Override
		public String toString() {
			return "PaymentVerificationResponse [success=" + success + ", message=" + message + ", paymentId="
					+ paymentId + ", orderId=" + orderId + ", status=" + status + ", amount=" + amount + ", currency="
					+ currency + ", paymentDate=" + paymentDate + ", customerEmail=" + customerEmail + "]";
		}
		public PaymentVerificationResponse(boolean success, String message, String paymentId, String orderId,
				String status, String amount, String currency, LocalDateTime paymentDate, String customerEmail) {
			super();
			this.success = success;
			this.message = message;
			this.paymentId = paymentId;
			this.orderId = orderId;
			this.status = status;
			this.amount = amount;
			this.currency = currency;
			this.paymentDate = paymentDate;
			this.customerEmail = customerEmail;
		}
		public PaymentVerificationResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
