package com.spring.project;

import java.util.List;

import com.spring.project.dto.User;
import com.spring.project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-management")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping({"/update/{userId}","/update"})
	public User updateUserInfo(@RequestBody User user,@PathVariable Long userId) {
		User use=new User();
		use.setUserId(user.getUserId()!=null ? user.getUserId():userId);
		use.setAddress(user.getAddress());
		use.setName(user.getName());
		use.setPhoneNumber(user.getPhoneNumber());
		use.setUsername(user.getUsername());
		return userService.updateUserInfo(use);
	}

	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
}
