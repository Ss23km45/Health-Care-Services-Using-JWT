package com.coding.ninja.jwt.security.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.coding.ninja.jwt.security.healthcare.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository  extends JpaRepository<ApplicationUser, String>{

}
