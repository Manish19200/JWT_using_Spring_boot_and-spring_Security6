package com.auth.jwttoken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	 

	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 
		  http.csrf(c->c.disable()).authorizeHttpRequests(a->
		 a.requestMatchers("/api/v1/*").permitAll()
		 .anyRequest()
		 .authenticated())
		  .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 
		 return http.build();
		 
		
		 
	 }

}
