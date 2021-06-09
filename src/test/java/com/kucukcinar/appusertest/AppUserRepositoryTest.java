package com.kucukcinar.appusertest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.kucukcinar.appuser.AppUser;
import com.kucukcinar.appuser.AppUserRepository;
import com.kucukcinar.appuser.AppUserRole;

@DataMongoTest
class AppUserRepositoryTest {

	
	private AppUserRepository underTest = Mockito.mock(AppUserRepository.class);
	
	@Test
	void testFindByEmail() {
		
		// given
		String email = "deneme@deneme.com";
		AppUser testAppUser = new AppUser("deneme", "deneme", email, "deneme", AppUserRole.USER);
		underTest.save(testAppUser);

		// when
		Boolean exists = underTest.findByEmail(email).isPresent();

		// then
		assertTrue(exists, "Email does not exists.");
		System.out.println(exists);

	}

}
