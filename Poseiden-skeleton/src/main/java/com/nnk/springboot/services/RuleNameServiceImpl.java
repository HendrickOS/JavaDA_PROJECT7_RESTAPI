package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Component
public class RuleNameServiceImpl implements RuleNameService {

	@Autowired
	RuleNameRepository ruleNameRepository;

	@Override
	public RuleName save(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	@Override
	public List<RuleName> findAll() {
		List<RuleName> result = new ArrayList<>();
		Iterable<RuleName> findAll = ruleNameRepository.findAll();
		for (Iterator<RuleName> iterator = findAll.iterator(); iterator.hasNext();) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public RuleName findById(Integer id) {
		return ruleNameRepository.findById(id).get();
	}

	@Override
	public void delete(RuleName ruleName) {
		ruleNameRepository.delete(ruleName);

	}

}
