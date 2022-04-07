package com.udacity.jdnd.course3.critter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Launches the Spring application. Unmodified from starter code.
 */
@SpringBootApplication
public class CritterApplication {
//	private static final Logger logger = LoggerFactory.getLogger(CritterApplication.class);
	public static void main(String[] args) {
//		logger.debug("APPLICATION STARTED");
		SpringApplication.run(CritterApplication.class, args);
	}

}
