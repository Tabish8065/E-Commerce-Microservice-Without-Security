package com.service.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.product.dto.OrderedProduct;
import com.service.product.model.ProductModel;
import com.service.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;

	@Override
	public ProductModel addProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public ProductModel readProduct(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Product with id "+id+" not found"));
	}

	@Override
	public List<ProductModel> readAllProduct() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public ProductModel updateProduct(ProductModel product) {
		// TODO Auto-generated method stub
		ProductModel productFetched = this.readProduct(product.getProduct_id());
		if(product.getName() == null) product.setName(productFetched.getName());
		if(product.getDescription() == null) product.setDescription(productFetched.getDescription());
		if(product.getPrice() == 0) product.setPrice(productFetched.getPrice());
		if(product.getQuantityAvailable() == 0) product.setQuantityAvailable(productFetched.getQuantityAvailable());
		return repo.save(product);
	}

	@Override
	public ProductModel deleteProduct(int id) {
		// TODO Auto-generated method stub
		ProductModel product = this.readProduct(id);
		product.setQuantityAvailable(0);
		
		return this.updateProduct(product);
		
	}

	@Override
	public List<ProductModel> addAllProducts(List<ProductModel> list) {
		// TODO Auto-generated method stub
		return repo.saveAll(list);
	}

	@Override
	public double getPriceById(int product_id) {
		// TODO Auto-generated method stub
		return repo.findByProductId(product_id);
	}

	@Override
	public void incrementQuantityByOne(int product_id) {
		// TODO Auto-generated method stub
		repo.incrementQuantityByOne(product_id);
		
	}

	@Override
	public void decrementQuantityByOne(int product_id) {
		// TODO Auto-generated method stub
		repo.decrementQuantityByOne(product_id);
		
	}

	@Override
	public List<OrderedProduct> orderProduct(List<Integer> productIds, boolean order) {
		// TODO Auto-generated method stub
		List<OrderedProduct> resp = productIds.stream()
				.map(
						(productId) -> {
							ProductModel product = this.readProduct(productId);
							if(product.getQuantityAvailable() <= 0) {
								throw new RuntimeException("Product Out Of Stock "+productId);
							}
							if(order)
								this.decrementQuantityByOne(productId);
							OrderedProduct temp = new OrderedProduct(
									product.getProduct_id(),
									product.getName(),
									product.getPrice());
							return temp;
						}).toList();
		return resp;
	}
}
