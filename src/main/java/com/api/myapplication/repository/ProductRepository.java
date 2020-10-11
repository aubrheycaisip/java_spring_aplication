package com.api.myapplication.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.myapplication.entity.Product;
import com.api.myapplication.entity.Seller;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	Page<Product> findAllBySeller(Seller seller, Pageable page);
	
	Product findByIdAndSeller(Long productId, Seller seller);
	
	@Query(value="SELECT * FROM product WHERE id IN(:productIds) AND seller_id=:sellerId", nativeQuery=true)
	List<Product> findAllByIdAndSeller(List<Long> productIds, Long sellerId);
	
	Page<Product> findAll(Pageable page);
	
	@Query(value="SELECT * FROM product WHERE seller_id=:sellerId", nativeQuery=true)
	Page<Product> findAllBySellerId(Long sellerId, Pageable page);
}
