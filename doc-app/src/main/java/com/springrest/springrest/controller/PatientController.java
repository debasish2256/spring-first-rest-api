package com.springrest.springrest.controller;

import java.sql.Timestamp;
import java.util.List;

import com.springrest.springrest.model.Doctor;
import com.springrest.springrest.model.Patient;
import com.springrest.springrest.repository.DoctorRepository;
import com.springrest.springrest.service.PatientService;

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
public class PatientController {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PatientService service;

	@PostMapping(value = "/patient")
	public String savePatient(@RequestBody String patientRequest) {

		JSONObject json = new JSONObject(patientRequest);
		Patient patient = setPatient(json);
		service.savePatient(patient);

		return "Saved patient";

	}

	private Patient setPatient(JSONObject json) {

		Patient patient = new Patient();

		patient.setPatientId(json.getInt("patientId"));
		patient.setPatientName(json.getString("patientName"));
		patient.setAge(json.getInt("age"));
		patient.setPhoneNumber(json.getString("phoneNumber"));
		patient.setDiseaseType(json.getString("diseaseType"));
		patient.setGender(json.getString("gender"));

		Timestamp currTime = new Timestamp(System.currentTimeMillis());
		patient.setAdmitDate(currTime);

		String doctorId = json.getString("doctorId");
		Doctor doctor = doctorRepository.findById(Integer.valueOf(doctorId)).get();
		patient.setDoctorId(doctor);
		return patient;

	}

	@GetMapping(value = "/patient")
	public List<Patient> getPatients(@Nullable @RequestParam String doctorId,
			@Nullable @RequestParam String patientId) {
		return service.getPatient(doctorId, patientId);

	}

	@PutMapping("/updatePatient")
	public ResponseEntity<String> updatePatient(@RequestBody String patient) {
		JSONObject jsonObject = new JSONObject(patient);
		service.updateDoctor(jsonObject);
		return new ResponseEntity<>("Patient updated with id - " + jsonObject.getInt("patientId"), HttpStatus.OK);
	}

	@DeleteMapping("/deletePatientWithId")
	public ResponseEntity<String> deletePatientWithId(@RequestParam("id") Integer id) {
		service.deletePatient(id);
		return new ResponseEntity<>("Patient deleted with id - " + id, HttpStatus.OK);
	}
}
