package com.kucukcinar.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppUserTest {

    /**
     * Constructor test for AppUser object
     */
    @Test
    public void constructorTest() {
        AppUser actualAppUser = new AppUser("yigit", "yigit", "ali", "yigit.ali@example.com", "password", "istanbul",
                "34000", AppUserRole.USER);
        actualAppUser.setAddress("istanbul");
        actualAppUser.setAppUserRole(AppUserRole.USER);
        actualAppUser.setEmail("yigit.ali@example.com");
        actualAppUser.setFirstName("yigit");
        actualAppUser.setLastName("ali");
        actualAppUser.setPassword("password");
        actualAppUser.setPostalCode("34000");
        actualAppUser.setUsername("yigit");
        assertEquals("istanbul", actualAppUser.getAddress());
        assertEquals(AppUserRole.USER, actualAppUser.getAppUserRole());
        assertEquals("yigit.ali@example.com", actualAppUser.getEmail());
        assertEquals("yigit", actualAppUser.getFirstName());
        assertNull(actualAppUser.getId());
        assertEquals("ali", actualAppUser.getLastName());
        assertEquals("password", actualAppUser.getPassword());
        assertEquals("34000", actualAppUser.getPostalCode());
        assertEquals("yigit.ali@example.com", actualAppUser.getUsername());
        assertTrue(actualAppUser.isAccountNonExpired());
        assertTrue(actualAppUser.isAccountNonLocked());
        assertTrue(actualAppUser.isCredentialsNonExpired());
        assertTrue(actualAppUser.isEnabled());
        assertEquals(
                "AppUser [id=null, username=yigit, firstName=yigit, lastName=ali, email=yigit.ali@example.com,"
                        + " password=password, address=istanbul, postalCode=34000, appUserRole=USER]",
                actualAppUser.toString());
    }
}

