package com.api.myapplication.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.myapplication.entity.Seller;
import com.api.myapplication.request.SellerRequest;
import com.api.myapplication.sevices.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> sellerSignup(@RequestBody SellerRequest request){
		return new ResponseEntity<Seller>(sellerService.sellerSignup(request), HttpStatus.OK);
	}
}
