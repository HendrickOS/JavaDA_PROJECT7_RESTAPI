package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Component
public class TradeServiceImpl implements TradeService {

	@Autowired
	TradeRepository tradeRepository;

	@Override
	public Trade save(Trade trade) {
		return tradeRepository.save(trade);
	}

	@Override
	public List<Trade> findAll() {
		List<Trade> result = new ArrayList<>();
		Iterable<Trade> findAll = tradeRepository.findAll();
		for (Iterator<Trade> iterator = findAll.iterator(); iterator.hasNext();) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public Trade findById(Integer id) {
		return tradeRepository.findById(id).get();
	}

	@Override
	public void delete(Trade trade) {
		tradeRepository.delete(trade);

	}

}
