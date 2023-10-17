package com.coding.ninja.jwt.security.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.coding.ninja.jwt.security.healthcare.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String>{

}
