package com.service.order.dto;

import java.time.LocalDateTime;
import java.util.List;


public record OrderRespDto(int orderId, int userId, String userName, LocalDateTime placedDate, double totalAmount, String status, List<OrderedProduct> orderedProduct) {

}
