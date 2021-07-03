package com.openshift.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openshift.model.User;

@Controller
public class HomeController {
	@RequestMapping("/index")
	public String viewHomePage(Model model) {  
		User user =new User();
		model.addAttribute("user",user);
	    return "index";
	}
	
	
	
	@RequestMapping("/signup")
	public String viewSignupPage(Model model) {
		User user =new User();
		model.addAttribute("user",user);
	    return "signup";
	}
	
	
}
