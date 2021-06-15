package com.kucukcinar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kucukcinar.models.AppUser;
import com.kucukcinar.models.AppUserRole;
import com.kucukcinar.repository.AppUserRepository;
import com.kucukcinar.requests.RegistrationRequest;
import com.kucukcinar.responses.JwtResponse;
import com.kucukcinar.security.jwt.JwtUtils;
import com.kucukcinar.security.login.LoginRequest;

/**
 * User Servive implementing UserDetailService, for saving and finding the user
 * with username(email in this case).
 * 
 * @author yigit
 * 
 */
@Service
public class AppUserService implements UserDetailsService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * This overriden method used for finding the users via their emails.
	 * 
	 * @param String email, email must be in the database otherwise exception is
	 *               thrown.
	 * @throws UsernameNotFoundException
	 * @see UsernameNotFoundException
	 */

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) throws UsernameNotFoundException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AppUser userDetails = (AppUser) authentication.getPrincipal();
		//List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				//.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), "USER"));
	}

	/**
	 * This method used for saving/signing up the user in to the database.
	 * 
	 * @param appUser entity that is going to be saved.
	 * @return String
	 * @exception IllegalStateException
	 * @see IllegalStateException
	 */
	public ResponseEntity<?> registerUser(RegistrationRequest registrationRequest) {
		System.out.println(registrationRequest.toString());
		if (appUserRepository.findByEmail(registrationRequest.getEmail()) != null) {
			return ResponseEntity.badRequest().body("Email is in use.");
		}

		AppUser appUser = new AppUser(registrationRequest.getUsername(), registrationRequest.getFirstName(), registrationRequest.getLastName(),
				registrationRequest.getEmail(), passwordEncoder.encode(registrationRequest.getPassword()),
				registrationRequest.getAddress(), registrationRequest.getPostalCode(), AppUserRole.USER);

		appUserRepository.save(appUser);

		return ResponseEntity.ok("User registered successfully!");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser user = appUserRepository.findByUsername(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return AppUser.build(user);
	}


}
