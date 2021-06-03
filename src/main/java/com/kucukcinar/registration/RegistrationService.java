package com.kucukcinar.registration;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kucukcinar.appuser.AppUser;
import com.kucukcinar.appuser.AppUserRole;
import com.kucukcinar.appuser.AppUserService;

@Service
public class RegistrationService {
	
	@Autowired
	private EmailValidator emailValidator;
	
	@Autowired
	private AppUserService appUserService;
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if(!isValidEmail) {
			throw new IllegalStateException("email not valid");
		}
		String token = appUserService.signUpUser(
				new AppUser(
						request.getFirstName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						AppUserRole.USER
						)
				);
		return token;
	}
}
