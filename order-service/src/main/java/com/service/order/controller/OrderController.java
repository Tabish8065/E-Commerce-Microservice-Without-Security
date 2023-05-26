package com.service.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.order.dto.OrderRespDto;
import com.service.order.model.OrderModel;
import com.service.order.request.OrderModelRequest;
import com.service.order.service.OrderService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;


	public static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@PostMapping("/register")
	public OrderRespDto bookOrder(@RequestBody OrderModelRequest order) {
		logger.info("OrderController -> bookOrder : "+order.getUser_id());
		return service.addOrder(order);
	}
	
	@Hidden
	@GetMapping("/fetchByUser/{user_id}")
	public List<OrderRespDto> retriveOrderByUserId(@PathVariable int user_id) {
		logger.info("OrderController -> retriveOrderByUserId : "+user_id);
		return service.getOrderByUserId(user_id);
	}
	
	@GetMapping("/getById/{id}")
	public OrderRespDto retriveOrderById(@PathVariable int id) {
		logger.info("OrderController -> retriveOrderById : "+id);
		return service.readOrder(id);
	}
	
	@GetMapping("/getAll")
	public List<OrderModel> retriveAllOrder(){
		logger.info("OrderController -> retriveAllOrder");
		return service.readAllOrder();
	}
	
	@DeleteMapping("/returnById/{id}")
	public OrderRespDto deleteOrder(@PathVariable int id) {
		logger.info("OrderController -> deleteOrder : "+id);
		return service.deleteOrder(id);
	}
	
	@Hidden
	@GetMapping("/activeOrder")
	public ResponseEntity<List<Integer>> getActiveOrderByUserId(@RequestParam("user_id") int user_id){
		logger.warn("OrderController -> getActiveOrderById : "+user_id);
		return new ResponseEntity<List<Integer>>(service.getActiveOrderByUserId(user_id), HttpStatusCode.valueOf(200)) ;
	}

}
