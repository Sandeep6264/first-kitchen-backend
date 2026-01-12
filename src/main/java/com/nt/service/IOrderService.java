package com.nt.service;

import java.util.List;

import com.nt.requestDTO.OrderRequestDTO;
import com.nt.responseDTO.OrderResponseDTO;
import com.nt.responseDTO.ReturnOrderResponseDTO;

public interface IOrderService {
	public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
	public List<ReturnOrderResponseDTO> findMyOrders() ;
}
