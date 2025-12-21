package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_items")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORDER_ITEM_ID")
	private Long orderItemId;
	
	@Column(name="qty")
	private Long qty;
	
	@Column(name="price")
	private Double price;
	
	@ManyToOne(targetEntity=OrdersEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
	private OrdersEntity order;

	
	@ManyToOne(targetEntity=ItemEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID",referencedColumnName="ITEM_ID")
	private ItemEntity item;
	

}
