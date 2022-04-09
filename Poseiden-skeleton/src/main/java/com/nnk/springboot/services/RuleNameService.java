package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {

	RuleName save(RuleName ruleName);

	List<RuleName> findAll();

	RuleName findById(Integer id);

	void delete(RuleName ruleName);

}
