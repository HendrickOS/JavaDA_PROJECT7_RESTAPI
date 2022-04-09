package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Component
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating save(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> findAll() {
		List<Rating> result = new ArrayList<>();
		Iterable<Rating> findAll = ratingRepository.findAll();
		for (Iterator<Rating> iterator = findAll.iterator(); iterator.hasNext();) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public Rating findById(Integer id) {
		return ratingRepository.findById(id).get();
	}

	@Override
	public void delete(Rating rating) {
		ratingRepository.delete(rating);

	}

}
