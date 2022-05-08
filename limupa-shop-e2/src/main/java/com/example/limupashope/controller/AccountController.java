package com.example.limupashope.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.limupashope.dto.outside.LoginDto;
import com.example.limupashope.dto.outside.RegisterDto;
import com.example.limupashope.entity.Role;
import com.example.limupashope.entity.User;
import com.example.limupashope.service.UserService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@GetMapping("login")
	public String login(ModelMap model) {
		LoginDto dto = new LoginDto();
		dto.setRememberMe(false);
		model.addAttribute("account", dto);
		if (session.getAttribute("username") != null) {
			session.removeAttribute("username");
		}
		return "_account/login";
	}

	@PostMapping("login")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") LoginDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/_account/login", model);
		}
		User user = userService.login(dto.getUsername(), dto.getPassword());

		if (user == null) {
			model.addAttribute("message", "Invalid user or password!");
			return new ModelAndView("/_account/login", model);
		}
		session.setAttribute("username", user.getUsername());

		// Đọc uri có sẵn trong session
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}

		return new ModelAndView("/admin/home");
	}

	@GetMapping("register")
	public String register(ModelMap model) {
		model.addAttribute("account", new RegisterDto());
		return "_account/register";
	}

	@PostMapping("register")
	public ModelAndView register(ModelMap model, @Valid @ModelAttribute("account") RegisterDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/_account/register", model);
		}
		// Comparasion re-pass and pass
		if (!dto.getRePassword().equals(dto.getPassword())) {
			model.addAttribute("message", "Re-password and Password not similar!");
			return new ModelAndView("/_account/register", model);
		}
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);
		Role role = new Role();
		role.setId(1L);
		entity.setRole(role);

		userService.save(entity);
		return new ModelAndView("/_account/login");

	}

	@GetMapping("forgot-password")
	public String forgotPassword() {
		return "_account/forgot-password";
	}

	@PostMapping("send-email")
	public void sendEmail(@RequestParam("emailInput") String emailInput) {
//		System.out.println(emailInput);
		System.out.println(emailInput);
		System.out.println(emailInput);
	}
}
