package com.api.myapplication.sevices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.api.exception.CustomException;
import com.api.myapplication.entity.Product;
import com.api.myapplication.entity.Seller;
import com.api.myapplication.repository.ProductRepository;
import com.api.myapplication.request.ProductRequest;
import com.api.myapplication.response.ProductResponse;
import com.api.myapplication.security.MyUserDetails;

@Service
public class ProductService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductResponse> addProducts(MyUserDetails userDetails, List<ProductRequest> requestList) throws Exception {
		
		Seller seller = userService.currentSeller(userDetails);
		
		if(requestList.size() <= 0) {
			throw new CustomException("there are no product to add", 10);
		}
		List<Product> products = requestList.stream()
				.map(product -> new Product(
						product.getProductName(), 
						product.getAmount(), 
						product.getQuantity(), 
						product.getImageDisplayUrl(), 
						seller))
				.collect(Collectors.toList());
		productRepository.saveAll(products);
		List<ProductResponse> productReponseList = products.stream().map(product -> new ProductResponse(product)).collect(Collectors.toList());
		return productReponseList;
	}
	
	public String removeProduct(MyUserDetails userDetails, Long productId) throws Exception {
		Seller seller = userService.currentSeller(userDetails);
		Product product = productRepository.findByIdAndSeller(productId,seller);
		if(product == null) {
			throw new CustomException("product not found", 10);
		}
		productRepository.delete(product);
		return "product successfully deleted";
	}
	
	public String removeProducts(MyUserDetails userDetails, List<Long> productIds) throws Exception{
		Seller seller = userService.currentSeller(userDetails);
		List<Product> productList = productRepository.findAllByIdAndSeller(productIds, seller.getId());
		if(productList.size() <= 0) {
			throw new CustomException("products not found", 10);
		}
		productRepository.deleteAll(productList);
		return "products successfully deleted";
	}
	
	public Product updateProduct(MyUserDetails userDetails, Long productId, ProductRequest request) throws Exception {
		Seller seller = userService.currentSeller(userDetails);
		Product product = productRepository.findByIdAndSeller(productId,seller);
		if(product == null) {
			throw new CustomException("product not found", 10);
		}
		product.setProductName(Optional.ofNullable(request.getProductName()).orElse(product.getProductName()));
		product.setAmount(Optional.ofNullable(request.getAmount()).orElse(product.getAmount()));
		product.setQuantity(Optional.ofNullable(request.getQuantity()).orElse(product.getQuantity()));
		product.setImageDisplayUrl(Optional.ofNullable(request.getImageDisplayUrl()).orElse(product.getImageDisplayUrl()));
		productRepository.save(product);
		return product;
	}
	
	public ProductResponse show(Long productId) throws Exception{
		Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("product not found", 10));
		return new ProductResponse(product);
	}
	
	public Map<String, Object> list(Long sellerId, int offset, int limit, String order, String sort){
		Pageable page = PageRequest.of(offset, limit, Sort.by(Direction.valueOf(order), sort));
		Page<Product> productList = null;
		Map<String, Object> response = new HashMap<>();
		if(sellerId !=0) {
			productList = productRepository.findAllBySellerId(sellerId, page);
		}else {
			productList = productRepository.findAll(page);
		}
		response.put("data", productList.getContent());
		response.put("count", productList.getNumberOfElements());
		response.put("total", productList.getTotalElements());
		return response;
	}
	
	public Map<String, Object> myProduct(MyUserDetails userDetails, int offset, int limit, String order, String sort) throws Exception{
		Seller seller = userService.currentSeller(userDetails);
		Pageable page = PageRequest.of(offset, limit, Sort.by(Direction.valueOf(order), sort));
		Page<Product> productList = productRepository.findAllBySeller(seller, page);
		Map<String, Object> response = new HashMap<>();
		response.put("data", productList.getContent());
		response.put("count", productList.getNumberOfElements());
		response.put("total", productList.getTotalElements());
		return response;
	}
}
