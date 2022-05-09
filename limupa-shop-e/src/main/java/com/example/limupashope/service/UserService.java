package com.example.limupashope.service;

import java.util.List;
import java.util.Optional;

import com.example.limupashope.entity.User;

public interface UserService {

	void deleteById(Long id);

	Optional<User> findById(Long id);
	Optional<User> findByUsername(String username);

	List<User> findAll();

	<S extends User> S save(S entity);
	
	void deleteByIds(Long[] ids);
	
	User login(String username , String password);

	User save(String email);

}
