package com.springrest.springrest.controller;

import java.sql.Timestamp;

import com.springrest.springrest.model.Users;
import com.springrest.springrest.service.UserService;
import com.springrest.springrest.service.exception.RecordNotFound;
import com.springrest.springrest.util.CommonUtils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/create-user")
	public ResponseEntity<String> createUser(@RequestBody String userData) {

		JSONObject isValid = validateUserRequest(userData);

		Users user = null;

		if (isValid.isEmpty()) {
			user = setUser(userData);
		} else {
			return new ResponseEntity<String>(isValid.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Saved", HttpStatus.CREATED);
	}

	private JSONObject validateUserRequest(String userData) {
		JSONObject userJson = new JSONObject(userData);
		JSONObject errorList = new JSONObject();

		if (userJson.has("username")) {
			String username = userJson.getString("username");
			// TODO: JPA find by field
		} else {
			errorList.put("username", "Missing parameter");
		}
		if (userJson.has("password")) {
			String password = userJson.getString("password");
			if (!CommonUtils.isValidPassword(password)) {
				errorList.put("password", "Please enter valid password eg: Akshay@12390");
			}
		} else {
			errorList.put("password", "Missing parameter");
		}

		if (userJson.has("firstName")) {
			String firstName = userJson.getString("firstName");
		} else {
			errorList.put("firstName", "Missing parameter");
		}

		if (userJson.has("email")) {
			String email = userJson.getString("email");
			if (!CommonUtils.isValidEmail(email)) {
				errorList.put("email", "Please enter a valid email");
			}
		} else {
			errorList.put("email", "Missing parameter");
		}

		if (userJson.has("phoneNumber")) {
			String phoneNumber = userJson.getString("phoneNumber");
			if (!CommonUtils.isValidPhoneNumber(phoneNumber)) {
				errorList.put("phoneNumber", "Please enter a valid phone number");
			}
		} else {
			errorList.put("phoneNumber", "Missing parameter");
		}

		return errorList;

	}

	private Users setUser(String userData) {
		Users user = new Users();
		JSONObject json = new JSONObject(userData);

		user.setEmail(json.getString("email"));
		user.setPassword(json.getString("password"));
		user.setFirstName(json.getString("firstName"));
		user.setUsername(json.getString("username"));
		user.setPhoneNumber(json.getString("phoneNumber"));

		if (json.has("age")) {
			user.setAge(json.getInt("age"));
		}

		if (json.has("lastName")) {
			user.setLastName(json.getString("lastName"));
		}
		if (json.has("gender")) {
			user.setGender(json.getString("gender"));
		}
		Timestamp createdTime = new Timestamp(System.currentTimeMillis());
		user.setCreatedDate(createdTime);
		return user;

	}
	
	 @GetMapping("/getUsers")
	    public void getAllUsers(@Nullable @RequestParam("user_id") Integer id) throws RecordNotFound {
	        userService.getAllUsers(id);

	    }

	    @DeleteMapping("/deleteById")
	    public ResponseEntity<String> deleteUserById(@RequestParam("id") Integer id){
	        userService.deleteUser(id);
	        return new ResponseEntity<>("User deleted with id - "+id, HttpStatus.OK);
	    }

	    @PutMapping("/updateUser")
	    public ResponseEntity<String> updateUserDetails(@RequestBody String user){
	        JSONObject jsonObject = new JSONObject(user);
	        userService.updateUser(jsonObject);
	        return new ResponseEntity<>("User updated",HttpStatus.OK);
	    }
}
