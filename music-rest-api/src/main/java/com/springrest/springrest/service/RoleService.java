package com.springrest.springrest.service;

import com.springrest.springrest.interfaces.IRoleServiceInterface;
import com.springrest.springrest.model.Role;
import com.springrest.springrest.repository.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleServiceInterface {
    @Autowired
    private IRoleRepository iRoleRepository;
	@Override
	public  Role addROle(Role role) {
		return iRoleRepository.save(role);
	}
}
