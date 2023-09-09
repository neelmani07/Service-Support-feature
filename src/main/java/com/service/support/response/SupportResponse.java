package com.service.support.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

import com.service.support.entity.Support;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class SupportResponse {

	Support support;
	
	public SupportResponse(Support support){
		this.support = support;
	}

}
