package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties
public class LoginDTO {
	@NotBlank(message="Username is required")
	 private String userName;
	
	@NotBlank(message="Password is required")
	  private String password;
}
