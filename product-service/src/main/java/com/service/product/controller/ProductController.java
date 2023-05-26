package com.service.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.product.dto.OrderedProduct;
import com.service.product.model.ProductModel;
import com.service.product.service.ProductService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	public static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/getById/{id}")
	public ProductModel readProductById(@PathVariable int id) {
		ProductModel readProduct = service.readProduct(id);
		logger.info("ProductController -> readProductById : "+id+" : "+readProduct);
		return readProduct;
	}
	
	@PostMapping("/register")
	public ProductModel saveProduct(@RequestBody ProductModel product) {
		ProductModel addProduct = service.addProduct(product);
		logger.info("ProductController -> saveProduct : "+product.getProduct_id()+" : "+addProduct);
		return addProduct;
	}
	
	@GetMapping("/getAll")
	public List<ProductModel> readAllProduct(){
		List<ProductModel> readAllProduct = service.readAllProduct();
		logger.info("ProductController -> readAllProduct : Retrived Size : "+readAllProduct.size());
		return readAllProduct;
	}
	
	@PutMapping("/update")
	public ProductModel updateProduct(@RequestBody ProductModel product) {
		ProductModel updateProduct = service.updateProduct(product);
		logger.info("ProductController -> updateProduct : "+product.getProduct_id()+" : "+updateProduct);
		return updateProduct;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		ProductModel deleteProduct = service.deleteProduct(id);
		logger.info("ProductController -> deleteProduct : "+id+" : "+deleteProduct);
		return "Product with id " + id + "is now out of stock";
	}
	
	@PostMapping("/jsonImport")
	@Hidden
	public List<ProductModel> importJson(@RequestBody List<ProductModel> list){
		System.out.println("JsonImport");
		logger.info("ProductController -> importJson : Retrived Size : "+list.size());
		return service.addAllProducts(list);
	}
	
//	@GetMapping("/getPrice/{product_id}")
//	public double getPriceById(@PathVariable int product_id) {
//		logger.info("ProductController -> getPriceById : Product Id : "+product_id);
//		return service.getPriceById(product_id);
//	}
//	
//	@GetMapping("/decrement/{product_id}")
//	public void decrementProductByOne(@PathVariable int product_id) {
//		logger.info("ProductController -> decrementProductByOne : Product Id : "+product_id);
//		service.decrementQuantityByOne(product_id);
//	}
//	
	@GetMapping("/increment/{product_id}")
	@Hidden
	public void incrementProductByOne(@PathVariable int product_id) {
		logger.info("ProductController -> incrementProductByOne : Product Id : "+product_id);
		service.incrementQuantityByOne(product_id);
	}
	
	//For Order Service
	@GetMapping("/orderProduct")
	@Hidden
	public List<OrderedProduct> orderProduct(@RequestParam List<Integer> productIds,@RequestParam boolean order){
		logger.info("ProductController -> orderProduct : Product Ids : "+productIds);
		return service.orderProduct(productIds,order);
	}
}
