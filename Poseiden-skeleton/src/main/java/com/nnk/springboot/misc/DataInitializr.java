package com.nnk.springboot.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nnk.springboot.dao.UserDao;
import com.nnk.springboot.domain.User;

@Component
public class DataInitializr {

	@Autowired
	UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		User user = new User();

		user.setUsername("testu");
		user.setFullname("testfn");
		user.setRole("USER");

		user.setPassword(passwordEncoder.encode("testp"));
//		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userDao.save(user);
	}
}
