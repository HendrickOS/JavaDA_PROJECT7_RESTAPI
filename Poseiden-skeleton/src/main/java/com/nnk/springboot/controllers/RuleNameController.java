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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Controller
public class RuleNameController {

	// TODO: Inject RuleName service
	@Autowired
	private RuleNameRepository ruleNameRepository;

	// TODO: find all RuleName, add to model
	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		model.addAttribute("rulename", ruleNameRepository.findAll());
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	// TODO: check data valid and save to db, after saving return RuleName list
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ruleNameRepository.save(ruleName);
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	// TODO: get RuleName by Id and to model then show to the form
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	// TODO: check required fields, if valid call service to update RuleName and
	// return RuleName list
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "ruleName/update";
		}
		ruleName.setId(id);
		ruleNameRepository.save(ruleName);
		model.addAttribute("rulename", ruleNameRepository.findAll());
		return "redirect:/ruleName/list";
	}

	// TODO: Find RuleName by Id and delete the RuleName, return to Rule list
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		ruleNameRepository.delete(ruleName);
		model.addAttribute("ruleName", ruleNameRepository.findAll());
		return "redirect:/ruleName/list";
	}
}
