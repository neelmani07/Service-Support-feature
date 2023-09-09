package com.service.support.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.support.entity.Support;
import com.service.support.entity.User;
import com.service.support.repository.SupportRepository;
import com.service.support.repository.UserRepository;
import com.service.support.response.SupportResponse;
import com.service.support.enums.SupportType;
import com.service.support.enums.SupportStatus;


@Service
public class SupportService {
	
	@Value("${upload.dir}")
    private String uploadDirectory;
	
	@Autowired
    SupportRepository supportRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StorageService storageService;


	public Support saveSupport(String topic, String description, String type, long userId, MultipartFile imageFile ) throws IOException {
		Support newSupport = new Support();
		
		if (StringUtils.isNotBlank(topic)) {
			newSupport.setTopic(topic);
			newSupport.setDescription(description);
			if (StringUtils.isNotBlank(type)) {
				if (type.equalsIgnoreCase(SupportType.APP.toString())) {
					newSupport.setType(SupportType.APP);
				} else if (type.equalsIgnoreCase(SupportType.NETWORK.toString())) {
					newSupport.setType(SupportType.NETWORK);
				} else if (type.equalsIgnoreCase(SupportType.PAYMENT.toString())) {
					newSupport.setType(SupportType.PAYMENT);
				} else {
					newSupport.setType(SupportType.OTHER);
				}
			}
			newSupport.setStatus(SupportStatus.PENDING);
			if(imageFile != null) {
		        String fileUrl =  storageService.uploadFile(imageFile);
		        /*String fileName = imageFile.getOriginalFilename();
		        String imageFilePath = uploadDirectory + fileName;
		        imageFile.transferTo(new File(imageFilePath));*/
		        newSupport.setAttachedFilePath(fileUrl);
			}
			Optional<User> userOptional = userRepository.findById(userId);
			if (userOptional.isPresent()) newSupport.setUser(userOptional.get());
			
			newSupport.setActive(true);

			newSupport = supportRepository.save(newSupport);
		}
		return newSupport;
	}
	
	public SupportResponse getSupportById(long supportId) {

		Support support = supportRepository.findByIdAndActive(supportId, true);
		return new SupportResponse(support);
	}

	
	public List<SupportResponse> getAllTickets() {
		List<Support> supportList = supportRepository.findAllByActive(true);
		List<SupportResponse> supportResponseList = new ArrayList<SupportResponse>();
		for(Support support : supportList) {
			supportResponseList.add(new SupportResponse(support));
		}
		return supportResponseList;
	}


	public void updateSupportStatus(long supportId, boolean status) {
		Support support = supportRepository.findByIdAndActive(supportId, status);
		if (status) {
			support.setStatus(SupportStatus.RESOLVED);
			supportRepository.save(support);
		}
		
	}
	
	
	public void deleteSupport(long supportId) {
		Optional<Support> supportOptional = supportRepository.findById(supportId);

		if (supportOptional.isPresent()) {
			Support support = supportOptional.get();
			support.setActive(false);
			supportRepository.save(support);
		}
	}
}
	