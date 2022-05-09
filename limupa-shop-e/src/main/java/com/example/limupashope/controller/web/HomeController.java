package com.example.limupashope.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("WebController")
@RequestMapping("limupa")
public class HomeController {
	
	@GetMapping("home")
	public String home() {
		return "web/index";
	}
	
}
