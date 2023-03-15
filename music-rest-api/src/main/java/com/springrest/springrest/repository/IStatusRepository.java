package com.springrest.springrest.repository;

import com.springrest.springrest.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepository extends JpaRepository<Status,Integer> {
}
