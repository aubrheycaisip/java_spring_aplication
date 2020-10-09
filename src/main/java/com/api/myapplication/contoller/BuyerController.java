package com.api.myapplication.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.myapplication.entity.Buyer;
import com.api.myapplication.request.UserSignupRequest;
import com.api.myapplication.sevices.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> buyerSignup(@RequestBody UserSignupRequest request){
		return new ResponseEntity<Buyer>(buyerService.buyerSignup(request), HttpStatus.OK);
	}
}
