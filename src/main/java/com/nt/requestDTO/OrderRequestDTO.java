package com.nt.requestDTO;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequestDTO {
	private LocalDateTime orderDate;
	private String status;
	 private Double totalAmount;
	 private Set<OrderItemRequestDTO> orderItem;
}
