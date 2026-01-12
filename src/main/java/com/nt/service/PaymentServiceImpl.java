package com.nt.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.exception.PaymentException;
import com.nt.requestDTO.PaymentOrderRequest;
import com.nt.requestDTO.PaymentVerificationRequest;
import com.nt.responseDTO.PaymentOrderResponse;
import com.nt.responseDTO.PaymentVerificationResponse;
import com.nt.util.RazorpaySignatureUtil;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements IPaymentService {
	
//	private static final Logger logger=LoggerFactory.getLogger(PaymentServiceImpl.class);
	private static final Logger logger=LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	private final RazorpayClient razorpayClient;
	private final RestTemplate  restTemplate;
	private final RazorpaySignatureUtil signatureUtil;
	
	@Value("${razorpay.key.id}")
	private String razorpayKeyId;
	
	@Value("${razorpay.webhook.secret}")
    private String webhookSecret;
	
	public PaymentServiceImpl(RazorpayClient razorpayClient, 
            RestTemplate restTemplate,
            RazorpaySignatureUtil signatureUtil) {
this.razorpayClient = razorpayClient;
this.restTemplate = restTemplate;
this.signatureUtil = signatureUtil;
}

	@Override
	@Transactional
	public PaymentOrderResponse createPaymentOrder(PaymentOrderRequest request) {
		try {
			logger.info("Creating payment order for receipt: {}, amount: {} {}",request.getReceipt(),request.getAmount(),request.getCurrency());
			BigDecimal amountInPaise=request.getAmount().multiply(BigDecimal.valueOf(100));
			
			JSONObject orderRequest =new JSONObject();
			orderRequest.put("amount", amountInPaise.intValue());
            orderRequest.put("currency", request.getCurrency());
            orderRequest.put("receipt", request.getReceipt());
            orderRequest.put("payment_capture", 1);
            
            JSONObject notes = new JSONObject();
            notes.put("customer_name", request.getCustomer().getName());
            notes.put("customer_email", request.getCustomer().getEmail());
            notes.put("customer_phone", request.getCustomer().getPhone());
            
            if (request.getNotes() != null) {
                notes.put("internal_notes", request.getNotes());
            }
            orderRequest.put("notes", notes);
            
            Order order = razorpayClient.orders.create(orderRequest);
            logger.info("Order created successfully: {}"+" "+order.get("id"));
            PaymentOrderResponse response = new PaymentOrderResponse();
            response.setSuccess(true);
            response.setMessage("Order created successfully");
            response.setOrderId(request.getReceipt());
            response.setRazorpayOrderId(order.get("id"));
            response.setCurrency(request.getCurrency());
            response.setAmount(request.getAmount().toString());
            response.setRazorpayKeyId(razorpayKeyId);
            response.setCustomerName(request.getCustomer().getName());
            response.setCustomerEmail(request.getCustomer().getEmail());
            response.setTimestamp(LocalDateTime.now().toString());
            return response;
			
		}catch(RazorpayException re) {
            logger.error("Failed to create Razorpay order", re);
            throw new PaymentException("Failed to create payment order: " + re.getMessage(), re);
			
		}
		catch (Exception e) {
            logger.error("Unexpected error creating payment order", e);
            throw new PaymentException("Unexpected error occurred", e);
        }
	}

	@Override
	@Transactional
	@Retryable(value= {Exception.class},
			maxAttempts =3,
			backoff =@Backoff(delay=1000))
	public PaymentVerificationResponse verifyPayment(PaymentVerificationRequest request) {
		try {
			 logger.info("Verifying payment for order: {}, payment: {}", 
	                 request.getRazorpayOrderId(), request.getRazorpayPaymentId());
			// TODO Auto-generated method stub
			 boolean isValidSignature = signatureUtil.verifySignature(request.getRazorpayOrderId()+"|"+request.getRazorpayPaymentId(), request.getRazorpaySignature());
			  if (!isValidSignature) {
	              logger.error("Invalid signature for payment: {}", request.getRazorpayPaymentId());
	              throw new PaymentException("Payment verification failed: Invalid signature");
	          }
			  Payment payment=razorpayClient.payments.fetch(request.getRazorpayPaymentId());
			  String status = payment.get("status");
			  boolean isPaymentSuccessful="captured".equals(status);
			  if (isPaymentSuccessful) {
	              logger.info("Payment successful for order: {}", request.getRazorpayOrderId());
	              // Update your database here (mark order as paid)
	              // savePaymentToDatabase(payment, request.getOrderId());
			  }
			  return buildVerificationResponse(payment, true, "Payment verified successfully");
		}catch(RazorpayException  e) {
			logger.error("Razorpay API error during payment verification", e);
            throw new PaymentException("Payment verification failed: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error during payment verification", e);
            throw new PaymentException("Payment verification failed", e);
        }
			
	}

	@Override
	@Cacheable(value = "paymentStatus", key = "#paymentId")
    public PaymentVerificationResponse getPaymentStatus(String paymentId) {
        try {
            Payment payment = razorpayClient.payments.fetch(paymentId);
            return buildVerificationResponse(payment, true, "Payment status retrieved");
        } catch (RazorpayException e) {
            logger.error("Failed to fetch payment status for: {}", paymentId, e);
            throw new PaymentException("Failed to fetch payment status", e);
        }
    }
    
    @Override
    @Transactional
    public void handleWebhook(String payload, String signature) {
        try {
            logger.info("Processing webhook with signature: {}", signature);
            
            // Verify webhook signature
            boolean isValidWebhook = signatureUtil.verifyWebhookSignature(payload, signature, webhookSecret);
            
            if (!isValidWebhook) {
                logger.error("Invalid webhook signature");
                throw new PaymentException("Invalid webhook signature");
            }
            
            // Parse webhook payload
            JSONObject webhookData = new JSONObject(payload);
            String event = webhookData.getString("event");
            JSONObject payloadData = webhookData.getJSONObject("payload");
            JSONObject paymentEntity = payloadData.getJSONObject("payment").getJSONObject("entity");
            
            String paymentId = paymentEntity.getString("id");
            String status = paymentEntity.getString("status");
            
            logger.info("Webhook event: {} for payment: {} with status: {}", 
                    event, paymentId, status);
            
            // Handle different webhook events
            switch (event) {
                case "payment.captured":
                    handlePaymentCaptured(paymentEntity);
                    break;
                case "payment.failed":
                    handlePaymentFailed(paymentEntity);
                    break;
                case "payment.refunded":
                    handlePaymentRefunded(paymentEntity);
                    break;
                default:
                    logger.info("Unhandled webhook event: {}", event);
            }
            
        } catch (Exception e) {
            logger.error("Error processing webhook", e);
            throw new PaymentException("Webhook processing failed", e);
        }
    }
    
    private void handlePaymentCaptured(JSONObject paymentEntity) {
        // Update your database - mark payment as successful
        String paymentId = paymentEntity.getString("id");
        logger.info("Payment captured: {}", paymentId);
        // Your business logic here
    }
    
    private void handlePaymentFailed(JSONObject paymentEntity) {
        // Update your database - mark payment as failed
        String paymentId = paymentEntity.getString("id");
        logger.warn("Payment failed: {}", paymentId);
        // Your business logic here
    }
    
    private void handlePaymentRefunded(JSONObject paymentEntity) {
        // Update your database - handle refund
        String paymentId = paymentEntity.getString("id");
        logger.info("Payment refunded: {}", paymentId);
        // Your business logic here
    }
	
	private PaymentVerificationResponse buildVerificationResponse(Payment  payment,boolean success,String message) {
        long createdAt = payment.get("created_at");
        LocalDateTime paymentDate=LocalDateTime.ofInstant(
                Instant.ofEpochSecond(createdAt), 
                ZoneId.systemDefault()
        );

	PaymentVerificationResponse response=new PaymentVerificationResponse(success,message,payment.get("id"),payment.get("order_id"),
			payment.get("status"),String.valueOf(Integer.parseInt(payment.get("amount"))/100),
			payment.get("currency"),paymentDate,payment.get("email"));
	
	return response;
	}
	
	
	
	


}
