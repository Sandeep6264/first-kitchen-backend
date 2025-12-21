package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.requestDTO.OrderRequestDTO;
import com.nt.responseDTO.OrderResponseDTO;
import com.nt.service.IOrderService;
import com.nt.util.ResponseUtil;

@RestController
@RequestMapping("/api/order")
public class OrderOperationController {
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO){
//		System.out.println(orderRequestDTO);
		OrderResponseDTO orderResponseDTO=orderService.placeOrder(orderRequestDTO);
		return ResponseUtil.success(orderResponseDTO, "Order placed successfully");
	}
}
