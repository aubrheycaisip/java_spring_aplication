package com.api.myapplication.response;

public class AuthenticationResponse {

	public final String jwt;
	
	public AuthenticationResponse(String jwt) {
		this.jwt =jwt;
	}
}
