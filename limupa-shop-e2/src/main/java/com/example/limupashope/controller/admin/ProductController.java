package com.example.limupashope.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.limupashope.dto.CategoryDto;
import com.example.limupashope.dto.ProductDto;
import com.example.limupashope.entity.Category;
import com.example.limupashope.entity.Product;
import com.example.limupashope.service.CategoryService;
import com.example.limupashope.service.ProductService;
import com.example.limupashope.service.StorageService;
import com.example.limupashope.utils.SessionUtils;

@Controller
@RequestMapping("admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private StorageService storageService;

    @Autowired
	private SessionUtils sessionUtils;
	@ModelAttribute("fullname")
	public String fullName() {
		return sessionUtils.getFullName();
	}
	
    @ModelAttribute("categories")
    public List<CategoryDto> getCategories() {
    	return categoryService.findAll().stream().map(item -> {
    		CategoryDto dto = new CategoryDto();
    		BeanUtils.copyProperties(item, dto);
    		return dto;
    	}).collect(Collectors.toList());
    }
    
    @RequestMapping("") //RequestMapping("") : để tránh lỗi method 'POST' not supportd
    public String show(ModelMap model) {
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        
        return "admin/products/list";
    }

    @GetMapping("add")
    public String add(ModelMap model) {
    	ProductDto dto = new ProductDto();
    	dto.setCheckEdit(false);
        model.addAttribute("product", dto);
        
        return "admin/products/addOrEdit-product";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
                                     @Valid @ModelAttribute("product") ProductDto dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/products/addOrEdit-product");
        }
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        entity.setCategory(category);
        
        //Lưu ảnh vào folder.
        if (!dto.getImageFile().isEmpty()) {
            //UUID: là một dãy các ký tự dùng để nhận dạng
            UUID uuid = UUID.randomUUID();
            String uuString = uuid.toString();
            entity.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));//Lưu trữ file hình
            storageService.store(dto.getImageFile(), entity.getImage()); // Xác định tên file cần lưu trữ
        }
        
        
        productService.save(entity);

        model.addAttribute("message", "Product is saved!");
        
        return new ModelAndView("forward:/admin/products", model);
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Long id) {
        Optional<Product> opt = productService.findById(id);
        ProductDto dto = new ProductDto();
        if (opt.isPresent()) {
            Product entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setCheckEdit(true);
            
            model.addAttribute("product", dto);
            return new ModelAndView("admin/products/addOrEdit-product", model);
        }
        model.addAttribute("message", "Product is not existed!");
        return new ModelAndView("redirect:/admin/products", model);
    }
    
    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Long id) throws IOException {
    	Optional<Product> opt = productService.findById(id);
    	if(opt.isPresent()) {
    		if(!StringUtils.isEmpty(opt.get().getImage())) {
    			storageService.delete(opt.get().getImage());
    		}
    		productService.deleteById(id);

    		model.addAttribute("message", "Product is deleted!");
    	} else {
    		model.addAttribute("message", "Product is not Found!");
    	}
    	
        return new ModelAndView("forward:/admin/products", model);
    }

    @GetMapping("delete")
    public ModelAndView deleteAll(ModelMap model, @RequestParam("ids") Long[] ids) {
        if (ids != null) {
            productService.deleteByIds(ids);
            model.addAttribute("message", "Product is deleted!");
        }
        
        return new ModelAndView("forward:/admin/products", model);
    }
    
    //Read file Image : uploads/images ->client
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
  
}
