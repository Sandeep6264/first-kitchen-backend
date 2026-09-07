package com.nt.request;

import java.time.LocalDateTime;
import java.util.Set;


public class OrderRequest {
	private LocalDateTime orderDate;
	private String status;
	 private Double totalAmount;
	 private Set<OrderItemRequest> orderItem;
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
	 public Set<OrderItemRequest> getOrderItem() {
		 return orderItem;
	 }
	 public void setOrderItem(Set<OrderItemRequest> orderItem) {
		 this.orderItem = orderItem;
	 }
	 @Override
	 public String toString() {
		return "OrderRequest [orderDate=" + orderDate + ", status=" + status + ", totalAmount=" + totalAmount
				+ ", orderItem=" + orderItem + "]";
	 }
	 public OrderRequest(LocalDateTime orderDate, String status, Double totalAmount,
			Set<OrderItemRequest> orderItem) {
		super();
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
		this.orderItem = orderItem;
	 }
	 public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 
	 
}
