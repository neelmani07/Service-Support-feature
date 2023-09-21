package com.service.support.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.service.support.entity.Otp;
import com.service.support.repository.OtpRepository;

@Service
public class NotificationService {
	
	@Autowired
	OtpRepository otpRepository;
	
	@Autowired
	JavaMailSender javaMailSender;
	

	public Otp verifyOtp(String email, String otpText) {
		Otp otp= otpRepository.findByEmailAndOtp(email, otpText).orElse(null);
		return otp;
	}
	public String generateOTPMessage() {
		return UUID.randomUUID().toString();
	}
	
	public boolean sendEmail(String to, String subject, String msg) {
		boolean sent = false;
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("manineel25@gmail.com");
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(msg);
		javaMailSender.send(mail);
		return sent;
		
	}
	
	public Otp registerOtpwithUser(String otpKey, String email){
		Otp otp = new Otp();
		otp.setEmail(email);
		otp.setOtp(otpKey);
		otp = otpRepository.save(otp);
		return null;
		
	}
	
	
}
