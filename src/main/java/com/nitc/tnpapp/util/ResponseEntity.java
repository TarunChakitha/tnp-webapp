package com.nitc.tnpapp.util;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public @Data class ResponseEntity {
	private String message;
	private int status;
	private Object object;
	private String httpResponse;
}
