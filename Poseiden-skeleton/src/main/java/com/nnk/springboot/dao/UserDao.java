package com.nnk.springboot.dao;

import java.util.List;

import com.nnk.springboot.domain.User;

public interface UserDao {

	User save(User user);

	List<User> findAll();

	User findByUsername(String username);

	User findById(Integer id);

}
