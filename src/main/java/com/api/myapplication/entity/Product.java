package com.api.myapplication.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.api.myapplication.entity.config.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
public class Product extends DateAudit{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_name")
	private String productName;
	
	@OneToMany(mappedBy = "cart")
	@JsonBackReference
	private List<Item> items;
	
	private Double amount;
	
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	@JsonBackReference
	private Seller seller;
	
	public Product(String productName, Double amount, Integer quantity, Seller seller) {
		this.productName = productName;
		this.amount = amount;
		this.quantity = quantity;
		this.seller = seller;
	}
	
	public Product() {
		
	}

}
