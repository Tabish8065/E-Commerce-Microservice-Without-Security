package com.service.user.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.user.client.OrderClient;
import com.service.user.dto.UserOrderRespDto;
import com.service.user.dto.UserSecurityDto;
import com.service.user.model.UserModel;
import com.service.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private OrderClient orderClient;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserModel addUser(UserModel user) {
		user.setOrder_id(new ArrayList<Integer>());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public UserModel readUser(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
	}

	public List<UserModel> readAllUser() {
		return repo.findAll();
	}

	@Override
	public UserModel updateUser(UserModel user) {
		UserModel userModel = this.readUser(user.getUser_id());
		if(user.getPassword() != null && user.getPassword().length() > 0 )
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setOrder_id(userModel.getOrder_id());
		return repo.save(user);
	}

	@Override
	public UserModel deleteUser(int id) {
		UserModel user = this.readUser(id);
		List<Integer> activeOrder = orderClient.getActiveOrderByUserId(id).getBody();
		if(activeOrder != null && activeOrder.size() != 0) {
			throw new RuntimeException("The user with id : "+id+" has acive orders with order id : "+activeOrder);
		}
		repo.delete(user);
		return user;
	}

	@Override
	public List<UserModel> addAllUser(List<UserModel> list) {
		list.stream().forEach(user -> user.setOrder_id(new ArrayList<Integer>()));
		return repo.saveAll(list);
	}

	@Override
	public UserOrderRespDto checkIfUserIsPresent(int user_id) {
		// TODO Auto-generated method stub
		
		UserModel user = this.readUser(user_id);
		 
		return new UserOrderRespDto(user.getUser_id(), user.getFirstName() + " " + user.getLastName());
	}

	@Override
	public void updateOrderList(int user_id, int order_id) {
		// TODO Auto-generated method stub
		
		UserModel user = this.readUser(user_id);
		user.getOrder_id().add(order_id);
		this.updateUser(user);
		return;
	}

	@Override
	public UserSecurityDto getUseremailPassword(String useremail) {
		// TODO Auto-generated method stub
		UserModel user = repo.findByEmail(useremail);
		
		if(user == null) throw new RuntimeException("User with email "+useremail+" not found");
		
		return new UserSecurityDto(user.getUser_id(), user.getEmail(), user.getPassword());
	}
}
