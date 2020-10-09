package com.api.myapplication.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.myapplication.entity.User;
import com.api.myapplication.request.AuthenticationRequest;
import com.api.myapplication.request.UserSignupRequest;
import com.api.myapplication.response.AuthenticationResponse;
import com.api.myapplication.sevices.UserService;
import com.api.myapplication.util.JwtUtil;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/hello")
	public String getUser() {
		return "hello";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest request) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt),HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody UserSignupRequest request){
		return new ResponseEntity<User>(userService.save(request), HttpStatus.OK);
	}
}
