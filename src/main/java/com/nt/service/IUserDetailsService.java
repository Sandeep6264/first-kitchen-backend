package com.nt.service;

import org.springframework.security.core.userdetails.UserDetailsService;

//import com.nt.entity.UserDetails;
//import com.nt.entity.UserEntity;
import com.nt.requestDTO.UserInfoDTO;

public interface IUserDetailsService extends UserDetailsService {
	
	public UserInfoDTO registerUser(UserInfoDTO userInfoDTO);

}
