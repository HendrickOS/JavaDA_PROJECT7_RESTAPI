package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer>, CrudRepository<CurvePoint, Integer> {

	Optional<CurvePoint> findById(Integer id);

}
