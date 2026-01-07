package com.nt.requestDTO;

import java.time.LocalDateTime;
import java.util.Set;


public class OrderRequestDTO {
	private LocalDateTime orderDate;
	private String status;
	 private Double totalAmount;
	 private Set<OrderItemRequestDTO> orderItem;
	 public LocalDateTime getOrderDate() {
		 return orderDate;
	 }
	 public void setOrderDate(LocalDateTime orderDate) {
		 this.orderDate = orderDate;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 public Double getTotalAmount() {
		 return totalAmount;
	 }
	 public void setTotalAmount(Double totalAmount) {
		 this.totalAmount = totalAmount;
	 }
	 public Set<OrderItemRequestDTO> getOrderItem() {
		 return orderItem;
	 }
	 public void setOrderItem(Set<OrderItemRequestDTO> orderItem) {
		 this.orderItem = orderItem;
	 }
	 @Override
	 public String toString() {
		return "OrderRequestDTO [orderDate=" + orderDate + ", status=" + status + ", totalAmount=" + totalAmount
				+ ", orderItem=" + orderItem + "]";
	 }
	 public OrderRequestDTO(LocalDateTime orderDate, String status, Double totalAmount,
			Set<OrderItemRequestDTO> orderItem) {
		super();
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
		this.orderItem = orderItem;
	 }
	 public OrderRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 
	 
}
