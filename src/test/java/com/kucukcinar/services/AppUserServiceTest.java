package com.kucukcinar.services;

import com.kucukcinar.repository.AppUserRepository;
import com.kucukcinar.requests.registration.RegistrationRequest;
import com.kucukcinar.requests.login.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppUserServiceTest {

    @Autowired
    private AppUserService appUserService;

    @MockBean
    private AppUserRepository appUserRepository;

    @Test
    public void authenticateUserTest() throws UsernameNotFoundException {

        LoginRequest loginRequest = mock(LoginRequest.class);

        when(loginRequest.getPassword()).thenThrow(new UsernameNotFoundException("Msg"));
        when(loginRequest.getUsername()).thenReturn("username");

        assertThrows(UsernameNotFoundException.class, () -> appUserService.authenticateUser(loginRequest));
        verify(loginRequest).getPassword();
        verify(loginRequest).getUsername();
    }

    @Test
    public void registerUserTest() {
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);

        when(registrationRequest.getEmail()).thenThrow(new UsernameNotFoundException("Msg"));

        assertThrows(UsernameNotFoundException.class, () -> appUserService.registerUser(registrationRequest));
        verify(registrationRequest).getEmail();
    }
}