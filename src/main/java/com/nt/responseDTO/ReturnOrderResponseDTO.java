package com.nt.responseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReturnOrderResponseDTO {
	 private Long orderId;
	    private LocalDateTime orderDate;
	    private String status;
	    private Double totalAmount;
	    private List<OrderItemDTO> items;
		/**
		 * @return the orderId
		 */
		public Long getOrderId() {
			return orderId;
		}
		/**
		 * @param orderId the orderId to set
		 */
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		/**
		 * @return the orderDate
		 */
		public LocalDateTime getOrderDate() {
			return orderDate;
		}
		/**
		 * @param orderDate the orderDate to set
		 */
		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the totalAmount
		 */
		public Double getTotalAmount() {
			return totalAmount;
		}
		/**
		 * @param totalAmount the totalAmount to set
		 */
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		/**
		 * @return the items
		 */
		public List<OrderItemDTO> getItems() {
			return items;
		}
		/**
		 * @param items the items to set
		 */
		public void setItems(List<OrderItemDTO> items) {
			this.items = items;
		}
		@Override
		public String toString() {
			return "ReturnOrderResponseDTO [orderId=" + orderId + ", orderDate=" + orderDate + ", status=" + status
					+ ", totalAmount=" + totalAmount + ", items=" + items + "]";
		}
		public ReturnOrderResponseDTO(Long orderId, LocalDateTime orderDate, String status, Double totalAmount,
				List<OrderItemDTO> items) {
			super();
			this.orderId = orderId;
			this.orderDate = orderDate;
			this.status = status;
			this.totalAmount = totalAmount;
			this.items = items;
		}
		public ReturnOrderResponseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
