package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserEntity;

public interface IUserDetailsRepo extends JpaRepository<UserEntity,Long> {
	public Optional<com.nt.entity.UserEntity> findByEmail(String email);

}
