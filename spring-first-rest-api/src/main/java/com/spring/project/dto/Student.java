package com.spring.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	private String firstName;
	private String lastName;
	private int age;
	private String department;
}
