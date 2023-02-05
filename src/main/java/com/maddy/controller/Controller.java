package com.maddy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maddy.dao.UserRepository;
import com.maddy.helper.Message;
import com.maddy.model.User;

import jakarta.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	UserRepository userRepository;

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
	public String signUp(Model model) {
		model.addAttribute("title", "Register - Smart Contact");
		model.addAttribute("user", new User());
		return "signup";
	}

	/* Form Submit Controller */
	@PostMapping("/form_submit")
	public String formSubmit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			@RequestParam(value = "trams", defaultValue = "false") boolean isAgreed, Model model, HttpSession session) {
			
		System.out.println("isAgreed : "+isAgreed);
		System.out.println("user : "+user);
		try {
			if(!isAgreed) {
				System.out.println("Please check Trams and Condition");
				throw new Exception("You have not check the Trams and Condition");
			}
			if(bindingResult.hasErrors()) {
				System.out.println("Server side Error "+bindingResult.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setImageUrl("default.icon");
			user.setRole("user_role");
			user.setiSEnabled(true);
			
			User saveUser = userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("SUCESS: Registration Sucessfully !!", "alert-sucess"));
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("ERROR: Someting went wrong !!"+e, "alert-denger"));
			return "signup";
		}
		
	}

}
