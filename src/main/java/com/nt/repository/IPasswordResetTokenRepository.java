package com.nt.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.PasswordResetTokenEntity;
import com.nt.entity.UserEntity;

public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

	Optional<PasswordResetTokenEntity> findByTokenHashAndUsedFalse(String tokenHash);

	void deleteByUser(UserEntity user);

//	Optional<PasswordResetTokenEntity> findByUserIdAndUsedFalseAndExpiryDateAfter(Long userId, LocalDateTime now);
}
