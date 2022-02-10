package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameRepository extends JpaRepository<RuleName, Integer>, CrudRepository<RuleName, Integer> {
}
