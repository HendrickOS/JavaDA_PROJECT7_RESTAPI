package com.nnk.springboot.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.dao.UserDao;
import com.nnk.springboot.domain.User;

@Component
public class DataInitializr {

	@Autowired
	UserDao userDao;

	@PostConstruct
	public void init() {
		User user = new User();

		user.setUsername("test");
		user.setPassword("test");
		user.setFullname("testFullName");
		user.setRole("USER");

		userDao.save(user);
	}
}
