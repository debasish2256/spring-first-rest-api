package com.spring.project.repository;

import com.spring.project.dto.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{


	Restaurant findByName(String name);
}
