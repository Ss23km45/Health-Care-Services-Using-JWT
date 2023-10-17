package com.coding.ninja.jwt.security.healthcare.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.coding.ninja.jwt.security.healthcare.security.JwtAuthenticationFilter;

import com.coding.ninja.jwt.security.healthcare.service.UserAuthService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	ApiAuthenticationEntryPoint apiAuthenticationEntryPoint;
	
@Override
	protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/register").permitAll().antMatchers("/signin").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated().and().exceptionHandling(handling -> handling.authenticationEntryPoint(apiAuthenticationEntryPoint));
	;
	
	http.headers().frameOptions().disable();
	
	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
//		super.configure(http);
	}

@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new UserAuthService();
}

@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}

@Bean
public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
}

@Autowired
public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
	BCryptPasswordEncoder emcoder = new BCryptPasswordEncoder();
    auth
        .inMemoryAuthentication()
            .withUser("user1")
            .password(emcoder.encode("User@123"))
            .roles("USER")
        .and()
            .withUser("user2")
            .password(emcoder.encode("user@2"))
            .roles("USER");
}





}