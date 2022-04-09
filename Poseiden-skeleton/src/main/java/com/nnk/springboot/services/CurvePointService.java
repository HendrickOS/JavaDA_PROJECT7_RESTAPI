package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

	CurvePoint save(CurvePoint curvePoint);

	List<CurvePoint> findAll();

	CurvePoint findById(Integer id);

	void delete(CurvePoint curvePoint);

}
