package com.example.limupashope.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.limupashope.utils.SessionUtils;



@Controller
@RequestMapping("admin")
public class HomeController{
	@Autowired
	private SessionUtils sessionUtils;
	@ModelAttribute("fullname")
	public String fullName() {
		return sessionUtils.getFullName();
	}
	
    @GetMapping("home")
    public String home(ModelMap model) {
    	return "admin/home";
    }
}
