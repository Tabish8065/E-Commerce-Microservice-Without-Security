package com.service.authentication.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.authentication.dto.UserSecurityDto;

//@HttpExchange
@FeignClient(name = "user-service", url = "http://localhost:8082/user")
public interface UserClient {

//	@GetExchange("/{id}")
	@GetMapping("/fromSecurity")
	public UserSecurityDto getUsernamePassword(@RequestParam String useremail);
	
	@GetMapping("/getById/{id}")
	public String readUseremailById(@PathVariable int id);
	
		
}
