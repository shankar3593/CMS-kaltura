package com.cognizant.cms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

public class Mongo {
	
	@Bean
	public static MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(), "cms");
		return mongoTemplate;
	}


}
