package com.coding.ninja.jwt.security.healthcare.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding.ninja.jwt.security.healthcare.model.ApplicationUser;
import com.coding.ninja.jwt.security.healthcare.model.AuthUser;
import com.coding.ninja.jwt.security.healthcare.model.JwtResponse;
import com.coding.ninja.jwt.security.healthcare.model.RegisterSignInResponse;
import com.coding.ninja.jwt.security.healthcare.service.ApplicationUserService;
import com.coding.ninja.jwt.security.healthcare.service.UserLoginService;

@RestController
public class ApplicationUserController {
	@Autowired
	ApplicationUserService appUserService;
	
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public RegisterSignInResponse registerAppUser(@RequestBody ApplicationUser user) {
		System.out.println(user.toString() + "Incoming Response");
		return appUserService.registerUser(user);
	}
	
	@PostMapping("/signin")
	public JwtResponse signInUser(@RequestBody ApplicationUser user) throws Exception {
		try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUser_name(), user.getPassword(), new ArrayList<>())
                    
            );
            
            
        } catch (Exception ex) {
        	System.out.println("Inside Exception");
        	return appUserService.getJwtToken(user);
//            throw new Exception("inavalid username/password");
            
        }
		
		System.out.println("Token is generating........");
		
		return appUserService.getJwtToken(user);
		
		
		
	}
	
	@GetMapping("/viewprofile/{userId}")
	public ApplicationUser checkUser(@PathVariable String userId) {
		return appUserService.returnUser(userId);
	}
	
	@GetMapping("/editprofile/{userId}")
	public ApplicationUser updateUser(@PathVariable String userId) {
		return appUserService.updateUser(userId);
	}
	
	
	
}