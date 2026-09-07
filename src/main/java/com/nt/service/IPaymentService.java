package com.nt.service;

import com.nt.request.PaymentOrderRequest;
import com.nt.request.PaymentVerificationRequest;
import com.nt.response.dto.PaymentOrderResponse;
import com.nt.response.dto.PaymentVerificationResponse;

public interface IPaymentService {
	PaymentOrderResponse createPaymentOrder(PaymentOrderRequest request);
    PaymentVerificationResponse verifyPayment(PaymentVerificationRequest request);
    PaymentVerificationResponse getPaymentStatus(String paymentId);
    void handleWebhook(String payload, String signature);
}
