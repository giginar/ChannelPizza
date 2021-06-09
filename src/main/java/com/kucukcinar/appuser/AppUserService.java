package com.kucukcinar.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User Servive implementing UserDetailService, for saving and finding the user with username(email in this case).
 * @author yigit
 * 
 */
@Service
public class AppUserService implements UserDetailsService{
	
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/**
	 * This overriden method used for finding the users via their emails.
	 * @param String email, email must be in the database otherwise exception is thrown.
	 * @throws UsernameNotFoundException
	 * @see UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	/**
	 * This method used for saving/signing up the user in to the database. 
	 * @param appUser entity that is going to be saved.
	 * @return String
	 * @exception IllegalStateException
	 * @see IllegalStateException
	 */
	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (userExists) {
			throw new IllegalStateException("email already taken"); 
		}
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		
		appUser.setPassword(encodedPassword);
	
		appUserRepository.save(appUser);
		
		return "User saved";
	}

}
