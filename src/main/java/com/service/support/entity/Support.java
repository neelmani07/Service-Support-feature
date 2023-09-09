package com.service.support.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.service.support.enums.SupportStatus;
import com.service.support.enums.SupportType;

@Entity
public class Support {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "topic")
	private String topic;

	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10) // Set the appropriate length for your enum values
	private SupportStatus status;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 10) // Set the appropriate length for your enum values
	private SupportType type;
	
	
	@Column(name = "file_path")
    private String attachedFilePath;
	
	
	@ManyToOne(optional = true)
    private User user;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "active")
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public SupportStatus getStatus() {
		return status;
	}

	public void setStatus(SupportStatus status) {
		this.status = status;
	}

	public SupportType getType() {
		return type;
	}

	public void setType(SupportType type) {
		this.type = type;
	}

	public Support() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getAttachedFilePath() {
		return attachedFilePath;
	}

	public void setAttachedFilePath(String attachedFilePath) {
		this.attachedFilePath = attachedFilePath;
	}

	public Support(Long id, String topic, String description, SupportStatus status, SupportType type,
			String filepath, boolean active) {
		super();
		this.id = id;
		this.topic = topic;
		this.description = description;
		this.status = status;
		this.type = type;
		this.active = active;
		this.attachedFilePath = filepath;
	}
	
}
