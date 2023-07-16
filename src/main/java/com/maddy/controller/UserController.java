package com.maddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/dashboard")
	public String dashBoard(Model model) {
		System.out.println("Dashboard Page--");
		return "/normal/dashboard";
	}

}
