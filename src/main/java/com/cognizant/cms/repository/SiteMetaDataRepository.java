package com.cognizant.cms.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognizant.cms.model.SiteMetaData;

public interface SiteMetaDataRepository extends MongoRepository<SiteMetaData, String> {
	
	}
