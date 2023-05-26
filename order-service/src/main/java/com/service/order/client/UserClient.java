package com.service.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.order.dto.UserDto;

//@HttpExchange
@FeignClient(name = "user-service", url = "http://localhost:8082/user")
public interface UserClient {
//	
//	@GetExchange("/{id}")
//	public Object[] readUserById(@PathVariable int id);
//	@GetExchange("/isUserPresent")
	@GetMapping("/fromOrder")
	public UserDto checkIfUserIsPresent(@RequestParam("user_id") int user_id);
	
	@GetMapping("/updateOrder")
	public void updateOrderInUser(@RequestParam("user_id") int user_id, @RequestParam("order_id") int order_id);

}
