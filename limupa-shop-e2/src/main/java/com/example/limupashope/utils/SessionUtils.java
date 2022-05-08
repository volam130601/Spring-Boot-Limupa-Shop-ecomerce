package com.example.limupashope.utils;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.limupashope.entity.User;
import com.example.limupashope.service.UserService;

@Component
public class SessionUtils {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
//	public void getSessions(ModelMap model) {
//		Optional<User> optExist = userService.findByUsername((String) session.getAttribute("username"));
//		if(optExist.isPresent()) {
//			model.addAttribute("fullname" , optExist.get().getFirstName() +" " + optExist.get().getLastName());
//		}
//	}
	
	public String getFullName() {
		Optional<User> optExist = userService.findByUsername((String) session.getAttribute("username"));
		if(optExist.isPresent()) {
			return optExist.get().getFirstName() +" " + optExist.get().getLastName();
		}
		return null;
	}
}
