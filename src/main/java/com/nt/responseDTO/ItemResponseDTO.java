package com.nt.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class ItemResponseDTO {
     private Long itemId;
	
	private String itemName;

	private String itemImg;

	private String description;
	
	private String itemCategory;

	private String itemSubCategory;

	private Double itemPrice;
	
	private String itemStatus;

	private String isHalf;
	
	private Double halfPrice;

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

	@Override
	public String toString() {
		return "ItemResponseDTO [itemId=" + itemId + ", itemName=" + itemName + ", itemImg=" + itemImg
				+ ", description=" + description + ", itemCategory=" + itemCategory + ", itemSubCategory="
				+ itemSubCategory + ", itemPrice=" + itemPrice + ", itemStatus=" + itemStatus + ", isHalf=" + isHalf
				+ ", halfPrice=" + halfPrice + "]";
	}

	public ItemResponseDTO(Long itemId, String itemName, String itemImg, String description, String itemCategory,
			String itemSubCategory, Double itemPrice, String itemStatus, String isHalf, Double halfPrice) {
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
	}

	public ItemResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
