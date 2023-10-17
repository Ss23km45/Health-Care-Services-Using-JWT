package com.coding.ninja.jwt.security.healthcare.repository;

import org.springframework.stereotype.Repository;

import com.coding.ninja.jwt.security.healthcare.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,String>{

}
