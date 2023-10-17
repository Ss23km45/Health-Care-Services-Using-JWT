package com.coding.ninja.jwt.security.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.ninja.jwt.security.healthcare.model.Appointment;
import com.coding.ninja.jwt.security.healthcare.model.RegisterSignInResponse;
import com.coding.ninja.jwt.security.healthcare.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	RegisterSignInResponse signInResponse;
	

	@PostMapping("/appointment/register")
	public RegisterSignInResponse saveAppointment(@RequestBody Appointment appointment) {
		return appointmentService.save(appointment);
	}
	
	@GetMapping("/appointment/list")
	public List<Appointment> listOfAppointments(){
		return appointmentService.listOfAppointments();
	}
	
	@GetMapping("/appointment/view/{appointmentID}")
	public Appointment listOfAppointments(@PathVariable String appointmentID){
		return appointmentService.viewAppointment(appointmentID);
	}
	
	@GetMapping("/appointment/view/{patientId}")
	public List<Appointment> patientList(@PathVariable String patientId){
		return appointmentService.patientList(patientId);
	}
	
	@DeleteMapping("/appointment/delete/{appointmentID}")
	public void deleteAppointment(@PathVariable String appointmentID){
	   appointmentService.delete(appointmentID);
	}
	
	
	
	

}