package com.nt.responseDTO;

import java.util.Set;


public class AuthResponseDTO {
    private String accessToken;
    private Set<String> accessRole;
    private String userEmail;
    private String userName;
    private String userGender;
    private Long userId;

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
		return "AuthResponseDTO [accessToken=" + accessToken + ", accessRole=" + accessRole + ", userEmail=" + userEmail
				+ ", userName=" + userName + ", userGender=" + userGender + "]";
	}
	public AuthResponseDTO(String accessToken, Set<String> accessRole, String userEmail, String userName,
			String userGender,Long userId) {
		super();
		this.accessToken = accessToken;
		this.accessRole = accessRole;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userGender = userGender;
		this.userId=userId;
	}
	public AuthResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userGender
	 */
	public String getUserGender() {
		return userGender;
	}
	/**
	 * @param userGender the userGender to set
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
