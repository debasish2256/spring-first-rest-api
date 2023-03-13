package com.springrest.springrest.controller;

import com.springrest.springrest.model.Status;
import com.springrest.springrest.service.StatusService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/v1/status")
public class StatusController {

	@Autowired
	StatusService service;

	@PostMapping("/status")
	public ResponseEntity<String> createStatus(@RequestBody String statusData) {
		Status status = setStatus(statusData);
		int statusId = service.saveStatus(status);
		return new ResponseEntity<>("status saved:" + statusId, HttpStatus.CREATED);
	}

	private Status setStatus(String statusData) {
		Status status = new Status();
		JSONObject json = new JSONObject(statusData);
		status.setPostData(json.getString("statusName"));
		status.setStatusDescription(json.getString("statusDescription"));
		return status;
	}

	@GetMapping("/getPost")
	public ResponseEntity<String> getPost(@Nullable @RequestParam("pId") Integer post_id,
			@Nullable @RequestParam("uId") Integer user_id) throws Exception {
		JSONArray postaArray = service.getPosts(post_id, user_id);

		return new ResponseEntity<>(postaArray.toString(), HttpStatus.OK);
	}

	@DeleteMapping("/deletePost")
	public ResponseEntity<String> deletePost(@RequestParam("id") Integer id) {
		service.deletePost(id);
		return new ResponseEntity<>("Post deleted with id - " + id, HttpStatus.OK);
	}

}
