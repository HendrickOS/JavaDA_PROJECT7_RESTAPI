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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {

	// TODO: Inject Rating service
	@Autowired
	private RatingService ratingService;

	// TODO: find all Rating, add to model
	@RequestMapping("/rating/list")
	public String home(Model model) {
		model.addAttribute("rating", ratingService.findAll());
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	// TODO: check data valid and save to db, after saving return Rating list
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ratingService.save(rating);
			model.addAttribute("rating", ratingService.findAll());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	// TODO: get Rating by Id and to model then show to the form
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	// TODO: check required fields, if valid call service to update Rating and
	// return Rating list
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "rating/update";
		}
		rating.setId(id);
		ratingService.save(rating);
		model.addAttribute("rating", ratingService.findAll());
		return "redirect:/rating/list";
	}

	// TODO: Find Rating by Id and delete the Rating, return to Rating list
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		ratingService.delete(rating);
		model.addAttribute("rating", ratingService.findAll());
		return "redirect:/rating/list";
	}
}
