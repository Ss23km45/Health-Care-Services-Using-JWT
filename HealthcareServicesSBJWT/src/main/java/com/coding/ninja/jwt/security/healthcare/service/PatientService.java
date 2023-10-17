package com.coding.ninja.jwt.security.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.ninja.jwt.security.healthcare.model.Patient;
import com.coding.ninja.jwt.security.healthcare.model.RegisterSignInResponse;
import com.coding.ninja.jwt.security.healthcare.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;

	public RegisterSignInResponse save(Patient patient) {
		RegisterSignInResponse response = new RegisterSignInResponse();
		// TODO Auto-generated method stub
		if((patient.getPatient_id().equalsIgnoreCase(null)) || (patient.getPatient_id().equalsIgnoreCase(""))){
			response.setMessage("Booking Failure");
			return response;
		}
		try {
			patientRepository.save(patient);
			response.setMessage("Booking Successful");
		}catch(Exception e) {
			response.setMessage("Booking Failure");
		}
		
		
		return response;
	}

	public List<Patient> listOfAppointments() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	public Patient viewAppointment(String appointmentID) {
		// TODO Auto-generated method stub
		return patientRepository.findById(appointmentID).get();
	}

	public void delete(String appointmentID) {
		// TODO Auto-generated method stub
		patientRepository.delete(patientRepository.findById(appointmentID).get());
	}


}