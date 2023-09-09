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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.support.entity.Support;
import com.service.support.entity.User;
import com.service.support.response.BaseApiResponse;
import com.service.support.response.SupportResponse;
import com.service.support.service.SupportService;


@RestController
public class SupportController {

	@Autowired
    SupportService supportService;
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/create support ticket")
	public ResponseEntity<?> saveSupportDetails(@RequestParam(required = true) String topic,
			@RequestParam(required = false,name = "description") String description,
			@RequestParam(required = false,name = "type") String type,
			@RequestParam(required = false,name = "User") long userId,
            @RequestPart(required = false, name = "file") MultipartFile imageFile,
			HttpServletRequest request) throws Exception {
		
		
		Support supportEntity = supportService.saveSupport(topic, description, type, userId, imageFile);
		
		BaseApiResponse baseApiResponse= new BaseApiResponse(supportEntity.getId(), "Support Ticket created successfully.");
		
		//BaseApiResponse baseApiResponse = BaseApiResponse((Object)supportEntity.getId(), "Support Ticket created successfully.");
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/get a support ticket")
	public ResponseEntity<BaseApiResponse> getSupprtById(@RequestParam(required = true) long supportId,
			HttpServletRequest request) {
		SupportResponse response=  supportService.getSupportById(supportId);
		BaseApiResponse baseApiResponse = new BaseApiResponse(response, "Support Ticket fetched successfully.");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);

	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/get all support ticket")
	public ResponseEntity<BaseApiResponse> getAllTickets(@RequestParam(required = true) Integer size,
			@RequestParam(required = true) Integer page, HttpServletRequest request) {
		List<SupportResponse> response=	supportService.getAllTickets();
		BaseApiResponse baseApiResponse = new BaseApiResponse(response, "All Support Ticket fetched successfully.");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/update support ticket")
	public ResponseEntity<BaseApiResponse> updateTicketStatus(@RequestParam(required=true) long supportId, @RequestParam(required=true) boolean status,
			HttpServletRequest request) {

		supportService.updateSupportStatus(supportId, status);
		BaseApiResponse baseApiResponse = new BaseApiResponse(supportId, "Support Ticket updated successfully.");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);

	}
	
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/delete support ticket")
	public ResponseEntity<BaseApiResponse> deleteTicket(@RequestParam(required=true) long supportId) {

		supportService.deleteSupport(supportId);
		BaseApiResponse baseApiResponse = new BaseApiResponse("Ticket deleted successfully");
		return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);
	}

}
