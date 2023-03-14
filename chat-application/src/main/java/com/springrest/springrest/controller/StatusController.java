package com.springrest.springrest.controller;

import com.springrest.springrest.model.Status;
import com.springrest.springrest.service.StatusService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
	@Autowired
	private StatusService statusServiceservice;

	@PostMapping("/addStatus")
	public int setStatus(@RequestBody String status) {

		JSONObject jsonObject = new JSONObject(status);
		Status newStatus = statusServiceservice.setStatus(jsonObject);
		return statusServiceservice.saveSatus(newStatus);

	}

}
