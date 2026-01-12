package com.nt.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="items")

public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ITEM_ID")
	private Long itemId;
	
	@Column(name="name")
	private String itemName;
	
	@Column(name="IMG")
	private String itemImg;
	
	@Column(name="description")
	private String description;
	
	@Column(name="category")
	private String itemCategory;
	
	@Column(name="subcategory")
	private String itemSubCategory;
	
	@Column(name="price")
	private Double itemPrice;
	
	@Column(name="status")
	private String itemStatus;
	
	@Column(name="is_half")
	private String isHalf;
	
	@Column(name="half_price")
	private Double halfPrice;
	
//	@JsonManagedReference
	@OneToMany(orphanRemoval = true,targetEntity = OrderItemEntity.class,mappedBy = "item",fetch = FetchType.LAZY)
	private Set<OrderItemEntity> orderItemEntity;

	@Override
	public String toString() {
		return "ItemEntity [itemId=" + itemId + ", itemName=" + itemName + ", itemImg=" + itemImg + ", description="
				+ description + ", itemCategory=" + itemCategory + ", itemSubCategory=" + itemSubCategory
				+ ", itemPrice=" + itemPrice + ", itemStatus=" + itemStatus + ", isHalf=" + isHalf + ", halfPrice="
				+ halfPrice + "]";
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImg() {
		return itemImg;
	}

	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSubCategory() {
		return itemSubCategory;
	}

	public void setItemSubCategory(String itemSubCategory) {
		this.itemSubCategory = itemSubCategory;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getIsHalf() {
		return isHalf;
	}

	public void setIsHalf(String isHalf) {
		this.isHalf = isHalf;
	}

	public Double getHalfPrice() {
		return halfPrice;
	}

	public void setHalfPrice(Double halfPrice) {
		this.halfPrice = halfPrice;
	}

	public Set<OrderItemEntity> getOrderItemEntity() {
		return orderItemEntity;
	}

	public void setOrderItemEntity(Set<OrderItemEntity> orderItemEntity) {
		this.orderItemEntity = orderItemEntity;
	}

	public ItemEntity(Long itemId, String itemName, String itemImg, String description, String itemCategory,
			String itemSubCategory, Double itemPrice, String itemStatus, String isHalf, Double halfPrice,
			Set<OrderItemEntity> orderItemEntity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.description = description;
		this.itemCategory = itemCategory;
		this.itemSubCategory = itemSubCategory;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
		this.isHalf = isHalf;
		this.halfPrice = halfPrice;
		this.orderItemEntity = orderItemEntity;
	}

	public ItemEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
