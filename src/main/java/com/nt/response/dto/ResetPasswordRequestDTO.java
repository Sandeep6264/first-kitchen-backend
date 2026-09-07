package com.nt.response.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequestDTO {
	@NotBlank(message = "Token is required")
	private String token;

	@NotBlank(message = "Password Required")
	@Size(min = 8, max = 100)
	private String newPassword;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ResetPasswordRequestDTO [token=" + token + ", newPassword=" + newPassword + "]";
	}

}
