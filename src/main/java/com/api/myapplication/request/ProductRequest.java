package com.api.myapplication.request;

import lombok.Data;

@Data
public class ProductRequest {

	private String productName;
	
	private Double amount;
	
	private Integer quantity;
}
