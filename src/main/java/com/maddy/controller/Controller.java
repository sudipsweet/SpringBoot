package com.maddy.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@org.springframework.stereotype.Controller
public class Controller {
	
	@GetMapping("/")
	public String home(Model m) {
	m.addAttribute("title", "Home - Smart Contact");
		return "home";
	}
	
	@GetMapping("/about")
	public String about(Model m) {
	m.addAttribute("title", "About - Smart Contact");
		return "about";
	}
	
	@GetMapping("/signup")
	public String signUp(Model m) {
	m.addAttribute("title", "Register - Smart Contact");
		return "signup";
	}

}
