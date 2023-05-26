package com.service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.service.product.model.ProductModel;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
	
	@Query(value = "SELECT price FROM product_model WHERE product_id = :product_id", nativeQuery = true)
	public double findByProductId(@Param("product_id") int product_id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE product_model SET quantity_available = product_model.quantity_available + 1 WHERE product_id = ?1",nativeQuery = true)
	public void incrementQuantityByOne(int product_id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE product_model SET quantity_available = product_model.quantity_available - 1 WHERE product_id = ?1",nativeQuery = true)
	public void decrementQuantityByOne(int product_id);

}
