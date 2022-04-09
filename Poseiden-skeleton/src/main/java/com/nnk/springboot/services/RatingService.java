package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface RatingService {

	Rating save(Rating rating);

	List<Rating> findAll();

	Rating findById(Integer id);

	void delete(Rating rating);

}
