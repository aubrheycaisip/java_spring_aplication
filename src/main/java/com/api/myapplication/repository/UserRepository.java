package com.api.myapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.myapplication.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	@Query(value="SELECT * FROM user where username= :username", nativeQuery=true)
	public User getUserByUsername(String username);
}
