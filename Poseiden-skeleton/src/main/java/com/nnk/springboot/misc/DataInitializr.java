package com.nnk.springboot.misc;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.User;

@Component
public class DataInitializr {

	public void init() {
		User user = new User();

		user.setUsername("test");
		user.setPassword("test");
		user.setFullname("test");
		user.setRole("USER");

		userDAO.save(user);
	}
}
