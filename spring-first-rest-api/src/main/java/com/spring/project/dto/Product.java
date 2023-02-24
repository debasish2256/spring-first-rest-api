package com.spring.project.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
    private Integer id;
    private String name;
    @NotNull
    @Min(0)
    private Integer price;
    private String description;
    private String category;
    private String brand;
}

