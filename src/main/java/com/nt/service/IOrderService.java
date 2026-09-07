package com.nt.service;

import java.util.List;

import com.nt.request.OrderRequest;
import com.nt.response.dto.OrderResponseDTO;
import com.nt.response.dto.ReturnOrderResponseDTO;

public interface IOrderService {
	public OrderResponseDTO placeOrder(OrderRequest orderRequest);
	public List<ReturnOrderResponseDTO> findMyOrders() ;
}
