package com.service.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int order_id;

	@NotNull
	private int userId;

	@Min(value = 1)
	private double price;

	private LocalDateTime placedDate;

	@NotNull
	private boolean isActive;

	@Size(min = 0)
	private List<Integer> orderedProduct;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser() {
		return userId;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(LocalDateTime placedDate) {
		this.placedDate = placedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Integer> getOrderedProduct() {
		return orderedProduct;
	}

	public void setOrderedProduct(List<Integer> orderedProduct) {
		this.orderedProduct = orderedProduct;
	}

	public OrderModel(int order_id, int userId,
			@Min(value = 1, message = "Order price sholud be minimum 1") double price, LocalDateTime placedDate,
			@NotNull boolean isActive, List<Integer> orderedProduct) {
		super();
		this.order_id = order_id;
		this.userId = userId;
		this.price = price;
		this.placedDate = placedDate;
		this.isActive = isActive;
		this.orderedProduct = orderedProduct;
	}

	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderModel [order_id=" + order_id + ", user=" + userId + ", price=" + price + ", placedDate=" + placedDate
				+ ", isActive=" + isActive + ", orderedProduct=" + orderedProduct + "]";
	}

}
