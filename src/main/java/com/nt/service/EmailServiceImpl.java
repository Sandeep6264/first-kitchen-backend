package com.nt.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

	private final JavaMailSender mailSender;

	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendPasswordResetEmail(String email, String resetLink) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(email);
			helper.setSubject("Reset your password");

			String html = """
				    <!DOCTYPE html>
				    <html>
				    <head>
				      <meta charset="UTF-8">
				      <meta name="viewport" content="width=device-width, initial-scale=1.0">
				      <title>First Kitchen – Password Reset</title>
				    </head>
				    <body style="margin:0; padding:0; background:#f8f4f4; font-family: 'Inter', Arial, Helvetica, sans-serif; -webkit-font-smoothing: antialiased;">
				    
				      <table width="100%%" cellpadding="0" cellspacing="0" border="0" style="background:#f8f4f4; padding:40px 20px;">
				        <tr>
				          <td align="center" style="padding:0;">
				    
				            <table width="600" cellpadding="0" cellspacing="0" border="0" style="max-width:600px; width:100%%; background:#ffffff; border-radius:16px; overflow:hidden; box-shadow:0 8px 30px rgba(0,0,0,0.06);">
				    
				              <tr>
				                <td style="background:#E54304; padding:32px 24px 28px; text-align:center;">
				                  <h1 style="margin:0; font-size:30px; font-weight:700; color:#ffffff; letter-spacing:-0.3px; display:flex; align-items:center; justify-content:center; gap:8px;">
				                    <span style="font-size:32px;">🍽</span> First Kitchen
				                  </h1>
				                  <p style="margin:8px 0 0; font-size:16px; font-weight:400; color:rgba(255,255,255,0.9); letter-spacing:0.2px;">
				                    Fresh Food Delivered Fast
				                  </p>
				                </td>
				              </tr>
				    
				              <tr>
				                <td style="padding:40px 36px 32px;">
				    
				                  <h2 style="color:#E54304; font-size:24px; font-weight:700; margin:0 0 16px; letter-spacing:-0.2px;">
				                    Reset Your Password
				                  </h2>
				    
				                  <p style="color:#444444; line-height:1.7; font-size:16px; margin:0 0 18px;">
				                    We received a request to reset the password for your First Kitchen account.
				                    If you made this request, simply click the button below to choose a new password.
				                  </p>
				    
				                  <p style="color:#444444; line-height:1.7; font-size:16px; margin:0 0 28px;">
				                    For your security, this link will expire in <strong>15 minutes</strong>.
				                  </p>
				    
				                  <div style="text-align:center; margin:32px 0 36px;">
				                    <a href="%s" style="
				                      background:#E54304;
				                      color:#ffffff;
				                      padding:16px 44px;
				                      text-decoration:none;
				                      border-radius:60px;
				                      font-size:18px;
				                      font-weight:600;
				                      display:inline-block;
				                      letter-spacing:0.3px;
				                      box-shadow:0 4px 10px rgba(229,67,4,0.25);
				                      border:1px solid #d03c03;
				                      ">
				                      Reset Password
				                    </a>
				                  </div>
				    
				                  <div style="background:#FFF4F0; border-left:5px solid #E54304; padding:16px 20px; border-radius:6px; margin:0 0 28px;">
				                    <p style="margin:0; color:#5a4a44; font-size:15px; line-height:1.6;">
				                      ⏳ This password reset link will expire in <strong>15 minutes</strong>.
				                    </p>
				                  </div>
				    
				                  <p style="color:#666666; line-height:1.7; font-size:15px; margin:0 0 8px;">
				                    If you didn't request a password reset, you can safely ignore this email.
				                    Your account remains secure and no action is required.
				                  </p>
				    
				                  <p style="color:#888888; font-size:14px; margin-top:20px; border-top:1px solid #f0eaea; padding-top:20px;">
				                    <span style="font-weight:600; color:#E54304;">First Kitchen</span> · Delicious food, delivered to your doorstep.
				                  </p>
				    
				                </td>
				              </tr>
				    
				              <tr>
				                <td style="background:#f8f4f4; text-align:center; padding:22px 24px; color:#9a8a84; font-size:13px; border-top:1px solid #eee8e6;">
				                  <span style="color:#E54304; font-weight:600;">First Kitchen</span><br>
				                  <span style="color:#887a74;">Delicious food, delivered to your doorstep.</span>
				                  <br><br>
				                  <span style="color:#b0a09a;">© 2026 First Kitchen. All Rights Reserved.</span>
				                  <br>
				                  <span style="color:#b8aaa4; font-size:12px;">This email was sent to you as a registered user.</span>
				                </td>
				              </tr>
				    
				            </table>
				    
				            <div style="height:6px; font-size:0; line-height:0;">&nbsp;</div>
				    
				          </td>
				        </tr>
				      </table>
				    
				    </body>
				    </html>
				    """.formatted(resetLink);

				helper.setText(html, true);

			mailSender.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException("Failed to send password reset email", e);
		}

	}

}
