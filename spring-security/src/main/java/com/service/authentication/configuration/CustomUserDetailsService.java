package com.service.authentication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.service.authentication.client.UserClient;
import com.service.authentication.dto.UserSecurityDto;
import com.service.authentication.model.UserModel;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		Optional<UserModel> credential = repository.findByName(username);
		UserSecurityDto user = userClient.getUsernamePassword(username);
		System.out.println("User : "+user.username()+" "+user.userId()+" "+user.password());
		UserModel credential = new UserModel(user.userId(),user.username(),user.password());
		System.out.println("Credential : "+credential);
		return credential;
    
	}
	
//	public String loadUserByUid(int uid) {
//		return userClient.readUseremailById(uid);
//	}

}
