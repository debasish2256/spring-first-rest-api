package com.springrest.springrest.controller;


import javax.validation.Valid;

import com.springrest.springrest.model.Role;
import com.springrest.springrest.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/addRole")
    public Role addRole(@Valid  @RequestBody Role role){
        return roleService.addROle(role);
    }
}
