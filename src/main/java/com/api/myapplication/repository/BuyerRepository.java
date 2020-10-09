package com.api.myapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.myapplication.entity.Buyer;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long>{

}
