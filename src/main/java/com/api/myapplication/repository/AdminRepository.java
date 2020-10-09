package com.api.myapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.myapplication.entity.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>{

	@Query(value="SELECT * FROM user WHERE user_type='Administrator'", nativeQuery=true)
	public List<Admin> getUserAdministrator();
}
