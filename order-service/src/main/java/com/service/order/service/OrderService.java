package com.service.order.service;

import java.util.List;

import com.service.order.dto.OrderRespDto;
import com.service.order.model.OrderModel;
import com.service.order.request.OrderModelRequest;

public interface OrderService {
	
	public OrderRespDto readOrder(int id);
	public List<OrderModel> readAllOrder();
	public OrderModel updateOrder(OrderModel order);
	public OrderRespDto deleteOrder(int id);
	public List<OrderModel> addAllOrder(List<OrderModel> list);
	List<OrderRespDto> getOrderByUserId(int user_id);
	OrderRespDto addOrder(OrderModelRequest orderReq);
	public List<Integer> getActiveOrderByUserId(int user_id);
	
}
