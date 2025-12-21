package com.nt.service;

import com.nt.requestDTO.OrderRequestDTO;
import com.nt.responseDTO.OrderResponseDTO;

public interface IOrderService {
	public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
}
