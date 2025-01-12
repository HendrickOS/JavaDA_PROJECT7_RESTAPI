package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.nnk.springboot.domain.User;

public interface UserRepository
		extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, CrudRepository<User, Integer> {

	User findByUsername(String username);

	Optional<User> findById(Integer id);

}
