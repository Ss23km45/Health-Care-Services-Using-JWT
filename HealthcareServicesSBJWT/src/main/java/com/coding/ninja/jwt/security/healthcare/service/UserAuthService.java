package com.coding.ninja.jwt.security.healthcare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coding.ninja.jwt.security.healthcare.model.ApplicationUser;
import com.coding.ninja.jwt.security.healthcare.repository.ApplicationUserRepository;
import com.coding.ninja.jwt.security.healthcare.security.JwtUtil;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserAuthService implements UserDetailsService{
	
	@Autowired
	ApplicationUserRepository userRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<ApplicationUser> opUser = userRepository.findById(username);
		ApplicationUser appUser = opUser.get();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		
		 return new org.springframework.security.core.userdetails.User(appUser.getUser_email(), appUser.getPassword(), updatedAuthorities);
		
			/*
			 * return withUsername(appUser.getUser_email()) .password(appUser.getPassword())
			 * .authorities(role_name) .accountExpired(false) .accountLocked(false)
			 * .credentialsExpired(false) .disabled(false) .build();
			 */
	}
	
	public UserDetails loadUserByJwtToken(String token) {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		return withUsername(jwtUtil.getUserName(token))
				.password(jwtUtil.getPassword(token))
				.authorities(updatedAuthorities)
										.accountExpired(false)
										.accountLocked(false)
										.disabled(false)
										.build();
	}

}