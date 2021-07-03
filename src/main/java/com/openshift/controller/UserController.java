package com.openshift.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openshift.model.User;
import com.openshift.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String saveUser(User user) {
		service.save(user);
		return "redirect:/index";
	}

	@RequestMapping("/home")
	public String showNewHomePage(Model model,HttpSession session) {
		User user =new User();
		user.setEmail(session.getAttribute("email").toString());	
	    model.addAttribute("user",user);
	    return "home";
	}
	@RequestMapping(value = "/verifyuser", method = RequestMethod.POST)
	public String verifyUser(User user, Model model, HttpServletRequest req) {

		model.addAttribute("user", user);
		req.getSession().setAttribute("email", user.getEmail());
		List<User> users = service.listAll();
		boolean check = users.stream()
				.anyMatch(ob -> ob.getEmail().equals(user.getEmail()) && ob.getPassword().equals(user.getPassword()));
		if (check)
			return "home";
		else
			return "error";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest req) {
		return "redirect:/index";
	}

	@RequestMapping("/plotgraph")
	public String showNewProductPage(Model model, HttpSession session) {
		User user = new User();
		user.setEmail(session.getAttribute("email").toString());
		model.addAttribute("user", user);
		return "plotgraph";
	}

}
