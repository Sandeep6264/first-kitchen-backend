//package com.nt.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.context.properties.bind.Nested;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import com.nt.exception.PaymentException;
//import com.nt.requestDTO.PaymentOrderRequest;
//import com.nt.requestDTO.PaymentVerificationRequest;
//import com.nt.responseDTO.PaymentOrderResponse;
//import com.nt.responseDTO.PaymentVerificationResponse;
//import com.nt.service.PaymentServiceImpl;
//import com.nt.util.RazorpaySignatureUtil;
//import com.razorpay.Order;
//import com.razorpay.Payment;
//
////PaymentServiceTest.java - COMPLETE
//
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("Payment Service Tests")
//class PaymentServiceTest {
// 
// @Mock
// private RazorpayClient razorpayClient;
// 
// @Mock
// private RazorpaySignatureUtil signatureUtil;
// 
// @InjectMocks
// private PaymentServiceImpl paymentService;
// 
// @Mock
// private Order razorpayOrder;
// 
// @Mock
// private Payment razorpayPayment;
// 
// @BeforeEach
// void setUp() {
//     ReflectionTestUtils.setField(paymentService, "razorpayKeyId", "test_key_id");
//     ReflectionTestUtils.setField(paymentService, "razorpayKeySecret", "test_secret");
// }
// 
// @Nested
// @DisplayName("Create Payment Order Tests")
// class CreatePaymentOrderTests {
//     
//     @Test
//     @DisplayName("Should create payment order successfully")
//     void createPaymentOrder_Success() throws RazorpayException {
//         // Given
//         PaymentOrderRequest request = createMockOrderRequest();
//         Map<String, Object> orderResponse = createMockOrderResponseMap();
//         
//         when(razorpayClient.orders.create(any(JSONObject.class)))
//             .thenReturn(razorpayOrder);
//         when(razorpayOrder.get("id")).thenReturn("order_12345");
//         when(razorpayOrder.get("amount")).thenReturn(10000);
//         when(razorpayOrder.get("currency")).thenReturn("INR");
//         when(razorpayOrder.get("receipt")).thenReturn("order_001");
//         
//         // When
//         PaymentOrderResponse response = paymentService.createPaymentOrder(request);
//         
//         // Then
//         assertNotNull(response);
//         assertTrue(response.isSuccess());
//         assertEquals("Order created successfully", response.getMessage());
//         assertEquals("order_001", response.getOrderId());
//         assertEquals("order_12345", response.getRazorpayOrderId());
//         assertEquals("INR", response.getCurrency());
//         assertEquals("100.00", response.getAmount());
//         assertEquals("test_key_id", response.getRazorpayKeyId());
//         assertEquals("John Doe", response.getCustomerName());
//         assertEquals("john@example.com", response.getCustomerEmail());
//         
//         verify(razorpayClient.orders, times(1)).create(any(JSONObject.class));
//     }
//     
//     @Test
//     @DisplayName("Should throw exception when Razorpay API fails")
//     void createPaymentOrder_RazorpayException() throws RazorpayException {
//         // Given
//         PaymentOrderRequest request = createMockOrderRequest();
//         
//         when(razorpayClient.orders.create(any(JSONObject.class)))
//             .thenThrow(new RazorpayException("API Error"));
//         
//         // When & Then
//         PaymentException exception = assertThrows(PaymentException.class, 
//             () -> paymentService.createPaymentOrder(request));
//         
//         assertEquals("Failed to create payment order: API Error", exception.getMessage());
//         assertEquals(500, exception.getStatusCode());
//     }
//     
//     @Test
//     @DisplayName("Should handle large amount correctly")
//     void createPaymentOrder_LargeAmount() throws RazorpayException {
//         // Given
//         PaymentOrderRequest request = createMockOrderRequest();
//         request.setAmount(new BigDecimal("999999.99")); // Large amount
//         
//         when(razorpayClient.orders.create(any(JSONObject.class)))
//             .thenReturn(razorpayOrder);
//         when(razorpayOrder.get("id")).thenReturn("order_67890");
//         when(razorpayOrder.get("amount")).thenReturn(99999999);
//         when(razorpayOrder.get("currency")).thenReturn("INR");
//         when(razorpayOrder.get("receipt")).thenReturn("order_002");
//         
//         // When
//         PaymentOrderResponse response = paymentService.createPaymentOrder(request);
//         
//         // Then
//         assertNotNull(response);
//         assertEquals("order_67890", response.getRazorpayOrderId());
//         assertEquals("999999.99", response.getAmount());
//     }
// }
// 
// @Nested
// @DisplayName("Verify Payment Tests")
// class VerifyPaymentTests {
//     
//     @Test
//     @DisplayName("Should verify payment successfully")
//     void verifyPayment_Success() throws RazorpayException {
//         // Given
//         PaymentVerificationRequest request = createMockVerificationRequest();
//         Map<String, Object> paymentData = createMockPaymentData();
//         
//         when(signatureUtil.verifySignature(anyString(), anyString()))
//             .thenReturn(true);
//         when(razorpayClient.payments.fetch(anyString()))
//             .thenReturn(razorpayPayment);
//         when(razorpayPayment.get("id")).thenReturn("pay_12345");
//         when(razorpayPayment.get("order_id")).thenReturn("order_12345");
//         when(razorpayPayment.get("status")).thenReturn("captured");
//         when(razorpayPayment.get("amount")).thenReturn(10000);
//         when(razorpayPayment.get("currency")).thenReturn("INR");
//         when(razorpayPayment.get("email")).thenReturn("john@example.com");
//         when(razorpayPayment.get("created_at")).thenReturn(1672531200L);
//         
//         // When
//         PaymentVerificationResponse response = paymentService.verifyPayment(request);
//         
//         // Then
//         assertNotNull(response);
//         assertTrue(response.isSuccess());
//         assertEquals("Payment verified successfully", response.getMessage());
//         assertEquals("pay_12345", response.getPaymentId());
//         assertEquals("order_12345", response.getOrderId());
//         assertEquals("captured", response.getStatus());
//         assertEquals("100.00", response.getAmount());
//         assertEquals("INR", response.getCurrency());
//         assertEquals("john@example.com", response.getCustomerEmail());
//         assertNotNull(response.getPaymentDate());
//         
//         verify(signatureUtil, times(1)).verifySignature(anyString(), anyString());
//         verify(razorpayClient.payments, times(1)).fetch(anyString());
//     }
//     
//     @Test
//     @DisplayName("Should throw exception when signature verification fails")
//     void verifyPayment_InvalidSignature() {
//         // Given
//         PaymentVerificationRequest request = createMockVerificationRequest();
//         
//         when(signatureUtil.verifySignature(anyString(), anyString()))
//             .thenReturn(false);
//         
//         // When & Then
//         PaymentException exception = assertThrows(PaymentException.class,
//             () -> paymentService.verifyPayment(request));
//         
//         assertEquals("Payment verification failed: Invalid signature", exception.getMessage());
//         assertEquals(400, exception.getStatusCode());
//     }
//     
//     @Test
//     @DisplayName("Should handle payment not captured")
//     void verifyPayment_NotCaptured() throws RazorpayException {
//         // Given
//         PaymentVerificationRequest request = createMockVerificationRequest();
//         
//         when(signatureUtil.verifySignature(anyString(), anyString()))
//             .thenReturn(true);
//         when(razorpayClient.payments.fetch(anyString()))
//             .thenReturn(razorpayPayment);
//         when(razorpayPayment.get("id")).thenReturn("pay_12345");
//         when(razorpayPayment.get("order_id")).thenReturn("order_12345");
//         when(razorpayPayment.get("status")).thenReturn("failed");
//         when(razorpayPayment.get("amount")).thenReturn(10000);
//         when(razorpayPayment.get("currency")).thenReturn("INR");
//         when(razorpayPayment.get("email")).thenReturn("john@example.com");
//         when(razorpayPayment.get("created_at")).thenReturn(1672531200L);
//         
//         // When
//         PaymentVerificationResponse response = paymentService.verifyPayment(request);
//         
//         // Then
//         assertNotNull(response);
//         assertFalse(response.isSuccess());
//         assertEquals("Payment not completed", response.getMessage());
//         assertEquals("failed", response.getStatus());
//     }
//     
//     @Test
//     @DisplayName("Should throw exception when payment not found")
//     void verifyPayment_NotFound() throws RazorpayException {
//         // Given
//         PaymentVerificationRequest request = createMockVerificationRequest();
//         
//         when(signatureUtil.verifySignature(anyString(), anyString()))
//             .thenReturn(true);
//         when(razorpayClient.payments.fetch(anyString()))
//             .thenThrow(new RazorpayException("Payment not found", 404));
//         
//         // When & Then
//         PaymentException exception = assertThrows(PaymentException.class,
//             () -> paymentService.verifyPayment(request));
//         
//         assertTrue(exception.getMessage().contains("Payment verification failed"));
//     }
// }
// 
// @Nested
// @DisplayName("Get Payment Status Tests")
// class GetPaymentStatusTests {
//     
//     @Test
//     @DisplayName("Should get payment status successfully")
//     void getPaymentStatus_Success() throws RazorpayException {
//         // Given
//         String paymentId = "pay_12345";
//         
//         when(razorpayClient.payments.fetch(paymentId))
//             .thenReturn(razorpayPayment);
//         when(razorpayPayment.get("id")).thenReturn("pay_12345");
//         when(razorpayPayment.get("order_id")).thenReturn("order_12345");
//         when(razorpayPayment.get("status")).thenReturn("captured");
//         when(razorpayPayment.get("amount")).thenReturn(10000);
//         when(razorpayPayment.get("currency")).thenReturn("INR");
//         when(razorpayPayment.get("email")).thenReturn("john@example.com");
//         when(razorpayPayment.get("created_at")).thenReturn(1672531200L);
//         
//         // When
//         PaymentVerificationResponse response = paymentService.getPaymentStatus(paymentId);
//         
//         // Then
//         assertNotNull(response);
//         assertTrue(response.isSuccess());
//         assertEquals("Payment status retrieved", response.getMessage());
//         assertEquals("pay_12345", response.getPaymentId());
//         assertEquals("captured", response.getStatus());
//         
//         verify(razorpayClient.payments, times(1)).fetch(paymentId);
//     }
//     
//     @Test
//     @DisplayName("Should throw exception when payment not found")
//     void getPaymentStatus_NotFound() throws RazorpayException {
//         // Given
//         String paymentId = "pay_invalid";
//         
//         when(razorpayClient.payments.fetch(paymentId))
//             .thenThrow(new RazorpayException("Payment not found", 404));
//         
//         // When & Then
//         PaymentException exception = assertThrows(PaymentException.class,
//             () -> paymentService.getPaymentStatus(paymentId));
//         
//         assertEquals("Failed to fetch payment status", exception.getMessage());
//         assertEquals(500, exception.getStatusCode());
//     }
// }
// 
// @Test
// @DisplayName("Should handle webhook successfully")
// void handleWebhook_Success() {
//     // Given
//     String payload = createWebhookPayload();
//     String signature = "valid_signature";
//     String webhookSecret = "webhook_secret";
//     
//     ReflectionTestUtils.setField(paymentService, "webhookSecret", webhookSecret);
//     
//     when(signatureUtil.verifyWebhookSignature(payload, signature, webhookSecret))
//         .thenReturn(true);
//     
//     // When & Then
//     assertDoesNotThrow(() -> paymentService.handleWebhook(payload, signature));
//     
//     verify(signatureUtil, times(1))
//         .verifyWebhookSignature(payload, signature, webhookSecret);
// }
// 
// @Test
// @DisplayName("Should throw exception for invalid webhook signature")
// void handleWebhook_InvalidSignature() {
//     // Given
//     String payload = createWebhookPayload();
//     String signature = "invalid_signature";
//     String webhookSecret = "webhook_secret";
//     
//     ReflectionTestUtils.setField(paymentService, "webhookSecret", webhookSecret);
//     
//     when(signatureUtil.verifyWebhookSignature(payload, signature, webhookSecret))
//         .thenReturn(false);
//     
//     // When & Then
//     PaymentException exception = assertThrows(PaymentException.class,
//         () -> paymentService.handleWebhook(payload, signature));
//     
//     assertEquals("Invalid webhook signature", exception.getMessage());
//     assertEquals(400, exception.getStatusCode());
// }
// 
// // Helper Methods
// 
// private PaymentOrderRequest createMockOrderRequest() {
//     PaymentOrderRequest.CustomerDetails customer = 
//         new PaymentOrderRequest.CustomerDetails(
//             "John Doe", 
//             "john@example.com", 
//             "+919876543210"
//         );
//     
//     PaymentOrderRequest request = new PaymentOrderRequest();
//     request.setAmount(new BigDecimal("100.00"));
//     request.setCurrency("INR");
//     request.setReceipt("order_001");
//     request.setCustomer(customer);
//     request.setNotes("Test order");
//     
//     return request;
// }
// 
// private Map<String, Object> createMockOrderResponseMap() {
//     Map<String, Object> response = new HashMap<>();
//     response.put("id", "order_12345");
//     response.put("amount", 10000);
//     response.put("currency", "INR");
//     response.put("receipt", "order_001");
//     response.put("status", "created");
//     response.put("attempts", 0);
//     response.put("created_at", 1672531200);
//     
//     Map<String, String> notes = new HashMap<>();
//     notes.put("customer_name", "John Doe");
//     notes.put("customer_email", "john@example.com");
//     notes.put("customer_phone", "+919876543210");
//     response.put("notes", notes);
//     
//     return response;
// }
// 
// private JSONObject createMockOrderResponse() {
//     return new JSONObject(createMockOrderResponseMap());
// }
// 
// private PaymentVerificationRequest createMockVerificationRequest() {
//     PaymentVerificationRequest request = new PaymentVerificationRequest();
//     request.setRazorpayPaymentId("pay_12345");
//     request.setRazorpayOrderId("order_12345");
//     request.setRazorpaySignature("valid_signature");
//     request.setOrderId("order_001");
//     return request;
// }
// 
// private Map<String, Object> createMockPaymentData() {
//     Map<String, Object> paymentData = new HashMap<>();
//     paymentData.put("id", "pay_12345");
//     paymentData.put("order_id", "order_12345");
//     paymentData.put("status", "captured");
//     paymentData.put("amount", 10000);
//     paymentData.put("currency", "INR");
//     paymentData.put("email", "john@example.com");
//     paymentData.put("contact", "+919876543210");
//     paymentData.put("method", "card");
//     paymentData.put("card_id", "card_12345");
//     paymentData.put("bank", "HDFC");
//     paymentData.put("wallet", null);
//     paymentData.put("vpa", null);
//     paymentData.put("created_at", 1672531200L);
//     
//     return paymentData;
// }
// 
// private Payment createMockPayment() {
//     Payment payment = mock(Payment.class);
//     when(payment.get("id")).thenReturn("pay_12345");
//     when(payment.get("order_id")).thenReturn("order_12345");
//     when(payment.get("status")).thenReturn("captured");
//     when(payment.get("amount")).thenReturn(10000);
//     when(payment.get("currency")).thenReturn("INR");
//     when(payment.get("email")).thenReturn("john@example.com");
//     when(payment.get("created_at")).thenReturn(1672531200L);
//     return payment;
// }
// 
// private String createWebhookPayload() {
//     return """
//         {
//             "event": "payment.captured",
//             "payload": {
//                 "payment": {
//                     "entity": {
//                         "id": "pay_12345",
//                         "order_id": "order_12345",
//                         "status": "captured",
//                         "amount": 10000,
//                         "currency": "INR",
//                         "email": "john@example.com",
//                         "method": "card",
//                         "created_at": 1672531200
//                     }
//                 }
//             }
//         }
//         """;
// }
// 
// // Additional Edge Case Tests
// 
// @Test
// @DisplayName("Should handle different currency conversions")
// void createPaymentOrder_DifferentCurrency() throws RazorpayException {
//     // Given
//     PaymentOrderRequest request = createMockOrderRequest();
//     request.setCurrency("USD");
//     request.setAmount(new BigDecimal("50.00")); // $50 = 5000 cents
//     
//     when(razorpayClient.orders.create(any(JSONObject.class)))
//         .thenReturn(razorpayOrder);
//     when(razorpayOrder.get("id")).thenReturn("order_usd_123");
//     when(razorpayOrder.get("amount")).thenReturn(5000); // 5000 cents
//     when(razorpayOrder.get("currency")).thenReturn("USD");
//     when(razorpayOrder.get("receipt")).thenReturn("order_usd_001");
//     
//     // When
//     PaymentOrderResponse response = paymentService.createPaymentOrder(request);
//     
//     // Then
//     assertNotNull(response);
//     assertEquals("USD", response.getCurrency());
//     assertEquals("50.00", response.getAmount());
// }
// 
// @Test
// @DisplayName("Should handle null customer details gracefully")
// void createPaymentOrder_NullCustomerDetails() {
//     // Given
//     PaymentOrderRequest request = new PaymentOrderRequest();
//     request.setAmount(new BigDecimal("100.00"));
//     request.setCurrency("INR");
//     request.setReceipt("order_001");
//     request.setCustomer(null); // Null customer
//     
//     // When & Then
//     PaymentException exception = assertThrows(PaymentException.class,
//         () -> paymentService.createPaymentOrder(request));
//     
//     assertTrue(exception.getMessage().contains("Failed to create payment order"));
// }
// 
// @Test
// @DisplayName("Should handle retry logic for transient failures")
// void verifyPayment_WithRetry() throws RazorpayException {
//     // Given
//     PaymentVerificationRequest request = createMockVerificationRequest();
//     
//     when(signatureUtil.verifySignature(anyString(), anyString()))
//         .thenReturn(true);
//     
//     // First call fails, second succeeds
//     when(razorpayClient.payments.fetch(anyString()))
//         .thenThrow(new RazorpayException("Temporary error"))
//         .thenReturn(razorpayPayment);
//     
//     when(razorpayPayment.get("id")).thenReturn("pay_12345");
//     when(razorpayPayment.get("order_id")).thenReturn("order_12345");
//     when(razorpayPayment.get("status")).thenReturn("captured");
//     when(razorpayPayment.get("amount")).thenReturn(10000);
//     when(razorpayPayment.get("currency")).thenReturn("INR");
//     when(razorpayPayment.get("email")).thenReturn("john@example.com");
//     when(razorpayPayment.get("created_at")).thenReturn(1672531200L);
//     
//     // When & Then
//     assertThrows(PaymentException.class, 
//         () -> paymentService.verifyPayment(request));
//     
//     // Note: In actual implementation with @Retryable, this would succeed on retry
//     // This test shows the need for proper retry configuration
// }
// 
// @Test
// @DisplayName("Should handle webhook with different events")
// void handleWebhook_DifferentEvents() {
//     // Given
//     String webhookSecret = "webhook_secret";
//     ReflectionTestUtils.setField(paymentService, "webhookSecret", webhookSecret);
//     
//     String[] events = {"payment.captured", "payment.failed", "payment.refunded"};
//     
//     for (String event : events) {
//         String payload = createWebhookPayloadWithEvent(event);
//         String signature = "valid_signature_" + event;
//         
//         when(signatureUtil.verifyWebhookSignature(payload, signature, webhookSecret))
//             .thenReturn(true);
//         
//         // When & Then
//         assertDoesNotThrow(() -> paymentService.handleWebhook(payload, signature));
//     }
// }
// 
// private String createWebhookPayloadWithEvent(String event) {
//     return String.format("""
//         {
//             "event": "%s",
//             "payload": {
//                 "payment": {
//                     "entity": {
//                         "id": "pay_12345",
//                         "order_id": "order_12345",
//                         "status": "%s",
//                         "amount": 10000,
//                         "currency": "INR",
//                         "email": "john@example.com"
//                     }
//                 }
//             }
//         }
//         """, event, event.replace("payment.", ""));
// }
//}
