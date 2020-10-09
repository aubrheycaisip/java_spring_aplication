package com.api.myapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.myapplication.entity.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Long>{

}
