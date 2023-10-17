package com.coding.ninja.jwt.security.healthcare.service;

import java.util.Base64;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coding.ninja.jwt.security.healthcare.model.ApplicationUser;
import com.coding.ninja.jwt.security.healthcare.model.AuthUser;
import com.coding.ninja.jwt.security.healthcare.model.JwtResponse;
import com.coding.ninja.jwt.security.healthcare.model.RegisterSignInResponse;
import com.coding.ninja.jwt.security.healthcare.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {
	
	@Autowired
	ApplicationUserRepository appUserRepository;
	
	@Autowired
	RegisterSignInResponse response;
	
	@Autowired
	UserLoginService userLoginService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	
	public RegisterSignInResponse registerUser(ApplicationUser user) {
		if((user.getUser_email()!=null) && (user.getPassword()!=null)) {
			String encode = passwordEncoder.encode(user.getPassword());
			user.setPassword(encode);
			appUserRepository.save(user);
			response.setMessage("Registration Successful");
		}else {
			response.setMessage("Password or username policy failed");
		}
		
		return response;
	}
	
	public JwtResponse getJwtToken(ApplicationUser user) throws Exception {
		
		JwtResponse jwtresponse = new JwtResponse();
		try {
			
			Optional<String> OptJwt = userLoginService.loginToGetJwt(user);
			jwtresponse.setToken(OptJwt.get());
			jwtresponse.setId(user.getUser_name());
			jwtresponse.setMessage("Authentication Successful");
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jwtresponse;
		
	}

	public ApplicationUser returnUser(String userId) {
		// TODO Auto-generated method stub
		return appUserRepository.findById(userId).get();
	}

	public ApplicationUser updateUser(String userId) {
		
		// TODO Auto-generated method stub
		return appUserRepository.save(appUserRepository.findById(userId).get());
	}

}
