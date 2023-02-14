package com.spring.project;

import java.util.List;

import com.spring.project.dto.Event;
import com.spring.project.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@PostMapping
	public Event addEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}

	@PutMapping("/{id}")
	public Event updateEvent(@PathVariable int id, @RequestBody Event event) {
		Event existingEvent = eventRepository.findById(id);
		existingEvent.setEventName(event.getEventName());
		existingEvent.setLocationOfEvent(event.getLocationOfEvent());
		existingEvent.setDate(event.getDate());
		existingEvent.setStartTime(event.getStartTime());
		existingEvent.setEndTime(event.getEndTime());
		existingEvent.setImageUrl(event.getImageUrl());
		return eventRepository.save(existingEvent);
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable int id) {
		eventRepository.deleteById(id);
	}

	@GetMapping
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@GetMapping("/date/{date}")
	public List<Event> getAllEventsByDate(@PathVariable String date) {
		return eventRepository.findByDate(date);
	}
}
