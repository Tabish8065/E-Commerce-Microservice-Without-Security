package com.service.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.service.order.dto.OrderedProduct;


//@HttpExchange
@FeignClient(name = "product-service", url = "http://localhost:8081/product")
public interface ProductClient {

//	@GetExchange("/getPrice/{product_id}")
	@GetMapping("/getPrice/{product_id}")
	public double getPriceById(@PathVariable int product_id);

//	@GetExchange("/decrement/{product_id}")
//	@GetMapping("/decrement/{product_id}")
//	public void decrementProductByOne(@PathVariable int product_id);
//
//	@GetExchange("/increment/{product_id}")
	@GetMapping("/increment/{product_id}")
	public void incrementProductByOne(@PathVariable int product_id);
	
	@GetMapping("/orderProduct")
	public List<OrderedProduct> orderProduct(@RequestParam List<Integer> productIds, @RequestParam boolean order);
	
}
