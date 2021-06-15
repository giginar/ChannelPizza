package com.kucukcinar.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kucukcinar.requests.RegistrationRequest;
import com.kucukcinar.security.login.LoginRequest;
import com.kucukcinar.services.AppUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	AppUserService appUserService;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("------********-------Login denemesi-------****-------");
		return appUserService.authenticateUser(loginRequest);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
		return appUserService.registerUser(registrationRequest);
	}
	
}
