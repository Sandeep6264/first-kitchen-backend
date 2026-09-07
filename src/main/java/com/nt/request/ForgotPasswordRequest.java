package com.nt.request;

import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
	@NotBlank(message="Email Id required")
		private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RequestRestCOdeDTO [email=" + email + "]";
	}
	
	
}
