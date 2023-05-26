package com.service.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int product_id;

	@NotNull(message = "Product Name can not be null.")
	private String name;

	@Min(value = 1, message = "Value should be greater than 1")
	private double price;

	@Min(value = 0, message = "Stock of the product sholud be greater than 1")
	private int quantityAvailable;

	private String description;

	public ProductModel(int product_id, @NotNull(message = "Product Name can not be null.") String name,
			@Min(value = 1, message = "Value should be greater than 1") double price,
			@Min(value = 0, message = "Stock of the product sholud be greater than 1") int quantityAvailable,
			String description) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.description = description;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductModel() {
		super();
	}

	@Override
	public String toString() {
		return "ProductModel [product_id=" + product_id + ", name=" + name + ", price=" + price + ", quantityAvailable="
				+ quantityAvailable + ", description=" + description + "]";
	}
}
