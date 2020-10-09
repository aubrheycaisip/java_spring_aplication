package com.api.myapplication.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.myapplication.entity.Buyer;
import com.api.myapplication.repository.BuyerRepository;
import com.api.myapplication.request.UserSignupRequest;

@Service
public class BuyerService {

	@Autowired
	private BuyerRepository buyerRepository;
	
	public Buyer buyerSignup(UserSignupRequest request) {
		Buyer buyer = Buyer.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.address(request.getAddress())
				.username(request.getUsername())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
		buyerRepository.save(buyer);
		return buyer;
	}
}
