package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class OrderItemRequestDTO {
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
			return "OrderItemRequestDTO [qty=" + qty + ", itemId=" + itemId + ", itemPrice=" + itemPrice + "]";
		}
		public OrderItemRequestDTO(Long qty, Long itemId, Double itemPrice) {
			super();
			this.qty = qty;
			this.itemId = itemId;
			this.itemPrice = itemPrice;
		}
		public OrderItemRequestDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
