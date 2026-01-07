package com.nt.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties
public class LoginDTO {
	@NotBlank(message="Username is required")
	 private String userName;
	
	@NotBlank(message="Password is required")
	  private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [userName=" + userName + ", password=" + password + "]";
	}

	public LoginDTO(@NotBlank(message = "Username is required") String userName,
			@NotBlank(message = "Password is required") String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
