package com.service.user.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

//@HttpExchange
@FeignClient(name = "order-service", url = "http://localhost:8083/order")
public interface OrderClient {

//	@GetExchange("/activeOrder")
	@GetMapping("/activeOrder")
	public ResponseEntity<List<Integer>> getActiveOrderByUserId(@RequestParam("user_id") int user_id);
	

}
