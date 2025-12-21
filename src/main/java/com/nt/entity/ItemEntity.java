package com.nt.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="items")
@Setter
@Getter
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
	
	@OneToMany(orphanRemoval = true,targetEntity = OrderItemEntity.class,mappedBy = "item",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<OrderItemEntity> orderItemEntity;

	@Override
	public String toString() {
		return "ItemEntity [itemId=" + itemId + ", itemName=" + itemName + ", itemImg=" + itemImg + ", description="
				+ description + ", itemCategory=" + itemCategory + ", itemSubCategory=" + itemSubCategory
				+ ", itemPrice=" + itemPrice + ", itemStatus=" + itemStatus + ", isHalf=" + isHalf + ", halfPrice="
				+ halfPrice + "]";
	}
	
	
	
	

}
