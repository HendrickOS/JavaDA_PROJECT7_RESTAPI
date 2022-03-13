package com.nnk.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Controller
@RequestMapping("app")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	@GetMapping("error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}

	@Autowired
	OAuth2AuthorizedClientService authclientService;

	@RequestMapping("oauth2LoginSuccess")
	public String getOauth2LoginInfo(Model model,
			@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken, HttpServletRequest request) {
		OAuth2User user = authenticationToken.getPrincipal();

		String githubUserName = (String) user.getAttributes().get("login");
		User userFromDB = userRepository.findByUsername(githubUserName);

		if (userFromDB == null) {
			User newUser = new User();
			newUser.setUsername(githubUserName);
			newUser.setFullname(githubUserName);
			newUser.setPassword("toto");
			newUser.setRole("USER");
			userFromDB = userRepository.save(newUser);
			// return "redirect:/app/error";
		}

		GrantedAuthority authority = new SimpleGrantedAuthority(userFromDB.getRole());
		List<GrantedAuthority> autorities = new ArrayList<>();
		autorities.add(authority);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userFromDB.getFullname(), null,
				autorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "/bidList/list";
	}

}
