package com.nt.requestDTO;

import jakarta.validation.constraints.NotBlank;

public class PaymentVerificationRequest {
	 @NotBlank(message = "Razorpay payment ID is required")
	    private String razorpayPaymentId;
	    
	    @NotBlank(message = "Razorpay order ID is required")
	    private String razorpayOrderId;
	    
	    @NotBlank(message = "Razorpay signature is required")
	    private String razorpaySignature;
	    
	    private String orderId;

		public String getRazorpayPaymentId() {
			return razorpayPaymentId;
		}

		public void setRazorpayPaymentId(String razorpayPaymentId) {
			this.razorpayPaymentId = razorpayPaymentId;
		}

		public String getRazorpayOrderId() {
			return razorpayOrderId;
		}

		public void setRazorpayOrderId(String razorpayOrderId) {
			this.razorpayOrderId = razorpayOrderId;
		}

		public String getRazorpaySignature() {
			return razorpaySignature;
		}

		public void setRazorpaySignature(String razorpaySignature) {
			this.razorpaySignature = razorpaySignature;
		}

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		@Override
		public String toString() {
			return "PaymentVerificationRequest [razorpayPaymentId=" + razorpayPaymentId + ", razorpayOrderId="
					+ razorpayOrderId + ", razorpaySignature=" + razorpaySignature + ", orderId=" + orderId + "]";
		}

		public PaymentVerificationRequest(
				@NotBlank(message = "Razorpay payment ID is required") String razorpayPaymentId,
				@NotBlank(message = "Razorpay order ID is required") String razorpayOrderId,
				@NotBlank(message = "Razorpay signature is required") String razorpaySignature, String orderId) {
			super();
			this.razorpayPaymentId = razorpayPaymentId;
			this.razorpayOrderId = razorpayOrderId;
			this.razorpaySignature = razorpaySignature;
			this.orderId = orderId;
		}

		public PaymentVerificationRequest() {
			super();
			// TODO Auto-generated constructor stub
		}	
	    
	    
}
