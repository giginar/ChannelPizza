package com.kucukcinar.appusertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.validateMockitoUsage;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kucukcinar.appuser.AppUser;
import com.kucukcinar.appuser.AppUserRepository;
import com.kucukcinar.appuser.AppUserRole;
import com.kucukcinar.appuser.AppUserService;
import com.kucukcinar.registration.RegistrationRequest;

class AppUserServiceTest {
	
	@Mock
	private AppUserRepository appUserRepository;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	AppUserService appUserService = new AppUserService();
	
	String email = "deneme@deneme.com";
	
	AppUser testAppUser = new AppUser("deneme", "deneme", email, "deneme", AppUserRole.USER);
	
	RegistrationRequest regRequest = Mockito.mock(RegistrationRequest.class);
	
	@Test
	void testLoadUserByUsername() {
		Mockito.when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(testAppUser));
		
	}

	@Test
	void testSignUpUser() {
		fail("Not yet implemented");
	}

}
