package com.kucukcinar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
