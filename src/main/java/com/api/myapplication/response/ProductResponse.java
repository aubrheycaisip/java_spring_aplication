package com.api.myapplication.response;

import com.api.myapplication.entity.Product;

import lombok.Data;

@Data
public class ProductResponse {

	private Long id;
	private String productName;
	private Double amount;
	private Integer quantity;
	private String imageDisplayUrl;
	
	public ProductResponse(Product product) {
		this.id = product.getId();
		this.productName = product.getProductName();
		this.amount = product.getAmount();
		this.quantity = product.getQuantity();
		this.imageDisplayUrl = product.getImageDisplayUrl();
	}
	
	public ProductResponse() {
		
	}
}
