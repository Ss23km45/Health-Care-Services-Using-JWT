package com.coding.ninja.jwt.security.healthcare.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.ninja.jwt.security.healthcare.model.Appointment;
import com.coding.ninja.jwt.security.healthcare.model.RegisterSignInResponse;
import com.coding.ninja.jwt.security.healthcare.repository.AppointmentRepository;

@Service
public class AppointmentService {
	
	@Autowired
	AppointmentRepository appointmentRepository;


    public void deleteAppointment(String appintId) {
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>();
    }

	public RegisterSignInResponse save(Appointment appointment) {
		RegisterSignInResponse response = new RegisterSignInResponse();
		// TODO Auto-generated method stub
		if((appointment.getBooking_id().equalsIgnoreCase(null)) || (appointment.getBooking_id().equalsIgnoreCase(""))){
			response.setMessage("Booking Failure");
			return response;
		}
		try {
			appointmentRepository.save(appointment);
			response.setMessage("Booking Successful");
		}catch(Exception e) {
			response.setMessage("Booking Failure");
		}
		
		
		return response;
	}

	public List<Appointment> listOfAppointments() {
		// TODO Auto-generated method stub
		return appointmentRepository.findAll();
	}

	public Appointment viewAppointment(String appointmentID) {
		// TODO Auto-generated method stub
		return appointmentRepository.findById(appointmentID).get();
	}

	public List<Appointment> patientList(String patientId) {
				// TODO Auto-generated method stub
		List<String> iterate = new ArrayList<>();
		iterate.add(patientId);
		return appointmentRepository.findAllById(iterate);
	}

	public void delete(String appointmentID) {
		// TODO Auto-generated method stub
		appointmentRepository.delete(appointmentRepository.findById(appointmentID).get());;
	}
}