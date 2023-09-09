package com.service.support.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.service.support.entity.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class UserResponse {

	public User user;
	
	public UserResponse(User user){
		this.user = user;
	}

}