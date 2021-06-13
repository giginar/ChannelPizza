package com.kucukcinar.registration;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kucukcinar.appuser.AppUser;
import com.kucukcinar.appuser.AppUserRole;
import com.kucukcinar.appuser.AppUserService;

/**
 * Registration Request Object save to database via this service.
 * @author yigit
 * @version 0.1
 *
 */
@Service
public class RegistrationService {
	
	@Autowired
	private EmailValidator emailValidator;
	
	@Autowired
	private AppUserService appUserService;
	
	/**
	 * This method used for registering the user with the necessary fields to the database.
	 * @param request - RegistraionRequest type object
	 * @return String - to understand that user is saved, returns as "user saved"
	 */
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
						request.getAddress(),
						request.getPostalCode(),
						AppUserRole.USER
						)
				);
		return token;
	}
}
