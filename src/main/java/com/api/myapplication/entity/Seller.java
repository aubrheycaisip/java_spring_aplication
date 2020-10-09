package com.api.myapplication.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Seller")
@SuperBuilder
public class Seller extends User{

	@OneToMany(mappedBy = "seller")
	@JsonManagedReference
	private List<Product> products;
	
	public Seller() {
		
	}
}
