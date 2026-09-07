package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="payment-info")
@Entity
public class PaymentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long paymentId;
	
	
	
}
