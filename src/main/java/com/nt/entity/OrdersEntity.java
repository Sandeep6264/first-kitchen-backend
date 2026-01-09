package com.nt.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long oid;

    @CreationTimestamp
    @Column(name = "ORDER_DATE", updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @JsonIgnore
    @ManyToOne(targetEntity = UserEntity.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserEntity user;
   
    @JsonIgnore
    @JsonManagedReference
    @OneToMany(targetEntity = OrderItemEntity.class, mappedBy = "order",cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderItemEntity> items=new HashSet<>();
    
    public void addItem(OrderItemEntity item) {
        items.add(item);
        item.setOrder(this);
    }
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public Set<OrderItemEntity> getItems() {
		return items;
	}
	public void setItems(Set<OrderItemEntity> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "OrdersEntity [oid=" + oid + ", orderDate=" + orderDate + ", status=" + status + ", totalAmount="
				+ totalAmount + ", user=" + user + ", items=" + items + "]";
	}
	public OrdersEntity(Long oid, LocalDateTime orderDate, String status, Double totalAmount, UserEntity user,
			Set<OrderItemEntity> items) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
		this.user = user;
		this.items = items;
	}
	public OrdersEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
