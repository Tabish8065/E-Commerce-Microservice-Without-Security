package com.service.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.dto.UserOrderRespDto;
import com.service.user.dto.UserSecurityDto;
import com.service.user.model.UserModel;
import com.service.user.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/user")
public class UserController {

	public static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@GetMapping("/getByUID/{id}")
	public UserModel readUserById(@PathVariable int id) {
		UserModel user = service.readUser(id);
		logger.info("User Controller -> readUserById : " + id + " : " + user.toString());
		return user;
	}
	
	@GetMapping("/getById/{id}")
	@Hidden
	public String readUseremailById(@PathVariable int id) {
		logger.info("User Controller -> readUseremailById : " + id + " : " );
		return service.readUser(id).getEmail();
	}

	@PostMapping("/register")
	public UserModel saveUser(@RequestBody UserModel user) {
		UserModel userRetrived = service.addUser(user);
		logger.info("User Controller -> saveUser : " + user.getUser_id() + " : " + userRetrived.toString());
		return userRetrived;
	}

	@GetMapping("/getAll")
	public List<UserModel> readAllUser() {
		List<UserModel> readAllUser = service.readAllUser();
		logger.info("UserController -> readAllUser Retrived list size : " + readAllUser.size());
		return readAllUser;
	}

	@PutMapping("/update")
	public UserModel updateUser(@RequestBody UserModel user) {
		UserModel updateUser = service.updateUser(user);
		logger.info("UserController -> updateUser : " + user.getUser_id() + " : " + updateUser);
		return updateUser;
	}

	@DeleteMapping("/delete/{id}")
	public int deleteUser(@PathVariable int id) {
		UserModel deleteUser = service.deleteUser(id);
		logger.info("UserController -> deleteUser : " + id + " : " + deleteUser);
		return deleteUser.getUser_id();
	}

	@PostMapping("/jsonImport")
	public List<UserModel> importJson(@RequestBody List<UserModel> list) {
		logger.info("UserController -> importJson : "+list.size());
		return service.addAllUser(list);
	}
	
	@GetMapping("/fromOrder")
	@Hidden
	public UserOrderRespDto checkIfUserIsPresent(@RequestParam("user_id") int user_id) {
		logger.warn("UserController -> isUserPresent : " + user_id);
		return service.checkIfUserIsPresent(user_id);
	}
	

	@GetMapping("/updateOrder")
	@Hidden
	public void updateOrderInUser(@RequestParam("user_id") int user_id, @RequestParam("order_id") int order_id) {
		logger.warn("Update the user order list");
		service.updateOrderList(user_id, order_id);
		return;
	}
	
	@GetMapping("/fromSecurity")
	@Hidden
	public UserSecurityDto getUsernamePassword(@RequestParam String useremail) {
		logger.warn("Request from security "+useremail);
		return service.getUseremailPassword(useremail);
	}
}
