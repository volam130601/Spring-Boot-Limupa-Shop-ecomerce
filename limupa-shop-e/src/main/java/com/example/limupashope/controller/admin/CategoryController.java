package com.example.limupashope.controller.admin;

import com.example.limupashope.dto.CategoryDto;
import com.example.limupashope.entity.Category;
import com.example.limupashope.service.CategoryService;
import com.example.limupashope.utils.SessionUtils;

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
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
   	private SessionUtils sessionUtils;
   	@ModelAttribute("fullname")
   	public String fullName() {
   		return sessionUtils.getFullName();
   	}
    
    @RequestMapping("") //RequestMapping("") : để tránh lỗi method 'POST' not supportd
    public String show(ModelMap model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "admin/categories/addOrEdit-category";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
                                     @Valid @ModelAttribute("category") CategoryDto dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/categories/addOrEdit-category");
        }
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        categoryService.save(entity);

        model.addAttribute("message", "Category is saved!");
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Long id) {
        Optional<Category> opt = categoryService.findById(id);
        CategoryDto dto = new CategoryDto();
        if (opt.isPresent()) {
            Category entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setCheckEdit(true);
            
            model.addAttribute("category", dto);
            return new ModelAndView("admin/categories/addOrEdit-category", model);
        }
        model.addAttribute("message", "Category is not existed!");
        return new ModelAndView("redirect:/admin/categories", model);
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Long id) {
        categoryService.deleteById(id);
        model.addAttribute("message", "Category is deleted!");
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("delete")
    public ModelAndView deleteAll(ModelMap model, @RequestParam("ids") Long[] ids) {
        if (ids != null) {
            categoryService.deleteByIds(ids);
            model.addAttribute("message", "Category is deleted!");
        }
        return new ModelAndView("forward:/admin/categories", model);
    }
}
