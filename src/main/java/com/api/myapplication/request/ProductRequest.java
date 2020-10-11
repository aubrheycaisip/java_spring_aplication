package com.api.myapplication.request;

import com.api.myapplication.entity.Product;

import lombok.Data;

@Data
public class ProductRequest {

	private String productName;
	
	private Double amount;
	
	private Integer quantity;
	
	private String imageDisplayUrl;
	
	public ProductRequest() {
		
	}
	
	public ProductRequest(Product product) {
		this.productName = product.getProductName();
		this.amount = product.getAmount();
		this.quantity = product.getQuantity();
		this.imageDisplayUrl = product.getImageDisplayUrl();
	}
}
