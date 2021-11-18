package com.nitc.tnpapp.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserAlreadyExistsException extends Exception {

	private String message;
}
