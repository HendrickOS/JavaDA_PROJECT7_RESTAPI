package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@Controller
public class CurveController {

	// TODO: Inject Curve Point service
	@Autowired
	CurvePointService curvePointService;

	// TODO: find all Curve Point, add to model
	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("curvepoint", curvePointService.findAll());
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	// TODO: check data valid and save to db, after saving return Curve list
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			curvePointService.save(curvePoint);
			model.addAttribute("curvepoint", curvePointService.findAll());
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	// TODO: get CurvePoint by Id and to model then show to the form
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointService.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	// TODO: check required fields, if valid call service to update Curve and return
	// Curve list
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "curvePoint/update";
		}
		curvePoint.setId(id);
		curvePointService.save(curvePoint);
		model.addAttribute("curvepoint", curvePointService.findAll());
		return "redirect:/curvePoint/list";
	}

	// TODO: Find Curve by Id and delete the Curve, return to Curve list
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointService.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		curvePointService.delete(curvePoint);
		model.addAttribute("curvepoint", curvePointService.findAll());
		return "redirect:/curvePoint/list";
	}
}
