package com.spring.project.repository;

import java.util.List;

import com.spring.project.dto.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
	Event findById(int id);

	List<Event> findAll();

	List<Event> findByDate(String date);
}
