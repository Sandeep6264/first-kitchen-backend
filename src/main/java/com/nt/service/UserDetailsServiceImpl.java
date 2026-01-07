package com.nt.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.common.CustomUserDetails;
import com.nt.entity.UserEntity;
import com.nt.repository.IUserDetailsRepo;
import com.nt.requestDTO.UserInfoDTO;


@Service("UserService")
public class UserDetailsServiceImpl implements IUserDetailsService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private IUserDetailsRepo userDetailsRepo;
	
	@Override
	public UserInfoDTO registerUser(UserInfoDTO userInfoDTO) {
		UserEntity userDetails=new UserEntity();
		userDetails.setPassword(encoder.encode(userInfoDTO.getPassword()));
		 userDetails.setUserName(userInfoDTO.getFullName());
		 userDetails.setEmail(userInfoDTO.getEmailAddress());
		 userDetails.setGender(userInfoDTO.getGender());
		 userDetails.setMobileNumber(userInfoDTO.getPhoneNumber());
		 userDetails.setAddress(userInfoDTO.getDeliveryAddress());
		 
		 userDetails.setRoles(Set.of(userInfoDTO.getRole()));
		UserEntity savedUser= userDetailsRepo.save(userDetails);
		
		UserInfoDTO responseDTO=new UserInfoDTO();
		responseDTO.setUserId(savedUser.getUid());
		responseDTO.setFullName(savedUser.getUserName());
		responseDTO.setEmailAddress(savedUser.getEmail());
		responseDTO.setGender(savedUser.getGender());
		responseDTO.setPhoneNumber(savedUser.getMobileNumber());
		responseDTO.setDeliveryAddress(savedUser.getAddress());
		return responseDTO;
		
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.nt.entity.UserEntity> opt=userDetailsRepo.findByEmail(username);
		if(opt.isEmpty()) 
			throw new IllegalArgumentException("Invalid Username / Passoword");
		else {
			com.nt.entity.UserEntity  details=opt.get();
			return new CustomUserDetails(details);
		}
	}

	@Override
	public UserEntity getUserDetails(String username) {
		return userDetailsRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with email: " + username));
	}
	
	
	
	




	
}
