package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nnk.springboot.domain.BidList;

public interface BidListRepository extends JpaRepository<BidList, Integer>, CrudRepository<BidList, Integer> {

	BidList findByAccount(String account);

	Optional<BidList> findById(Integer id);

}
