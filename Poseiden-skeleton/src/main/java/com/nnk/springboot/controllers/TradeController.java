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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@Controller
public class TradeController {

	// TODO: Inject Trade service
	@Autowired
	private TradeService tradeService;

	// TODO: find all Trade, add to model
	@RequestMapping("/trade/list")
	public String home(Model model) {
		model.addAttribute("trade", tradeService.findAll());
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		return "trade/add";
	}

	// TODO: check data valid and save to db, after saving return Trade list
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			tradeService.save(trade);
			model.addAttribute("trade", tradeService.findAll());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	// TODO: get Trade by Id and to model then show to the form
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	// TODO: check required fields, if valid call service to update Trade and return
	// Trade list
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "trade/update";
		}
		trade.setTradeId(id);
		tradeService.save(trade);
		model.addAttribute("trade", tradeService.findAll());
		return "redirect:/trade/list";
	}

	// TODO: Find Trade by Id and delete the Trade, return to Trade list
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.findById(id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		tradeService.delete(trade);
		model.addAttribute("trade", tradeService.findAll());
		return "redirect:/trade/list";
	}
}
