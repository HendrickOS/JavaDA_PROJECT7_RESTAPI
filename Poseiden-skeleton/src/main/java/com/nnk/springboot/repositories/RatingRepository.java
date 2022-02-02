package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nnk.springboot.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>, CrudRepository<Rating, Integer> {

}
