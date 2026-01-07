package com.nt.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.UserEntity;
import com.nt.requestDTO.LoginDTO;
import com.nt.requestDTO.UserInfoDTO;
import com.nt.responseDTO.AuthResponseDTO;
import com.nt.service.IUserDetailsService;
import com.nt.util.JwtUtil;
import com.nt.util.ResponseUtil;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationProvider authenticationProvider;
	@Autowired
		private IUserDetailsService userService;
		
	@Autowired
		private JwtUtil jwtService;
		
	@Autowired
		private AuthenticationManager authenticationManager;

    UserController(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
		
		@PostMapping("/register")
		public ResponseEntity<?> registerUser(@RequestBody UserInfoDTO userInfo){
			UserInfoDTO responseUserInfo =userService.registerUser(userInfo);	
			return ResponseUtil.success(responseUserInfo, "User register successfully");
		}
		
		@PostMapping("/login")
		public ResponseEntity<?> loginUser(@RequestBody LoginDTO authRequest){
			Authentication authentication=authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
				);
			Object principal= authentication.getPrincipal();
			if(authentication.isAuthenticated()) {
				String token=jwtService.generateToken(authRequest.getUserName());
				User user=(User) principal;
				AuthResponseDTO authResponseDTO=new AuthResponseDTO();
				authResponseDTO.setAccessToken(token);
				UserEntity userEntity=userService.getUserDetails(authRequest.getUserName());
				authResponseDTO.setUserEmail(userEntity.getEmail());
				authResponseDTO.setUserGender(userEntity.getGender());
				authResponseDTO.setUserName(userEntity.getUserName());
				authResponseDTO.setUserId(userEntity.getUid());
				
				Set<String> roles = new HashSet<>();
				user.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
				authResponseDTO.setAccessRole(roles);
				return ResponseUtil.success(authResponseDTO, "User login successfully");
			}else {
				return ResponseUtil.error(404, "Invalid user request!");

			}
	
		}

}
