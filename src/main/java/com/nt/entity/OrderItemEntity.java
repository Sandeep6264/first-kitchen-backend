package com.nt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="order_items")

public class OrderItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORDER_ITEM_ID")
	private Long orderItemId;
	
	@Column(name="qty")
	private Long qty;
	
	@Column(name="price")
	private Double price;
	
	@ManyToOne(targetEntity=OrdersEntity.class)
	@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
//	@JsonBackReference
	private OrdersEntity order;

	
	@ManyToOne(targetEntity=ItemEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID",referencedColumnName="ITEM_ID")
//	@JsonIgnore
	private ItemEntity item;


	public Long getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}


	public Long getQty() {
		return qty;
	}


	public void setQty(Long qty) {
		this.qty = qty;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public OrdersEntity getOrder() {
		return order;
	}


	public void setOrder(OrdersEntity order) {
		this.order = order;
	}


	public ItemEntity getItem() {
		return item;
	}


	public void setItem(ItemEntity item) {
		this.item = item;
	}


	@Override
	public String toString() {
		return "OrderItemEntity [orderItemId=" + orderItemId + ", qty=" + qty + ", price=" + price + ", order=" + order
				+ ", item=" + item + "]";
	}


	public OrderItemEntity(Long orderItemId, Long qty, Double price, OrdersEntity order, ItemEntity item) {
		super();
		this.orderItemId = orderItemId;
		this.qty = qty;
		this.price = price;
		this.order = order;
		this.item = item;
	}


	public OrderItemEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
