package com.nt.service;

public interface IEmailService {
	void sendPasswordResetEmail(String email, String resetLink);
}
