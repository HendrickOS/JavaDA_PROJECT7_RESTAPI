package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Component
public class CurvePointServiceImpl implements CurvePointService {

	@Autowired
	CurvePointRepository curvePointRepository;

	@Override
	public CurvePoint save(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	@Override
	public List<CurvePoint> findAll() {
		List<CurvePoint> result = new ArrayList<>();
		Iterable<CurvePoint> findAll = curvePointRepository.findAll();
		for (Iterator<CurvePoint> iterator = findAll.iterator(); iterator.hasNext();) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public CurvePoint findById(Integer id) {
		return curvePointRepository.findById(id).get();
	}

	@Override
	public void delete(CurvePoint curvePoint) {
		curvePointRepository.delete(curvePoint);

	}

}
