package com.springrest.springrest.service;

import java.util.ArrayList;
import java.util.List;

import com.springrest.springrest.model.Users;
import com.springrest.springrest.repository.IUserRepository;
import com.springrest.springrest.service.exception.RecordNotFound;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	IUserRepository userRepository;

	public List<Users> getAllUsers(Integer id) throws RecordNotFound {
		List<Users> userById = new ArrayList<>();
		if (id != null && userRepository.findById(id).get() != null) {
			userById.add(userRepository.findById(id).get());
		}
		return userById;

	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	public void updateUser(JSONObject jsonObj) {
		Users newuser = userRepository.findById(jsonObj.getInt("user_id")).get();
		newuser.setFirstName(jsonObj.getString("first_name"));
		newuser.setLastName(jsonObj.getString("last_name"));
		newuser.setEmail(jsonObj.getString("email"));
		newuser.setAge(jsonObj.getInt("age"));
		newuser.setPhoneNumber(jsonObj.getString("phone_number"));
		userRepository.save(newuser);
	}

}