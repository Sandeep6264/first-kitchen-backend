package com.nt.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayConfig {
	
	
//	private static final Logger logger =LoggerFactory.getLogger((RazorpayConfig.class);
	
	@Value("${razorpay.key.id}")
	private String razorpayKeyId;
	
	@Value("${razorpay.key.secret}")
	private String razorpayKeySecret;
	
	@Bean
	public RazorpayClient createRazorPayClient() {
		try {
//			logger.info("Initializing Razorpay client with key: {}",razorpayKeyId.substring(0,8)+"...............");
			return new RazorpayClient(razorpayKeyId, razorpayKeySecret);
		}catch(RazorpayException ex) {
//			logger.error("Failed to initialize Razorpay client", ex.getMessage());
            throw new RuntimeException("Failed to initialize Razorpay client", ex);
		}
	}

}
