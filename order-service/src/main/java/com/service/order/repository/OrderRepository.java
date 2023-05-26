package com.service.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.order.model.OrderModel;

import jakarta.transaction.Transactional;


@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE order_model SET is_active = :status WHERE order_id = :order_id",
			nativeQuery = true)
	public void returnOrder(@Param(value = "order_id") int order_id, @Param(value = "status") boolean status);

	@Query(value = "SELECT * FROM order_model "
			+ "WHERE user_id = :user_id",
			nativeQuery = true)
	public List<OrderModel> findByUser_User_id(@Param(value = "user_id") int user_id);
	
	@Query(value = "SELECT order_id FROM order_model WHERE"
			+ " user_id = :user_id"
			+ " AND"
			+ " is_active = :status", nativeQuery = true)
	public List<Integer> findActiveOrderId(@Param(value = "user_id") int user_id,
			@Param(value = "status") boolean status);
}
