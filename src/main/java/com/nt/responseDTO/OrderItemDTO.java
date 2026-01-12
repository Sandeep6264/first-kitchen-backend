package com.nt.responseDTO;

public class OrderItemDTO {
    private Long itemId;
    private String itemName;
    private Integer qty;
    private Double price;
	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the qty
	 */
	public Integer getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderItemDTO [itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty + ", price=" + price + "]";
	}
	public OrderItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItemDTO(Long itemId, String itemName, Integer qty, Double price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.qty = qty;
		this.price = price;
	}
    
    
}
