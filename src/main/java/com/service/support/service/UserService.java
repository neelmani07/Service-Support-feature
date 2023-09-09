package com.service.support.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.support.response.UserResponse;
import com.service.support.entity.User;
import com.service.support.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
    UserRepository userRepository;

	public UserResponse getUserById(long supportId) {
		User user = userRepository.findByIdAndStatus(supportId, true);
		return new UserResponse(user);
	}

	public List<UserResponse> getAllUsers() {
		List<User> userList = userRepository.findAllByStatus(true);
		List<UserResponse> userResponseList = new ArrayList<UserResponse>();
		for(User user : userList) {
			userResponseList.add(new UserResponse(user));
		}
		return userResponseList;
	}


	public User saveUser(String firstName, String lastName, String email, String password, String mobNumber) {
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		if(lastName!=null)newUser.setLastName(lastName);
		if(mobNumber!=null)newUser.setMobileNumber(mobNumber);
		newUser.setStatus(true);
		newUser = userRepository.save(newUser);
		return newUser;
	}

	public UserResponse getUserByEmail(String email) {
		User user = userRepository.findByEmailAndStatus(email, true);
		return new UserResponse(user);
	}
	

	public void deleteUser(long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setStatus(false);
			userRepository.save(user);
		}
	}
}
