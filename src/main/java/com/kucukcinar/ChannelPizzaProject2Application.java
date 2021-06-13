package com.kucukcinar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * The Application class for spring boot to start.
 * 
 * @author yigit
 * @version 0.1
 * @since 09.06.2021
 * 
 */

@SpringBootApplication
public class ChannelPizzaProject2Application {

	/**
	 * For spring framework to build the project.
	 * @param args
	 * @return nothing.
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(ChannelPizzaProject2Application.class, args);

	}
	
}
