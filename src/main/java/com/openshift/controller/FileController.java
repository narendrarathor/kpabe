package com.openshift.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.openshift.model.FileModel;
import com.openshift.model.User;
import com.openshift.service.FileService;

@Controller
public class FileController {
	@Autowired
    private FileService service;
	@RequestMapping("/viewfile")
	public String viewHomePage(Model model,HttpSession session) {
	    List<FileModel> listFiles = service.listAll();
	    model.addAttribute("listFiles", listFiles);
	    User user =new User();
		user.setEmail(session.getAttribute("email").toString());
		 model.addAttribute("user",user);
	    return "viewfile";
	}
	@RequestMapping("/uploadfile")
	public String showUploadPage(Model model,HttpSession session) {
		User user =new User();
		user.setEmail(session.getAttribute("email").toString());	
	    model.addAttribute("user",user);
	    return "uploadfile";
	}
	
	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public String saveFile(@RequestParam("file") MultipartFile  file ,Model model,HttpSession session) {
		service.storeFile(file); 
		User user =new User();
		user.setEmail(session.getAttribute("email").toString());
		model.addAttribute("user",user);
		return "redirect:/viewfile";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_file");
	    FileModel file = service.get(id);
	    mav.addObject("file", file);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id,Model model,HttpSession session) {
	    service.delete(id);
	    User user =new User();
		user.setEmail(session.getAttribute("email").toString());
		model.addAttribute("user",user);
		return "redirect:/viewfile";      
	}
	@RequestMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) {
        // Load file from database
        FileModel dbFile = service.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
	
}
