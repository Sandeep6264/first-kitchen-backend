package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@JsonIgnoreProperties
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [userId=" + userId + ", fullName=" + fullName + ", emailAddress=" + emailAddress
				+ ", phoneNumber=" + phoneNumber + ", deliveryAddress=" + deliveryAddress + ", password=" + password
				+ ", gender=" + gender + ", role=" + role + "]";
	}

	public UserInfoDTO(Long userId, @NotBlank(message = "Full name is required") String fullName,
			@NotBlank(message = "Email address is required") @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email") String emailAddress,
			@NotBlank(message = "Phone is required") String phoneNumber,
			@NotBlank(message = "Delivery Address is required") String deliveryAddress,
			@NotBlank(message = "Phone is required") @Size(min = 8, max = 16, message = "Password must be 8 - 16  characters") String password,
			@NotBlank(message = "Geneder is required") String gender,
			@NotBlank(message = "Role is required") String role) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.deliveryAddress = deliveryAddress;
		this.password = password;
		this.gender = gender;
		this.role = role;
	}

	public UserInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}