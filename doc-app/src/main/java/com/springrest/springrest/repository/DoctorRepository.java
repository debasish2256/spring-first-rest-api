package com.springrest.springrest.repository;

import com.springrest.springrest.model.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
