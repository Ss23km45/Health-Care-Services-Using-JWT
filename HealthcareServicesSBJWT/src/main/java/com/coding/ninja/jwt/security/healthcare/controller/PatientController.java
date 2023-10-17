package com.coding.ninja.jwt.security.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding.ninja.jwt.security.healthcare.model.Patient;
import com.coding.ninja.jwt.security.healthcare.model.RegisterSignInResponse;
import com.coding.ninja.jwt.security.healthcare.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	PatientService patientSerivce;
	
	@Autowired
	RegisterSignInResponse signInResponse;
	

	@PostMapping("/patients/register")
	public RegisterSignInResponse saveAppointment(@RequestBody Patient patient) {
		return patientSerivce.save(patient);
	}
	
	@GetMapping("/patients/list/")
	public List<Patient> listOfAppointments(){
		return patientSerivce.listOfAppointments();
	}
	
	@GetMapping("/patients/view/{appointmentID}")
	public Patient listOfAppointments(@PathVariable String appointmentID){
		return patientSerivce.viewAppointment(appointmentID);
	}
	
	
	@DeleteMapping("/patients/delete/{appointmentID}")
	public void deleteAppointment(@PathVariable String appointmentID){
	    patientSerivce.delete(appointmentID);
	}
}