package com.nnk.springboot.misc;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.User;

@Component
public class DataInitializr {

	public void init() {
		UserEntity user = new UserEntity();
		User user = new User();

		user.setUsername(null);
		user.setPassword("user123");
		user.setFullname(null);
		user.setRole(null);
		user.setEmail("springuser");
		userDAO.save(user);
	}
}
