package com.api.myapplication.entity.config;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@MappedSuperclass
@SuperBuilder
public class DateAudit {

	@Column(name = "date_created", nullable = false, updatable = false)
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "date_updated", nullable = false, updatable = true)
	@UpdateTimestamp
	private Date dateUpdated;
	
	public DateAudit() {
		
	}
}
