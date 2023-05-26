package com.service.order.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
public class OrderModelRequest {
	@NotNull
	private int user_id;
	
	@Size(min = 1)
	private List<Integer> products = new ArrayList<>();

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<Integer> getProducts() {
		return products;
	}

	public void setProducts(List<Integer> products) {
		this.products = products;
	}

	public OrderModelRequest(@NotNull int user_id,@Size(min = 1) List<Integer> products) {
		super();
		this.user_id = user_id;
		this.products = products;
	}

	public OrderModelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderModelRequest [user_id=" + user_id + ", products=" + products + "]";
	}

}
