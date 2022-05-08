package com.example.limupashope.service;

import java.util.List;
import java.util.Optional;

import com.example.limupashope.entity.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long aLong);

    Product getById(Long aLong);

    <S extends Product> S save(S entity);

    void deleteById(Long aLong);

    void deleteByIds(Long[] ids);
}
