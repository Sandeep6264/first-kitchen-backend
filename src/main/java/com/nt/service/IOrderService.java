package com.nt.service;

import java.util.List;

import com.nt.entity.OrdersEntity;
import com.nt.requestDTO.OrderRequestDTO;
import com.nt.responseDTO.OrderResponseDTO;

public interface IOrderService {
	public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
	public List<OrdersEntity> findMyOrders() ;
}
