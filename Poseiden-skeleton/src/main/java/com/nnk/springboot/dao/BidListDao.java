package com.nnk.springboot.dao;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidListDao {

	BidList save(BidList bidList);

	List<BidList> findAll();

	BidList findByAccount(String string);

	BidList findById(Integer id);

}
