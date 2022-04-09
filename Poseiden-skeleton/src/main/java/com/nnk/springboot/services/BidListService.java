package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidListService {

	BidList save(BidList bidList);

	List<BidList> findAll();

	BidList findByAccount(String string);

	BidList findById(Integer id);

	void delete(BidList bidList);

}
