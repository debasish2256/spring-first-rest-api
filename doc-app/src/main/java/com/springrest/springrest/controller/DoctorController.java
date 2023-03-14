package com.springrest.springrest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.springrest.springrest.model.Doctor;
import com.springrest.springrest.service.DoctorService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

	@Autowired
	DoctorService service;

	@PostMapping(value = "/doctor")
	public ResponseEntity<String> saveDoctor(@RequestBody String requestDoctor) {

		JSONObject json = new JSONObject(requestDoctor);

		List<String> validationList = validateDoctor(json);

		if (validationList.isEmpty()) {
			Doctor doctor = setDoctor(json);
			service.saveDoctor(doctor);
			return new ResponseEntity<>("Doctor saved", HttpStatus.CREATED);
		} else {

			String[] answer = Arrays.copyOf(validationList.toArray(), validationList.size(), String[].class);

			return new ResponseEntity<>("Please pass these mandatory parameters- " + Arrays.toString(answer),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/doctor")
	public List<Doctor> getDoctor(@Nullable @RequestParam String doctorId) {

		return service.getDoctor(doctorId);
	}

	private List<String> validateDoctor(JSONObject json) {

		List<String> errorList = new ArrayList<>();

		if (!json.has("doctorId")) {
			errorList.add("doctorId");
		}

		if (!json.has("doctorName")) {
			errorList.add("doctorName");
		}

		if (!json.has("specializedIn")) {
			errorList.add("specializedIn");
		}

		return errorList;

	}

	public Doctor setDoctor(JSONObject json) {
		Doctor doctor = new Doctor();

		String doctorId = json.getString("doctorId");
		doctor.setDoctorId(Integer.valueOf(doctorId));

		String doctorName = json.getString("doctorName");
		doctor.setDoctorName(doctorName);

		String specializedIn = json.getString("specializedIn");
		doctor.setSpecializedIn(specializedIn);

		if (json.has("experience")) {
			String exp = json.getString("experience");
			doctor.setExperience(exp);
		}

		return doctor;

	}

	@DeleteMapping("/deleteDoctor")
	public ResponseEntity<String> deleteDoctor(@RequestParam("id") Integer id) {
		service.deleteDoctor(id);
		return new ResponseEntity<>("Doctor deleted having id- " + id, HttpStatus.OK);
	}

	@PutMapping("/updateDoctor")
	public ResponseEntity<String> updateDoctorDetails(@RequestBody String doctor) {
		JSONObject jsonObject = new JSONObject(doctor);
		service.updateDoctorDetails(jsonObject);
		return new ResponseEntity<>("Doctor details updated having id - " + jsonObject.getInt("doctorId"),
				HttpStatus.CREATED);
	}

}
