package com.api.myapplication.request;

import lombok.Data;

@Data
public class UserSignupRequest {

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String address;
	private String password;
	private String roles;
}
