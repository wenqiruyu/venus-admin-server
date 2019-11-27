package com.server.venus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VenusServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(VenusServerApplication.class);

	public static void main(String[] args) {

		logger.debug("VenusServerApplication is start ...");
		SpringApplication.run(VenusServerApplication.class, args);
	}

}
