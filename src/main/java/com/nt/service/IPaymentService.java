package com.nt.service;

import com.nt.requestDTO.PaymentOrderRequest;
import com.nt.requestDTO.PaymentVerificationRequest;
import com.nt.responseDTO.PaymentOrderResponse;
import com.nt.responseDTO.PaymentVerificationResponse;

public interface IPaymentService {
	PaymentOrderResponse createPaymentOrder(PaymentOrderRequest request);
    PaymentVerificationResponse verifyPayment(PaymentVerificationRequest request);
    PaymentVerificationResponse getPaymentStatus(String paymentId);
    void handleWebhook(String payload, String signature);
}
