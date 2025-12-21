package com.nt.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponseDTO {
		
		private Long orderId;
		private LocalDateTime orderDate;
		private Double totalAmount;
		private String status;
		private Long userId;
}
