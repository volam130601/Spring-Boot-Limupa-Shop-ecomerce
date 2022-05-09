package com.example.limupashope.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.limupashope.dto.RoleDto;
import com.example.limupashope.dto.UserDto;
import com.example.limupashope.entity.Role;
import com.example.limupashope.entity.User;
import com.example.limupashope.service.RoleService;
import com.example.limupashope.service.UserService;
import com.example.limupashope.utils.SessionUtils;

@Controller
@RequestMapping("admin/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
   	private SessionUtils sessionUtils;
   	@ModelAttribute("fullname")
   	public String fullName() {
   		return sessionUtils.getFullName();
   	}
    
    @ModelAttribute("roles")
    public List<RoleDto> getRoles() {
    	return roleService.findAll().stream().map(item -> {
    		RoleDto dto = new RoleDto();
    		BeanUtils.copyProperties(item, dto);
    		dto.setRoleId(item.getId());		
    		return dto;
    	}).collect(Collectors.toList());
    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @RequestMapping("") //RequestMapping("") : để tránh lỗi method 'POST' not supportd
    public String show(ModelMap model) {
        List<User> list = userService.findAll();
        model.addAttribute("users", list);
        return "admin/users/list";
    }

    @GetMapping("add")
    public String add(Model model) {
    	UserDto dto = new UserDto();
    	dto.setCheckEdit(false);
    			
        model.addAttribute("user", dto);
        return "admin/users/addOrEdit-user";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
                                     @Valid @ModelAttribute("user") UserDto dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/users/addOrEdit-user");
        }
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        
        Role role = new Role();
        role.setId(dto.getRoleId());
        entity.setRole(role);
        
        userService.save(entity);
        
        model.addAttribute("message", "User is saved!");
        return new ModelAndView("forward:/admin/users", model);
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Long id) {
        Optional<User> opt = userService.findById(id);
        UserDto dto = new UserDto();
        if (opt.isPresent()) {
            User entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setCheckEdit(true);
            dto.setPassword("");
            model.addAttribute("user", dto);
            return new ModelAndView("admin/users/addOrEdit-user", model);
        }
        model.addAttribute("message", "User is not existed!");
        return new ModelAndView("redirect:/admin/users", model);
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Long id) {
        userService.deleteById(id);
        model.addAttribute("message", "User is deleted!");
        return new ModelAndView("forward:/admin/users", model);
    }

    @GetMapping("delete")
    public ModelAndView deleteAll(ModelMap model, @RequestParam("ids") Long[] ids) {
        if (ids != null) {
            userService.deleteByIds(ids);
            model.addAttribute("message", "User is deleted!");
        }
        return new ModelAndView("forward:/admin/users", model);
    }
}
