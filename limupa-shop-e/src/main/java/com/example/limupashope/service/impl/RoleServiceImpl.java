package com.example.limupashope.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.limupashope.entity.Role;
import com.example.limupashope.repository.RoleRepository;
import com.example.limupashope.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public <S extends Role> S save(S entity) {
		return roleRepository.save(entity);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}
	
	@Override
	public void deleteByIds(long[] ids) {
		for(long id : ids) {
			roleRepository.deleteById(id);
		}
	}
	
}
