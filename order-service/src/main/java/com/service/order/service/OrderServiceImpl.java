package com.service.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.order.client.ProductClient;
import com.service.order.client.UserClient;
import com.service.order.controller.OrderController;
import com.service.order.dto.OrderRespDto;
import com.service.order.dto.OrderedProduct;
import com.service.order.dto.UserDto;
import com.service.order.model.OrderModel;
import com.service.order.repository.OrderRepository;
import com.service.order.request.OrderModelRequest;

@Service
public class OrderServiceImpl implements OrderService {

	public static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductClient productClient;
	@Autowired
	private UserClient userClient;

	@Override
	public OrderRespDto addOrder(OrderModelRequest orderReq) {
		// TODO Auto-generated method stub
//		OrderModel order = new OrderModel();

		// Fetch the User
		final UserDto user;
		try {
			user = userClient.checkIfUserIsPresent(orderReq.getUser_id());
		}
			
			catch(Exception e) {
				throw new RuntimeException("User cant be fetched");
			}

			// Fetch the product
			List<OrderedProduct> orderedProduct = productClient.orderProduct(orderReq.getProducts(), true);
			logger.warn("Product Retrived "+orderedProduct);
			
			double price = orderedProduct.stream().mapToDouble(orderedProducts -> orderedProducts.price()).sum();
			logger.warn("Order Price "+price);
			
			
//			for (int product_id : orderReq.getProducts()) {
//				price += productClient.getPriceById(product_id);
//			}
//			
			OrderModel order = new OrderModel();
			order.setUser(orderReq.getUser_id());
			order.setOrderedProduct(orderReq.getProducts());
			order.setActive(true);
			order.setPlacedDate(LocalDateTime.now());
			order.setPrice(price);

			int orderId = orderRepository.save(order).getOrder_id();
			
			//Update User Order Id
			userClient.updateOrderInUser(order.getUser(), order.getOrder_id());
//
//			order.getOrderedProduct().stream().forEach(product_id -> productClient.decrementProductByOne(product_id));
//		} catch (Exception e) {
//			order.getOrderedProduct().stream().forEach(product_id -> productClient.decrementProductByOne(product_id));
//			throw new RuntimeException("Order can't be placed");
//		}
			
			OrderRespDto response = new OrderRespDto(orderId, user.userId(), 
					user.name(), 
					order.getPlacedDate(), price, 
					order.isActive()?"Active":"Not Active", 
							orderedProduct);
			

		return response;
	}

	@Override
	public OrderRespDto readOrder(int id) {
		// TODO Auto-generated method stub
		OrderModel order = orderRepository.findById(id).orElse(null);
		
		UserDto user;
		try {
			user = userClient.checkIfUserIsPresent(order.getUser());
		}catch(Exception e) {
			throw new RuntimeException("User cant be fetched");
		}
		
		List<OrderedProduct> orderedProduct = productClient.orderProduct(order.getOrderedProduct(), false);
		logger.warn("Product Retrived "+orderedProduct);
		
		OrderRespDto response = new OrderRespDto(order.getOrder_id(), user.userId(), 
				user.name(), 
				order.getPlacedDate(), order.getPrice(), 
				order.isActive()?"Active":"Not Active", 
				orderedProduct);
		
		return response;
	}

	@Override
	public List<OrderModel> readAllOrder() {
		// TODO Auto-generated method stub

		return orderRepository.findAll();
	}

	// Unused
	@Override
	public OrderModel updateOrder(OrderModel order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public OrderRespDto deleteOrder(int id) {
		// TODO Auto-generated method stub
		OrderRespDto order = this.readOrder(id);
		if(order.status().equals("Not Active")) {
			throw new RuntimeException("The order is not active");
		}
		order.orderedProduct().stream()
				.forEach(product -> {
					productClient.incrementProductByOne(product.productId());
					});
		logger.warn("Product Quantity Updated");
		orderRepository.returnOrder(id, false);
		
		return new OrderRespDto(order.orderId(),
				order.userId(), 
				order.userName(),
				order.placedDate(),
				order.totalAmount(),
				"Not Active",
				order.orderedProduct());

	}

	// Dev Unused Method
	@Override
	public List<OrderModel> addAllOrder(List<OrderModel> list) {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public List<OrderRespDto> getOrderByUserId(int user_id) {

//		return orderRepository.findByUser_User_id(user_id);
		return null;

	}

	@Override
	public List<Integer> getActiveOrderByUserId(int user_id) {
		// TODO Auto-generated method stub
		return orderRepository.findActiveOrderId(user_id, true);
	}

}
