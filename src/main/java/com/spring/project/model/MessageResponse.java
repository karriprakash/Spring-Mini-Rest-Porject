package com.spring.project.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageResponse {

	private String module;

	private String message;
	
	private String status;
	
	private String date;
	
}
