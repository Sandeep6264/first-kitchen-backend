package com.nt.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class OrderItemRequest {
		private Long qty;
		private Long itemId;
		private Double itemPrice;
		public Long getQty() {
			return qty;
		}
		public void setQty(Long qty) {
			this.qty = qty;
		}
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		public Double getItemPrice() {
			return itemPrice;
		}
		public void setItemPrice(Double itemPrice) {
			this.itemPrice = itemPrice;
		}
		@Override
		public String toString() {
			return "OrderItemRequest [qty=" + qty + ", itemId=" + itemId + ", itemPrice=" + itemPrice + "]";
		}
		public OrderItemRequest(Long qty, Long itemId, Double itemPrice) {
			super();
			this.qty = qty;
			this.itemId = itemId;
			this.itemPrice = itemPrice;
		}
		public OrderItemRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
