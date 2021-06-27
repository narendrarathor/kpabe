package com.openshift.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.openshift.model.FileModel;
import com.openshift.service.FileService;

@Controller
public class HomeController {
	@Autowired
    private FileService service;
	@RequestMapping("/")
	public String viewHomePage(Model model) {
	    List<FileModel> listFiles = service.listAll();
	    model.addAttribute("listFiles", listFiles);
	     
	    return "index";
	}
	@RequestMapping("/newfile")
	public String showNewProductPage(Model model) {
	    FileModel file = new FileModel();
	    model.addAttribute("file", file);
	     
	    return "new_file";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("file") FileModel file) {
	    service.save(file);
	   
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_file");
	    FileModel file = service.get(id);
	    mav.addObject("file", file);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
}
