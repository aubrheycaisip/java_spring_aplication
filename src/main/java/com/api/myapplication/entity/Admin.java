package com.api.myapplication.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Administrator")
@SuperBuilder
public class Admin extends User{

	public Admin() {
		
	}
}
