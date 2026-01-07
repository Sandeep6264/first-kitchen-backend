package com.nt.responseDTO;

import java.util.Set;


public class AuthResponseDTO {
    private String accessToken;
    private Set<String> accessRole;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Set<String> getAccessRole() {
		return accessRole;
	}
	public void setAccessRole(Set<String> accessRole) {
		this.accessRole = accessRole;
	}
	@Override
	public String toString() {
		return "AuthResponseDTO [accessToken=" + accessToken + ", accessRole=" + accessRole + "]";
	}
	public AuthResponseDTO(String accessToken, Set<String> accessRole) {
		super();
		this.accessToken = accessToken;
		this.accessRole = accessRole;
	}
	public AuthResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	

}
