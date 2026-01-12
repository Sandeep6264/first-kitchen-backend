package com.nt.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RazorpaySignatureUtil {
	
		@Value("${razorpay.key.secret}")
		 private String razorpaySecret;
		
		 private static final String HMAC_SHA256 = "HmacSHA256";
		
		private static final Logger logger=LoggerFactory.getLogger(RazorpaySignatureUtil.class);
		
		public boolean verifySignature(String orderId,String paymentId,String razorpaySignature) {
			 try {
		            String payload = orderId + "|" + paymentId;
		            return verifySignature(payload, razorpaySignature);
		        } catch (Exception e) {
		            logger.error("Error verifying signature", e);
		            return false;
		        }
		}
		public boolean verifySignature(String payload, String razorpaySignature) {
			try {
				String generatedSignature =calculateHMAC(payload,razorpaySecret);
				logger.debug("Generated signature: {}, Received signature: {}", 
	                    generatedSignature, razorpaySignature);
				 return MessageDigest.isEqual(
		                    generatedSignature.getBytes(StandardCharsets.UTF_8),
		                    razorpaySignature.getBytes(StandardCharsets.UTF_8)
		            );
			}catch (Exception e) {
	            logger.error("Error verifying signature", e);
	            return false;
	        }
		}
		 public boolean verifyWebhookSignature(String payload, String signature, String webhookSecret) {
			 try {
				 String generatedSignature = calculateHMAC(payload, webhookSecret);
		            return MessageDigest.isEqual(
		                    generatedSignature.getBytes(StandardCharsets.UTF_8),
		                    signature.getBytes(StandardCharsets.UTF_8)
		            );
			 }catch (Exception e) {
		            logger.error("Error verifying webhook signature", e);
		            return false;
		        }
		 }
		 private String calculateHMAC(String data, String secret) throws Exception {
			 Mac sha256HMAC =Mac.getInstance(HMAC_SHA256);
			 SecretKeySpec secretKey=new SecretKeySpec (secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
			 sha256HMAC.init(secretKey);
			 byte[] hash = sha256HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
		        return bytesToHex(hash);
		 }
		 private String bytesToHex(byte[] bytes) {
		        StringBuilder hexString = new StringBuilder(2 * bytes.length);
		        for (byte b : bytes) {
		            String hex = Integer.toHexString(0xff & b);
		            if (hex.length() == 1) {
		                hexString.append('0');
		            }
		            hexString.append(hex);
		        }
		        return hexString.toString();
		    }
		        
}
