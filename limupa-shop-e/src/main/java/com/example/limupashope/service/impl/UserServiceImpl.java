package com.example.limupashope.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.limupashope.dto.UserDto;
import com.example.limupashope.entity.User;
import com.example.limupashope.repository.UserRepository;
import com.example.limupashope.service.UserService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User login(String username, String password) {
		Optional<User> optExist = userRepository.findByUsername(username);

		if (optExist.isPresent() && bCryptPasswordEncoder.matches(password, optExist.get().getPassword())) {
			optExist.get().getPassword();
			return optExist.get();
		}
		return null;
	}

	@Override
	public <S extends User> S save(S entity) {
		Optional<User> optExist = findByUsername(entity.getUsername());
		if (optExist.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(optExist.get().getPassword());
			} else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		} else {
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		}
		return userRepository.save(entity);
	}
	
	
	@Override
	public User save(String email) {
		User entity = userRepository.findByEmail(email);
		if(entity != null) {
			String password = "123456";
			entity.setPassword(bCryptPasswordEncoder.encode(password));
			return userRepository.save(entity);
		}
		return null;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for (long id : ids) {
			userRepository.deleteById(id);
		}
	}
	
}
