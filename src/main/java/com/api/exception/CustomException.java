package com.api.exception;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CustomException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
    private int code;
	
	public CustomException() {
		
	}
	
	public CustomException(String message, int code) {
		this.message = message;
		this.code = code;
	}

}
