package com.service.product.service;

import java.util.List;

import com.service.product.dto.OrderedProduct;
import com.service.product.model.ProductModel;

public interface ProductService {


	public ProductModel addProduct(ProductModel product);
	public ProductModel readProduct(int id);
	public List<ProductModel> readAllProduct();
	public ProductModel updateProduct(ProductModel product);
	public ProductModel deleteProduct(int id);
	public List<ProductModel> addAllProducts(List<ProductModel> list);
	public double getPriceById(int product_id);
	public void incrementQuantityByOne(int product_id);
	public void decrementQuantityByOne(int product_id);
	public List<OrderedProduct> orderProduct(List<Integer> productIds, boolean order);
}
