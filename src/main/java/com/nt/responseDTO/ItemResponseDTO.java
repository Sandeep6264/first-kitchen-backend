package com.nt.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
}
