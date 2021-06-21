package com.kucukcinar.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Controller page created for controlling if the user.role works clearly.
 * @author yigit
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HomeController {
	
	/**
	 * Any USER can reach this page.
	 * @return
	 */
	
	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}
	
	/**
	 * Only Admin can reach this page.
	 * @return
	 */
	@GetMapping("/admin")
	public String admin() {
		return "this is admin page";
	}
	
}
