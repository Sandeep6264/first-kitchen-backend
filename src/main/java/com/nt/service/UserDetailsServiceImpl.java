package com.nt.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.common.CustomUserDetails;
import com.nt.entity.PasswordResetTokenEntity;
import com.nt.entity.UserEntity;
import com.nt.exception.InvalidTokenException;
import com.nt.repository.IPasswordResetTokenRepository;
import com.nt.repository.IUserDetailsRepo;
import com.nt.request.UserInfoRequest;

import jakarta.transaction.Transactional;

@Service("UserService")
public class UserDetailsServiceImpl implements IUserDetailsService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IUserDetailsRepo userDetailsRepo;

	@Autowired
	private IPasswordResetTokenRepository passwordResetTokenRepository;

	@Value("${front-end-url}")
	private String frontEndUrl;

	@Autowired
	private IEmailService emailService;

	@Override
	public UserInfoRequest registerUser(UserInfoRequest userInfoRequest) {
		UserEntity userDetails = new UserEntity();
		userDetails.setPassword(encoder.encode(userInfoRequest.getPassword()));
		userDetails.setUserName(userInfoRequest.getFullName());
		userDetails.setEmail(userInfoRequest.getEmailAddress());
		userDetails.setGender(userInfoRequest.getGender());
		userDetails.setMobileNumber(userInfoRequest.getPhoneNumber());
		userDetails.setAddress(userInfoRequest.getDeliveryAddress());

		userDetails.setRoles(Set.of(userInfoRequest.getRole()));
		UserEntity savedUser = userDetailsRepo.save(userDetails);

		UserInfoRequest responseDTO = new UserInfoRequest();
		responseDTO.setUserId(savedUser.getUid());
		responseDTO.setFullName(savedUser.getUserName());
		responseDTO.setEmailAddress(savedUser.getEmail());
		responseDTO.setGender(savedUser.getGender());
		responseDTO.setPhoneNumber(savedUser.getMobileNumber());
		responseDTO.setDeliveryAddress(savedUser.getAddress());
		return responseDTO;

	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<com.nt.entity.UserEntity> opt = userDetailsRepo.findByEmail(username);
		if (opt.isEmpty())
			throw new IllegalArgumentException("Invalid Username / Passoword");
		else {
			com.nt.entity.UserEntity details = opt.get();
			return new CustomUserDetails(details);
		}
	}

	@Override
	public UserEntity getUserDetails(String username) {
		return userDetailsRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
	}

	// Rrequest reset Code on given email Id
	@Override
	@Transactional
	public String forgetPassword(String emailId) {
		Optional<UserEntity> optionalUser = userDetailsRepo.findByEmail(emailId);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found with email: " + emailId);
		}
		UserEntity userEntity = optionalUser.get();
//		Optional<PasswordResetTokenEntity>  optionalReset =passwordResetTokenRepository.findByUserIdAndUsedFalseAndExpiryDateAfter(userEntity.getUid(), LocalDateTime.now());
//		if(optionalReset.isEmpty()) {
		passwordResetTokenRepository.deleteByUser(userEntity);
		String rawToken = generateToken();
		String tokenHash = hashToken(rawToken);
		PasswordResetTokenEntity resetToken = new PasswordResetTokenEntity();

		resetToken.setTokenHash(tokenHash);
		resetToken.setUser(userEntity);

		resetToken.setExpireDateTime(LocalDateTime.now().plusMinutes(15));
		resetToken.setUsed(false);
		passwordResetTokenRepository.save(resetToken);

		String resetLink = frontEndUrl + "/reset-password?token=" + URLEncoder.encode(rawToken, StandardCharsets.UTF_8);

		emailService.sendPasswordResetEmail(userEntity.getEmail(), resetLink);
		return "If an account exists for this email, a password reset link has been sent.";
		
	}

	@Transactional
	public void resetPassword(String token, String newPassword) {

		String tokenHash = hashToken(token);

		Optional<PasswordResetTokenEntity> passwordOptional = passwordResetTokenRepository
				.findByTokenHashAndUsedFalse(tokenHash);

		if (passwordOptional.isEmpty()) {
			throw new InvalidTokenException("Invalid or expired token");
		}

		PasswordResetTokenEntity resetToken = passwordOptional.get();

		// Check expiration
		if (resetToken.getExpireDateTime().isBefore(LocalDateTime.now())) {

			throw new InvalidTokenException("Invalid or expired token");
		}
		UserEntity user = resetToken.getUser();

		user.setPassword(encoder.encode(newPassword));
		resetToken.setUsed(true);
		userDetailsRepo.save(user);
		passwordResetTokenRepository.save(resetToken);
	}

	// generate token for reset link
	private String generateToken() {
		byte[] randomBytes = new byte[32];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(randomBytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	}

	// Hash generated token to SHA-256 secure
	private String hashToken(String token) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
			return HexFormat.of().formatHex(hash);

		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 algorithm not available", e);
		}
	}
}
