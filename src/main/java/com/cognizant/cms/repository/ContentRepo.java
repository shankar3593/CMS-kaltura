package com.cognizant.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.cognizant.cms.model.Content;


public interface ContentRepo extends MongoRepository<Content, String> {

	//void save(Content content);

}
