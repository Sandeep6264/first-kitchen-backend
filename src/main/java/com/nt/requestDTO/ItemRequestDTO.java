package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class ItemRequestDTO {
     private Long id;
	
	private String itemName;

	private String itemImg;

	private String description;
	
	private String itemCategory;

	private String itemSubCategory;

	private Double itemPrice;
	
	private String itemStatus;

	private String isHalf;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	private Double halfPrice;

	@Override
	public String toString() {
		return "ItemRequestDTO [id=" + id + ", itemName=" + itemName + ", itemImg=" + itemImg + ", description="
				+ description + ", itemCategory=" + itemCategory + ", itemSubCategory=" + itemSubCategory
				+ ", itemPrice=" + itemPrice + ", itemStatus=" + itemStatus + ", isHalf=" + isHalf + ", halfPrice="
				+ halfPrice + "]";
	}

	public ItemRequestDTO(Long id, String itemName, String itemImg, String description, String itemCategory,
			String itemSubCategory, Double itemPrice, String itemStatus, String isHalf, Double halfPrice) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.description = description;
		this.itemCategory = itemCategory;
		this.itemSubCategory = itemSubCategory;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
		this.isHalf = isHalf;
		this.halfPrice = halfPrice;
	}

	public ItemRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
