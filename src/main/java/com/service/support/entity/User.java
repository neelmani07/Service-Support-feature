package com.service.support.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String firstName;
	
	@Column(name = "last_name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "number")
	private String mobileNumber;

	@Column(name = "status")
	private boolean status;
	
	@Column(name = "isVerified")
	private boolean isVerified;
}
