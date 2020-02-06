package com.cognizant.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmsApplication {
	
    private static final Logger logger = LoggerFactory.getLogger(CmsApplication.class);

	public static void main(String[] args) {
		logger.info("In Main Class Spring Boot Application");
		SpringApplication.run(CmsApplication.class, args);
	}

}
