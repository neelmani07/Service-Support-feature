package com.service.support.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.support.entity.User;
import com.service.support.response.BaseApiResponse;
import com.service.support.response.UserResponse;
import com.service.support.service.UserService;

@RestController
public class UserController {
	
	@Autowired
    UserService userService;

	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/create user")
	public ResponseEntity<?> saveSupportDetails(
			@RequestParam(required = true,name = "first name") String firstName,
			@RequestParam(required = false,name = "last name") String lastName,
			@RequestParam(required = true,name = "email-id") String email,
			@RequestParam(required = true,name = "password") String password,
			@RequestParam(required = false,name = "mobile numbetr") String mobNumber,
			HttpServletRequest request) throws Exception {
		User userEntity = userService.saveUser(firstName, lastName, email, password, mobNumber);
		BaseApiResponse baseApiResponse= new BaseApiResponse(userEntity.getId(), "User created successfully.");		
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/get a user detail by id.")
	public ResponseEntity<BaseApiResponse> getSupprtById(@RequestParam(required = true) long userId,
			HttpServletRequest request) {
		UserResponse response = userService.getUserById(userId);
		BaseApiResponse baseApiResponse = new BaseApiResponse(response, "user fetched successfully.");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);

	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/get a user detail by email.")
	public ResponseEntity<BaseApiResponse> getUserByEmailAndActive(@RequestParam(required = true) String email,
			HttpServletRequest request) {
		UserResponse response = userService.getUserByEmail(email);
		BaseApiResponse baseApiResponse = new BaseApiResponse(response.user.getId(), "user fetched successfully.");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);

	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/get all user")
	public ResponseEntity<BaseApiResponse> getAllUser(@RequestParam(required = true) Integer size,
			@RequestParam(required = true) Integer page, HttpServletRequest request) {
		List<UserResponse> response = userService.getAllUsers();
		BaseApiResponse baseApiResponse = new BaseApiResponse(response, "All Users fetched successfully.");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/delete a user")
	public ResponseEntity<BaseApiResponse> deleteTicket(@RequestParam(required=true) long userId) {
		userService.deleteUser(userId);
		BaseApiResponse baseApiResponse = new BaseApiResponse("User deleted successfully");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);
	}

}