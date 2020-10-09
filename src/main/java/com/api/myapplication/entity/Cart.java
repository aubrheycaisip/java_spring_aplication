package com.api.myapplication.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.api.myapplication.entity.config.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
public class Cart extends DateAudit{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "cart")
	@JsonBackReference
	private Buyer buyer;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@OneToMany(mappedBy = "cart")
	@JsonManagedReference
	private List<Item> items;
	
	public Cart() {
		
	}
}
