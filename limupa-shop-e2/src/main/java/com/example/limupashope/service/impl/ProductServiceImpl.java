package com.example.limupashope.service.impl;

import com.example.limupashope.entity.Product;
import com.example.limupashope.repository.ProductRepository;
import com.example.limupashope.service.ProductService;
import com.example.limupashope.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    @Override
    public Product getById(Long aLong) {
        return productRepository.getById(aLong);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (long id : ids) {
        	Optional<Product> opt = productRepository.findById(id);
        	
        	if(opt.isPresent()) {
        		if(!StringUtils.isEmpty(opt.get().getImage())) {
        			try {
						storageService.delete(opt.get().getImage());
					} catch (IOException e) {
						e.printStackTrace();
					}
        		}
        		productRepository.deleteById(id);
        	}
        }
    }
}
