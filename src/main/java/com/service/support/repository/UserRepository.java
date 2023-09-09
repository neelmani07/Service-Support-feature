package com.service.support.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.support.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findById(Long userId);

	// Find all users
	List<User> findAll();
	
    // Find users by first name
    List<User> findByFirstName(String firstName);

    // Find users by last name
    List<User> findByLastName(String lastName);

    // Find users by mobile number
    List<User> findByMobileNumber(String mobileNumber);

	User findByIdAndStatus(long supportId, boolean status);

	List<User> findAllByStatus(boolean b);

	User findByEmailAndStatus(String email, boolean b);

}