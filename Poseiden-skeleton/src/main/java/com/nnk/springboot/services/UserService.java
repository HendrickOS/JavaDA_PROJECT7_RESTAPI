package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User findByUsername(String username);

	User findById(Integer id);

	void delete(User user);

}
