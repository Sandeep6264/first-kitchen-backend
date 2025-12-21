package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
	private Long userId;
	
	@NotBlank(message="Full name is required")
	private String fullName;
	
	@NotBlank(message="Email address is required")
	@Pattern(
			  regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
			  message = "Invalid email"
			)
	private String emailAddress;
	
	@NotBlank(message="Phone is required")
	private String phoneNumber;
	
	@NotBlank(message="Delivery Address is required")
	private String deliveryAddress;
	
	@NotBlank( message="Phone is required")
	@Size(min = 8, max = 16, message = "Password must be 8 - 16  characters")
	private String password;
	
	@NotBlank( message="Geneder is required")
	private String gender;
	
	@NotBlank(message="Role is required")
	private String role;
	
}