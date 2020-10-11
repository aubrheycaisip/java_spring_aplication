package com.api.myapplication.sevices;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.exception.CustomException;
import com.api.myapplication.emuns.RoleName;
import com.api.myapplication.entity.Admin;
import com.api.myapplication.entity.Buyer;
import com.api.myapplication.entity.Seller;
import com.api.myapplication.entity.User;
import com.api.myapplication.repository.AdminRepository;
import com.api.myapplication.repository.BuyerRepository;
import com.api.myapplication.repository.RoleRepository;
import com.api.myapplication.repository.SellerRepository;
import com.api.myapplication.repository.UserRepository;
import com.api.myapplication.request.UserSignupRequest;
import com.api.myapplication.security.MyUserDetails;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;

	public User save(UserSignupRequest request) {
		User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.username(request.getUsername()).password(request.getPassword())
				.roles(Arrays.asList(roleRepository.findByAuthority(RoleName.valueOf(request.getRoles())))).build();
		return userRepository.save(user);

	}
	
	public Admin currentAdmin(MyUserDetails userDetails) throws CustomException {
		return adminRepository.findById(userDetails.getId()).orElseThrow(() -> new CustomException("admin not found", 10));
	}
	
	public Buyer currentBuyer(MyUserDetails userDetails) throws CustomException {
		return buyerRepository.findById(userDetails.getId()).orElseThrow(() -> new CustomException("buyer not found", 10));
	}
	
	public Seller currentSeller(MyUserDetails userDetails) throws CustomException {
		return sellerRepository.findById(userDetails.getId()).orElseThrow(() -> new CustomException("seller not found", 10));
	}
	
	public User currentUser(MyUserDetails userDetails) throws CustomException {
		return userRepository.findById(userDetails.getId()).orElseThrow(() -> new CustomException("user not found", 10));
	}
}
