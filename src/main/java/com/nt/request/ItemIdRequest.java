package com.nt.request;

import jakarta.validation.constraints.NotBlank;

public class ItemIdRequest {
	@NotBlank(message="Item id is required")
	private Long itemId;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "ItemIdRequest [itemId=" + itemId + "]";
	}

	public ItemIdRequest(@NotBlank(message = "Item id is required") Long itemId) {
		super();
		this.itemId = itemId;
	}

	public ItemIdRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
