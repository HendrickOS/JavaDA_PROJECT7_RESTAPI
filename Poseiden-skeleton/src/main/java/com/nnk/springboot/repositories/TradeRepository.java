package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nnk.springboot.domain.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer>, CrudRepository<Trade, Integer> {

	Optional<Trade> findById(Integer id);

}
