package com.api.myapplication.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.myapplication.entity.Product;
import com.api.myapplication.request.ProductRequest;
import com.api.myapplication.response.ProductResponse;
import com.api.myapplication.security.CurrentUser;
import com.api.myapplication.security.MyUserDetails;
import com.api.myapplication.sevices.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Secured("ROLE_SELLER")
	@PostMapping("/addProducts")
	public ResponseEntity<?> addProducts(@CurrentUser MyUserDetails userDetails, @RequestBody List<ProductRequest> requestList) throws Exception{
		return new ResponseEntity<List<ProductResponse>>(productService.addProducts(userDetails, requestList), HttpStatus.OK);
	}
	
	@Secured("ROLE_SELLER")
	@DeleteMapping("/removeProduct")
	public ResponseEntity<?> removeProduct(@CurrentUser MyUserDetails userDetails, @RequestParam Long productId) throws Exception{
		return new ResponseEntity<String>(productService.removeProduct(userDetails, productId), HttpStatus.OK);
	}
	
	@Secured("ROLE_SELLER")
	@DeleteMapping("/removeProducts")
	public ResponseEntity<?> removeProducts(@CurrentUser MyUserDetails userDetails, @RequestParam List<Long> productIds) throws Exception{
		return new ResponseEntity<String>(productService.removeProducts(userDetails, productIds), HttpStatus.OK); 
	}
	
	@Secured("ROLE_SELLER")
	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<?> updateProduct(@CurrentUser MyUserDetails userDetails, 
			@PathVariable Long productId, 
			@RequestBody ProductRequest request) throws Exception{
		return new ResponseEntity<Product>(productService.updateProduct(userDetails, productId, request), HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<?> show(@PathVariable Long productId) throws Exception{
		return new ResponseEntity<ProductResponse>(productService.show(productId),HttpStatus.OK);
	}
	
	@Secured("ROLE_SELLER")
	@GetMapping("/myProduct")
	public ResponseEntity<?> myProduct(@CurrentUser MyUserDetails userDetails,
			@RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "order", defaultValue = "ASC") String order,
            @RequestParam(value = "sort", defaultValue = "date_created") String sort) throws Exception{
		return new ResponseEntity<Map<String, Object>>(productService.myProduct(userDetails, offset, limit, order, sort),HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> list(
			@RequestParam(value = "sellerId", defaultValue = "0") Long sellerId,
			@RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "order", defaultValue = "ASC") String order,
            @RequestParam(value = "sort", defaultValue = "date_created") String sort) throws Exception{
		return new ResponseEntity<Map<String, Object>>(productService.list(sellerId, offset, limit, order, sort),HttpStatus.OK);
	}
}
