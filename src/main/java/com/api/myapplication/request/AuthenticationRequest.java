package com.api.myapplication.request;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String username;
	private String password;
}