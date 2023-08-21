package com.auth.jwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwttoken.entity.AuthReq;
import com.auth.jwttoken.entity.User;
import com.auth.jwttoken.jwtUtil.JwtUtil;
import com.auth.jwttoken.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class Controller {

	@Autowired
	private UserRepository repo;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<String> Register(@RequestBody User user) {
		String already = user.getUserName();
		if (repo.findByUserName(already) != null) {
			return new ResponseEntity<>("user Already exists",HttpStatus.BAD_REQUEST);
		}
		repo.save(user);
		return new ResponseEntity<>("Save successfully",HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<String> generateToken(@RequestBody AuthReq authReq){
		
			User user = repo.findByUserNameAndPassword(authReq.getUserName(), authReq.getPassword());
			if(user==null) {
				return new ResponseEntity<>("invalide username/password",HttpStatus.BAD_REQUEST);
			}else {
				
				return new ResponseEntity<>(jwtUtil.generateToken(authReq.getUserName()),HttpStatus.ACCEPTED);
			}
		}
}

