package com.nt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.requestDTO.PaymentOrderRequest;
import com.nt.requestDTO.PaymentVerificationRequest;
import com.nt.responseDTO.PaymentOrderResponse;
import com.nt.responseDTO.PaymentVerificationResponse;
import com.nt.service.IPaymentService;
import com.nt.util.ResponseUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/payments")

public class PaymentController {
	@Autowired
	private  IPaymentService paymentService;
	
	private static final Logger log=LoggerFactory.getLogger(PaymentController.class);
	
	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(
            @Valid @RequestBody PaymentOrderRequest request) {
        
        log.info("Received request to create payment order for receipt: {}", 
                request.getReceipt());
        
        PaymentOrderResponse response = paymentService.createPaymentOrder(request);
        return ResponseUtil.success(response, "Payment created successfully");
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	@PostMapping("/verify")
	public ResponseEntity<?> verifyPayment(
            @Valid @RequestBody PaymentVerificationRequest request) {
        
        log.info("Verifying payment for order: {}", request.getRazorpayOrderId());
        
        PaymentVerificationResponse response = paymentService.verifyPayment(request);
        
        return ResponseUtil.success(response, "Payment Verified successfully");
    }
	 @GetMapping("/status/{paymentId}")
	 public ResponseEntity<?> getPaymentStatus(
	            @PathVariable String paymentId) {
	        
	        log.info("Getting payment status for: {}", paymentId);
	        
	        PaymentVerificationResponse response = paymentService.getPaymentStatus(paymentId);
	        return ResponseUtil.success(response, "Payment Status checked");
	    }
	 @PostMapping("/webhook")
	 public ResponseEntity<?> handleWebhook(
	            @RequestBody String payload,
	            @RequestHeader("X-Razorpay-Signature") String signature) {
	        
	        log.info("Received webhook with signature: {}", signature);
	        
	        paymentService.handleWebhook(payload, signature);
	        
	        return ResponseUtil.success(null, "Webhook processed successfully");
	    }
}
