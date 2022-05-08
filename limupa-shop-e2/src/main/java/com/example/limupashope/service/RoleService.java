package com.example.limupashope.service;

import java.util.List;
import java.util.Optional;

import com.example.limupashope.entity.Role;

public interface RoleService {

	void deleteById(Long id);

	Optional<Role> findById(Long id);

	List<Role> findAll();

	<S extends Role> S save(S entity);

	void deleteByIds(long ids[]);

}
