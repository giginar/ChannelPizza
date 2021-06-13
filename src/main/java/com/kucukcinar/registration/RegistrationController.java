package com.kucukcinar.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Rest controller layer for registration service.
 * @author yigit
 *
 */
@RestController
@RequestMapping(path = "api")
@CrossOrigin
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	/**
	 * This method used wth post mapping to save the new user to the database.
	 * 
	 * @param request - RegistrationRequest object with necessary attributes to create a db registry
	 * @return - String user saved.
	 */
	@PostMapping (path = "/v1/registration")
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
}
