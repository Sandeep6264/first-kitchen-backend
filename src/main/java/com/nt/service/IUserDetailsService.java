package com.nt.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nt.entity.UserEntity;
import com.nt.request.UserInfoRequest;

public interface IUserDetailsService extends UserDetailsService {
	
	public UserInfoRequest registerUser(UserInfoRequest userInfoRequest);
	public  UserEntity getUserDetails(String username);
	public String forgetPassword (String emailId);
	public void resetPassword(String token, String newPassword);

}
