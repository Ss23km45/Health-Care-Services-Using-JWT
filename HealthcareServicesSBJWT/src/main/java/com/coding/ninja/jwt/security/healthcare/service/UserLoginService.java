package com.coding.ninja.jwt.security.healthcare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coding.ninja.jwt.security.healthcare.model.ApplicationUser;
import com.coding.ninja.jwt.security.healthcare.model.AuthUser;
import com.coding.ninja.jwt.security.healthcare.repository.ApplicationUserRepository;
import com.coding.ninja.jwt.security.healthcare.security.JwtUtil;

@Service
public class UserLoginService {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	ApplicationUserRepository userRepository;
	
	@Autowired
	JwtUtil jwtGenerator;
	
	@Autowired
	PasswordEncoder pwdEncoder;
	
	public Optional<String> loginToGetJwt(ApplicationUser user) throws Exception{
		System.out.println("Inside Generate JWT token");
		Optional<String> token = null;
		
		
		token = Optional.of(jwtGenerator.generateJwtToken(user));
//		System.out.println("Token generated for User " + user.getUsername() + " is " + token.get());
		
		/*
		 * Optional<ApplicationUser> user1 =
		 * userRepository.findById(user.getUser_name()); System.out.println(user1 +
		 * "found from REPO"); // System.out.println("IS User present");
		 * if(pwdEncoder.matches(user.getPassword(), user1.get().getPassword())) {
		 * System.out.println("Is Authenticated ?"); Authentication authentication =
		 * authenticationManager.authenticate(new
		 * UsernamePasswordAuthenticationToken(user1.get().getUser_name(),user1.get().
		 * getPassword() )); if(authentication.isAuthenticated()) {
		 * 
		 * } }
		 */
		
		return token;
	}
	
}
