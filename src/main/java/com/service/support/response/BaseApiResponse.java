package com.service.support.response;

import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class BaseApiResponse {
	
	private ResponseStatus responseStatus;
	private Object responseData;
	private String message;
	
	public BaseApiResponse() {
		this.responseData = null;
	}
	
	public BaseApiResponse(Object responseData) {
		this.responseData = responseData;
	}
	
	public BaseApiResponse(Object responseData, String message) {
		this.responseData = responseData;
		this.message = message;
	}

	public BaseApiResponse(ResponseStatus responseStatus, Object responseData, String message) {
		super();
		this.responseStatus = responseStatus;
		this.responseData = responseData;
		this.message = message;
	}
	
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
