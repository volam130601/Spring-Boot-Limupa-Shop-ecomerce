package com.example.limupashope.controller.admin;

import com.example.limupashope.dto.RoleDto;
import com.example.limupashope.entity.Role;
import com.example.limupashope.service.RoleService;
import com.example.limupashope.utils.SessionUtils;
import com.example.limupashope.service.RoleService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
   	private SessionUtils sessionUtils;
   	@ModelAttribute("fullname")
   	public String fullName() {
   		return sessionUtils.getFullName();
   	}
    
    @RequestMapping("") //RequestMapping("") : để tránh lỗi method 'POST' not supportd
    public String show(ModelMap model) {
        List<Role> list = roleService.findAll();
        model.addAttribute("roles", list);
        return "admin/role/list";
    }

    @GetMapping("add")
    public String add(Model model) {
    	RoleDto dto = new RoleDto();
    	dto.setCheckEdit(false);
        model.addAttribute("role", dto);
        return "admin/role/addOrEdit-role";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
                                     @Valid @ModelAttribute("role") RoleDto dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/role/addOrEdit-role");
        }
        Role entity = new Role();
        BeanUtils.copyProperties(dto, entity);
        roleService.save(entity);

        model.addAttribute("message", "Role is saved!");
        return new ModelAndView("forward:/admin/role", model);
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Long id) {
        Optional<Role> opt = roleService.findById(id);
        RoleDto dto = new RoleDto();
        if (opt.isPresent()) {
            Role entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setCheckEdit(true);
            
            model.addAttribute("role", dto);
            return new ModelAndView("admin/role/addOrEdit-role", model);
        }
        model.addAttribute("message", "Role is not existed!");
        return new ModelAndView("redirect:/admin/role", model);
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Long id) {
        roleService.deleteById(id);
        model.addAttribute("message", "Role is deleted!");
        return new ModelAndView("forward:/admin/role", model);
    }

    @GetMapping("delete")
    public ModelAndView deleteAll(ModelMap model, @RequestParam("ids") long[] ids) {
        if (ids != null) {
            roleService.deleteByIds(ids);
            model.addAttribute("message", "Role is deleted!");
        }
        return new ModelAndView("forward:/admin/categories", model);
    }
}
