package com.spring.project.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laptop {
	@Id
	private String laptop_id;
	private String name;
	private String brand;
	private String price;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;
}