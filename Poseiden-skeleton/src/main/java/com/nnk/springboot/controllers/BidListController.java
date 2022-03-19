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

import com.nnk.springboot.dao.BidListDao;
import com.nnk.springboot.domain.BidList;

@Controller
public class BidListController {

	// TODO: Inject Bid service
//	@Autowired
//	BidListRepository bidListRepository;
	@Autowired
	BidListDao bidListDao;

	// TODO: call service find all bids to show to the view
	@RequestMapping("/bidList/list")
	public String home(Model model) {
		model.addAttribute("bidlist", bidListDao.findAll());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	// TODO: check data valid and save to db, after saving return bid list
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidListDao.save(bid);
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	// TODO: get Bid by Id and to model then show to the form
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidListDao.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	// TODO: check required fields, if valid call service to update Bid and return
	// list Bid
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "bidList/update";
		}
		bidList.setBidListId(id);
		bidListDao.save(bidList);
		model.addAttribute("bidList", bidListDao.findAll());
		return "redirect:/bidList/list";
	}

	// TODO: Find Bid by Id and delete the bid, return to Bid list
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidListDao.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		if (bidList != null) {
			bidListDao.delete(bidList);
		}
		model.addAttribute("bidList", bidListDao.findAll());
		return "redirect:/bidList/list";
	}
}
