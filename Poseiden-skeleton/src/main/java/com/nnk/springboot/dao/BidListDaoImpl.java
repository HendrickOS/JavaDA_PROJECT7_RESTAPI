package com.nnk.springboot.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Component
public class BidListDaoImpl implements BidListDao {

	@Autowired
	BidListRepository bidListRepository;

	@Override
	public BidList save(BidList bidList) {
		return bidListRepository.save(bidList);
	}

	@Override
	public List<BidList> findAll() {
		List<BidList> result = new ArrayList<>();
		Iterable<BidList> findAll = bidListRepository.findAll();
		for (Iterator<BidList> iterator = findAll.iterator(); iterator.hasNext();) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public BidList findByAccount(String account) {
		BidList result = new BidList();
		Iterable<BidList> findAll = bidListRepository.findAll();
		for (BidList bidList : findAll) {
			if (bidList.getAccount().equals(account))
				result = bidList;
		}
		return result;
	}

	@Override
	public BidList findById(Integer id) {
		return bidListRepository.findById(id).get();
	}

}
