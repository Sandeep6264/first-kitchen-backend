package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
	
	private Double halfPrice;
}
