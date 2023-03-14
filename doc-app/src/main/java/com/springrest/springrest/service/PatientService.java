package com.springrest.springrest.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.springrest.springrest.model.Doctor;
import com.springrest.springrest.model.Patient;
import com.springrest.springrest.repository.DoctorRepository;
import com.springrest.springrest.repository.PatientRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;
    @Autowired
    private DoctorRepository doctorRepository;

    public void savePatient(Patient patient) {
        repository.save(patient);
    }

    public List<Patient> getPatient(@Nullable String doctorId, @Nullable String patientId) {
        List<Patient>patients = new ArrayList<>();
        List<Patient>onlyPatient =  new ArrayList<>();
        if(null!=doctorId){
            patients = repository.findAll();
            for(Patient p:patients){
                if(p.getDoctorId().getDoctorId()==(Integer.parseInt(doctorId))){
                    onlyPatient.add(p);
                }
            }return onlyPatient;
        }
        else if(patientId!=null){
            List<Integer>ids = new ArrayList<>();
            ids.add(Integer.parseInt(patientId));
            patients = repository.findAllById(ids);
            return patients;
        }
        else if(doctorId==null && patientId==null){
            patients = repository.findAll();
            return patients;
        }
        return patients;
    }

    public void updateDoctor(JSONObject json) {
        Patient patient = repository.findById(json.getInt("patientId")).get();
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
        repository.save(patient);
    }

    public void deletePatient(Integer id) {
        repository.deleteById(id);
    }
}
