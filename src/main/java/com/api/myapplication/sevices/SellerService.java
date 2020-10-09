package com.api.myapplication.sevices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.myapplication.entity.Product;
import com.api.myapplication.entity.Seller;
import com.api.myapplication.repository.ProductRepository;
import com.api.myapplication.repository.SellerRepository;
import com.api.myapplication.request.SellerRequest;

@Service
public class SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Seller sellerSignup(SellerRequest request) {
		Seller seller = Seller.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.address(request.getAddress())
				.username(request.getUsername())
				.email(request.getEmail())
				.password(request.getPassword()).build();
		sellerRepository.save(seller);
		List<Product> products = request.getProducts().stream()
				.map(x -> new Product(x.getProductName(), x.getAmount(), x.getQuantity(), seller))
				.collect(Collectors.toList());
		productRepository.saveAll(products);
		seller.setProducts(products);
		return seller;
	}
}
