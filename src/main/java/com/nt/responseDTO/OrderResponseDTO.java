package com.nt.responseDTO;

import java.time.LocalDateTime;


public class OrderResponseDTO {
		
		private Long orderId;
		private LocalDateTime orderDate;
		private Double totalAmount;
		private String status;
		private Long userId;
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public LocalDateTime getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}
		public Double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		@Override
		public String toString() {
			return "OrderResponseDTO [orderId=" + orderId + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount
					+ ", status=" + status + ", userId=" + userId + "]";
		}
		public OrderResponseDTO(Long orderId, LocalDateTime orderDate, Double totalAmount, String status, Long userId) {
			super();
			this.orderId = orderId;
			this.orderDate = orderDate;
			this.totalAmount = totalAmount;
			this.status = status;
			this.userId = userId;
		}
		public OrderResponseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
