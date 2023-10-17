package com.coding.ninja.jwt.security.healthcare.security;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.coding.ninja.jwt.security.healthcare.model.ApplicationUser;
import com.coding.ninja.jwt.security.healthcare.model.AuthUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {
	
	private final String ROLES_KEY="roles";

	
	private JwtParser jwtparser;
	private String secretKey;
	private Long validityInMilliseconds;
	
	public JwtUtil(@Value("$jwt.secret") String secretKey) {
		this.secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.validityInMilliseconds = 600000L;
	}

	/**
	 * Create Jwt Token Based on userName and Roles we pass in here
	 * @param userName
	 * @param roles
	 * @return jwtToken
	*/
	public String createToken(ApplicationUser user) {
		Claims claims = Jwts.claims().setSubject(user.getUser_name());
		
		Date date = new Date();
		Date expiresAt = new Date(date.getTime() + validityInMilliseconds);
		
		return Jwts.builder()
				   .setClaims(claims)
				   .setIssuedAt(date)
				   .setExpiration(expiresAt)
				   .signWith(SignatureAlgorithm.HS256, secretKey)
				   .compact();
	}
	
	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public String getUserName(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	/*
	 * public List<GrantedAuthority> getRoles(String token){ List<Map<String,
	 * String>> roleClaims = Jwts.parser() .setSigningKey(secretKey)
	 * .parseClaimsJws(token) .getBody() .get(ROLES_KEY,List.class); return
	 * roleClaims.stream() .map(role->new
	 * SimpleGrantedAuthority(role.get("authority"))) .collect(Collectors.toList());
	 * }
	 */
	
	
	public String generateJwtToken(ApplicationUser user) {
		return createToken(user);
	}

	public String getPassword(String token) {
		// TODO Auto-generated method stub
		return "mukeshCh";
	}

}