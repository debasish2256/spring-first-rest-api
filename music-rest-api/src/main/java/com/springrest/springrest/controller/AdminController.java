package com.springrest.springrest.controller;



import com.springrest.springrest.dto.UserDto;
import com.springrest.springrest.service.UsersService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private UsersService usersService;


    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@Validated @RequestBody UserDto userDto){
        JSONObject response = usersService.addUser(userDto,"admin");
        if(response.has("errorMessage")){
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response.toString(), HttpStatus.CREATED);
    }



}
