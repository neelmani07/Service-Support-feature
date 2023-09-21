package com.service.support.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.support.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

	Optional<Otp> findByEmailAndOtp(String otp, String email);
	
}
