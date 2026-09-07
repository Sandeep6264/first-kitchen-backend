package com.nt.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.common.CustomUserDetails;
import com.nt.entity.UserEntity;
import com.nt.request.ForgotPasswordRequest;
import com.nt.request.LoginInfoRequest;
import com.nt.request.UserInfoRequest;
import com.nt.response.dto.AuthResponseDTO;
import com.nt.response.dto.ResetPasswordRequestDTO;
import com.nt.service.IUserDetailsService;
import com.nt.util.JwtUtil;
import com.nt.util.ResponseUtil;

@RestController
@RequestMapping("/auth")
public class UserController {

	private final AuthenticationProvider authenticationProvider;

	private final IUserDetailsService userService;

	private final JwtUtil jwtService;

	private final AuthenticationManager authenticationManager;

	UserController(AuthenticationProvider authenticationProvider, IUserDetailsService userService, JwtUtil jwtService,
			AuthenticationManager authenticationManager) {
		this.authenticationProvider = authenticationProvider;
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserInfoRequest userInfo) {
		UserInfoRequest responseUserInfo = userService.registerUser(userInfo);
		return ResponseUtil.success(responseUserInfo, "User register successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginInfoRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		Object principal = authentication.getPrincipal();
		if (authentication.isAuthenticated()) {
			String token = jwtService.generateToken(authRequest.getUserName());
			CustomUserDetails user = (CustomUserDetails) principal;
			AuthResponseDTO authResponseDTO = new AuthResponseDTO();
			authResponseDTO.setAccessToken(token);
			UserEntity userEntity = userService.getUserDetails(authRequest.getUserName());
			authResponseDTO.setUserEmail(userEntity.getEmail());
			authResponseDTO.setUserGender(userEntity.getGender());
			authResponseDTO.setUserName(userEntity.getUserName());
			authResponseDTO.setUserId(userEntity.getUid());

			Set<String> roles = new HashSet<>();
			user.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
			authResponseDTO.setAccessRole(roles);
			return ResponseUtil.success(authResponseDTO, "User login successfully");
		} else {
			return ResponseUtil.error(401, "Invalid user request!");

		}

	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgetPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		String message = userService.forgetPassword(forgotPasswordRequest.getEmail());
		return ResponseUtil.success(null, message);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordDTO) {
		userService.resetPassword(resetPasswordDTO.getToken(), resetPasswordDTO.getNewPassword());
		return ResponseUtil.success(null, "Password Reset successfully");
	}

}
