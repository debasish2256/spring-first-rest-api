package com.spring.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
public class Event {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	private String eventName;
	private String locationOfEvent;
	private String date;
	private String startTime;
	private String endTime;
	private String imageUrl = "https://via.placeholder.com/600/92c952";
}
