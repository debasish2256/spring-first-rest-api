package com.spring.project.service;

import java.util.List;

import com.spring.project.dto.User;
import com.spring.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.save(user);
	}

	
	public User getUser(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}
	 

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User updateUserInfo(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
	}
}

