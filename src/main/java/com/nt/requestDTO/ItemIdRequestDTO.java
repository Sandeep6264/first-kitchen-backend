package com.nt.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ItemIdRequestDTO {
	@NotBlank(message="Item id is required")
	private Long itemId;
}
