package com.spring.project.repository;

import java.util.List;

import com.spring.project.dto.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findById(int id);

	List<Student> findAll();
}
