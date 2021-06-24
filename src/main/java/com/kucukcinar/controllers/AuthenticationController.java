package com.kucukcinar.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kucukcinar.requests.registration.RegistrationRequest;
import com.kucukcinar.requests.login.LoginRequest;
import com.kucukcinar.services.AppUserService;

/**
 * Controller for sign in and sign up system.
 * @author yigit
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	AppUserService appUserService;
	
	/**
	 * this method used for signing in
	 * @param loginRequest - entity with username and password
	 * @return responseEntity
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return appUserService.authenticateUser(loginRequest);
		
	}
	/**
	 * This method used for signing up
	 * @param registrationRequest entity with all the necessary information of the userApp
	 * @return responseEntity
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
		return appUserService.registerUser(registrationRequest);
	}
	
}
