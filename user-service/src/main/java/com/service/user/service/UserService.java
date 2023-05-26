package com.service.user.service;

import java.util.List;

import com.service.user.dto.UserOrderRespDto;
import com.service.user.dto.UserSecurityDto;
import com.service.user.model.UserModel;


public interface UserService {
	public UserModel addUser(UserModel user);
	public UserModel readUser(int id);
	public UserModel updateUser(UserModel user);
	public UserModel deleteUser(int id);
	public List<UserModel> addAllUser(List<UserModel> list);
	public List<UserModel> readAllUser();
	public UserOrderRespDto checkIfUserIsPresent(int user_id);
	public void updateOrderList(int user_id, int order_id);
	public UserSecurityDto getUseremailPassword(String useremail);
	
}
