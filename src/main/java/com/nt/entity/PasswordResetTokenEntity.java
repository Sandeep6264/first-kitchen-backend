package com.nt.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetTokenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resetId;

	@Column(nullable = false, unique = true, length = 64)
	private String tokenHash;

	@Column(nullable = false)
	private LocalDateTime expireDateTime;

	@Column(nullable = false)
	private boolean used = false;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "USER_ID", nullable = false)
	private UserEntity user;

	public Long getResetId() {
		return resetId;
	}

	public void setResetId(Long resetId) {
		this.resetId = resetId;
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public LocalDateTime getExpireDateTime() {
		return expireDateTime;
	}

	public void setExpireDateTime(LocalDateTime expireDateTime) {
		this.expireDateTime = expireDateTime;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "PasswordResetTokenEntity [resetId=" + resetId + ", tokenHash=" + tokenHash + ", expireTime="
				+ expireDateTime + ", user=" + user + "]";
	}

}
