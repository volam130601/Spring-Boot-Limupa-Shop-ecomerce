package com.example.limupashope.service;

import com.example.limupashope.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Category getById(Long aLong);

    Page<Category> findAll(Pageable pageable);

    <S extends Category> S save(S entity);

    Optional<Category> findById(Long aLong);

    void deleteById(Long aLong);

    void deleteByIds(Long[] ids);
}
