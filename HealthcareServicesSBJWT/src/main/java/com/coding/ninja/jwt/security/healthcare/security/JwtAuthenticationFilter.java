package com.coding.ninja.jwt.security.healthcare.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.coding.ninja.jwt.security.healthcare.service.UserAuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean  {
	
	@Autowired
	UserAuthService userAuthService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		// TODO Auto-generated method stub
		String header = ((HttpServletRequest)request).getHeader("Authorization");
		if(header!=null) {
	    System.out.println(header + "  Request Header");
	    String completeJwt = decodeJwt(header);
	    System.out.println(completeJwt);
	    if(!completeJwt.equalsIgnoreCase("")) {
	    	UserDetails userservice = userAuthService.loadUserByJwtToken(completeJwt);
	    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userservice, userservice, updatedAuthorities); 
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request)); 
            SecurityContextHolder.getContext().setAuthentication(authToken); 
       
	    }
		}
	    
	    chain.doFilter(request, response);
		
	}

	private String decodeJwt(String header) {
		final String BEARER = "Bearer ";
		
		return header.replace(BEARER,"").trim();
	}

}