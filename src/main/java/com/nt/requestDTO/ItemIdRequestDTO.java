package com.nt.requestDTO;

import jakarta.validation.constraints.NotBlank;

public class ItemIdRequestDTO {
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
		return "ItemIdRequestDTO [itemId=" + itemId + "]";
	}

	public ItemIdRequestDTO(@NotBlank(message = "Item id is required") Long itemId) {
		super();
		this.itemId = itemId;
	}

	public ItemIdRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
