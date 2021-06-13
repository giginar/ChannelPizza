package com.kucukcinar.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

/**
 * Email validating service using Predicate interface, returns true if the mail is valid.
 * @author yigit
 *
 */
@Service
public class EmailValidator implements Predicate<String>{

	@Override
	public boolean test(String s) {
		//TODO : Regex to validate email
		return true;
	}

}
