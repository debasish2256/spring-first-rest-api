package com.springrest.springrest.repository;

import com.springrest.springrest.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
}
